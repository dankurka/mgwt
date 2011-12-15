package com.googlecode.mgwt.linker.linker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.TreeLogger.Type;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.ConfigurationProperty;
import com.google.gwt.core.ext.linker.EmittedArtifact;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.Shardable;
import com.google.gwt.core.ext.linker.SyntheticArtifact;
import com.google.gwt.core.ext.linker.impl.SelectionInformation;
import com.googlecode.mgwt.linker.server.BindingProperty;

@LinkerOrder(LinkerOrder.Order.POST)
@Shardable
public class PermutationMapLinker extends AbstractLinker {

	private XMLPermutationProvider xmlPermutationProvider;

	public PermutationMapLinker() {
		xmlPermutationProvider = new XMLPermutationProvider();
		manifestWriter = new ManifestWriter();
	}

	public static final String MANIFEST_MAP = "manifest.map";
	private ManifestWriter manifestWriter;

	@Override
	public String getDescription() {
		return "PermutationMapLinker";
	}

	@Override
	public ArtifactSet link(TreeLogger logger, LinkerContext context, ArtifactSet artifacts, boolean onePermutation) throws UnableToCompleteException {
		if (onePermutation) {
			Map<String, Set<BindingProperty>> permutationMap = buildPermutationMap(logger, context, artifacts);
			Set<Entry<String, Set<BindingProperty>>> entrySet = permutationMap.entrySet();

			//since we are in onePermutation there should be just one strongName
			//better make sure..
			if (permutationMap.size() != 1) {
				logger.log(Type.ERROR, "There should be only one permutation right now, but there were: '" + permutationMap.size() + "'");
				throw new UnableToCompleteException();
			}

			Entry<String, Set<BindingProperty>> next = entrySet.iterator().next();
			String strongName = next.getKey();
			Set<BindingProperty> bindingProperties = next.getValue();

			//all artifacts for this compilation
			Set<String> artifactsForCompilation = getArtifactsForCompilation(logger, context, artifacts);

			ArtifactSet toReturn = new ArtifactSet(artifacts);
			PermutationArtifact permutationArtifact = new PermutationArtifact(PermutationMapLinker.class, strongName, artifactsForCompilation, bindingProperties);
			toReturn.add(permutationArtifact);
			return toReturn;
		}

		ArtifactSet toReturn = new ArtifactSet(artifacts);
		Map<String, Set<BindingProperty>> map = buildPermutationMap(logger, context, artifacts);

		if (map.size() == 0) {
			//hosted mode
			return toReturn;
		}

		Map<String, PermutationArtifact> permutationArtifactAsMap = getPermutationArtifactAsMap(artifacts);

		Set<String> externalFiles = getExternalFiles(logger, context);

		Set<String> allPermutationFiles = getAllPermutationFiles(permutationArtifactAsMap);

		//get all artifacts
		Set<String> allArtifacts = getArtifactsForCompilation(logger, context, artifacts);

		for (Entry<String, PermutationArtifact> entry : permutationArtifactAsMap.entrySet()) {
			PermutationArtifact permutationArtifact = entry.getValue();
			//make a copy of all artifacts
			HashSet<String> filesForCurrentPermutation = new HashSet<String>(allArtifacts);
			//remove all permutations
			filesForCurrentPermutation.removeAll(allPermutationFiles);
			//add files of the one permutation we are interested in
			filesForCurrentPermutation.addAll(entry.getValue().getPermutationFiles());

			String permXml = buildPermXml(permutationArtifact, filesForCurrentPermutation, externalFiles);

			//emit permutation information file
			SyntheticArtifact emitString = emitString(logger, permXml, permutationArtifact.getPermutationName() + ".perm.xml");
			toReturn.add(emitString);

			//build manifest
			String maniFest = buildManiFest(entry.getKey(), filesForCurrentPermutation, externalFiles);
			toReturn.add(emitString(logger, maniFest, entry.getKey() + ".manifest"));

		}

		toReturn.add(createPermutationMap(logger, map));
		return toReturn;

	}

	public String buildPermXml(PermutationArtifact permutationArtifact, Set<String> gwtCompiledFiles, Set<String> otherResources) {
		HashSet<String> namesForPermXml = new HashSet<String>(gwtCompiledFiles);
		namesForPermXml.addAll(otherResources);
		String permXmlString = xmlPermutationProvider.writePermutationInformation(permutationArtifact.getPermutationName(), permutationArtifact.getBindingProperties(), namesForPermXml);
		return permXmlString;
	}

	/**
	 * @param permutationArtifactAsMap
	 * @return
	 */
	private Set<String> getAllPermutationFiles(Map<String, PermutationArtifact> permutationArtifactAsMap) {
		Set<String> allPermutationFiles = new HashSet<String>();

		for (Entry<String, PermutationArtifact> entry : permutationArtifactAsMap.entrySet()) {
			allPermutationFiles.addAll(entry.getValue().getPermutationFiles());
		}
		return allPermutationFiles;
	}

	protected Map<String, PermutationArtifact> getPermutationArtifactAsMap(ArtifactSet artifacts) {
		Map<String, PermutationArtifact> hashMap = new HashMap<String, PermutationArtifact>();
		for (PermutationArtifact permutationArtifact : artifacts.find(PermutationArtifact.class)) {
			hashMap.put(permutationArtifact.getPermutationName(), permutationArtifact);
		}
		return hashMap;
	}

	public boolean shouldArtifactBeInManifest(String pathName) {
		if (pathName.endsWith("symbolMap") || pathName.endsWith(".xml.gz") || pathName.endsWith("rpc.log") || pathName.endsWith("gwt.rpc") || pathName.endsWith("manifest.txt")
				|| pathName.startsWith("rpcPolicyManifest") || pathName.startsWith("soycReport")) {
			return false;
		}

		//TODO reg exp

		return true;
	}

	protected Set<String> getArtifactsForCompilation(TreeLogger logger, LinkerContext context, ArtifactSet artifacts) {
		Set<String> artifactNames = new HashSet<String>();
		for (EmittedArtifact artifact : artifacts.find(EmittedArtifact.class)) {
			String pathName = artifact.getPartialPath();

			if (shouldArtifactBeInManifest(pathName)) {
				artifactNames.add(context.getModuleName() + "/" + pathName);
			}
		}

		return artifactNames;

	}

	protected Set<String> getCompilationStrongNames(ArtifactSet artifacts) {

		HashSet<String> strongNames = new HashSet<String>();

		SortedSet<SelectionInformation> compilationResults = artifacts.find(SelectionInformation.class);
		for (SelectionInformation result : compilationResults) {
			strongNames.add(result.getStrongName());
		}

		return strongNames;
	}

	protected String buildManiFest(String moduleName, Set<String> cacheResources, Set<String> externalFiles) {
		return manifestWriter.writeManifest(externalFiles, cacheResources);
	}

	protected Set<String> getExternalFiles(TreeLogger logger, LinkerContext context) {
		HashSet<String> set = new HashSet<String>();
		SortedSet<ConfigurationProperty> properties = context.getConfigurationProperties();
		for (ConfigurationProperty configurationProperty : properties) {
			String name = configurationProperty.getName();
			if ("html5manifestlinker_files".equals(name)) {
				for (String value : configurationProperty.getValues()) {
					set.add(value);
				}
			}
		}

		return set;
	}

	protected EmittedArtifact createPermutationMap(TreeLogger logger, Map<String, Set<BindingProperty>> map) throws UnableToCompleteException {
		String string = xmlPermutationProvider.serializeMap(map);
		System.out.println(string);
		return emitString(logger, string, MANIFEST_MAP);

	}

	protected Map<String, Set<BindingProperty>> buildPermutationMap(TreeLogger logger, LinkerContext context, ArtifactSet artifacts) throws UnableToCompleteException {

		SortedSet<SelectionInformation> compilationResults = artifacts.find(SelectionInformation.class);

		HashMap<String, Set<BindingProperty>> map = new HashMap<String, Set<BindingProperty>>();

		for (SelectionInformation result : compilationResults) {
			Set<BindingProperty> list = new HashSet<BindingProperty>();
			map.put(result.getStrongName(), list);

			TreeMap<String, String> propMap = result.getPropMap();
			Set<Entry<String, String>> set = propMap.entrySet();

			for (Entry<String, String> entry : set) {
				BindingProperty bindingProperty = new BindingProperty(entry.getKey(), entry.getValue());
				list.add(bindingProperty);
			}

		}

		return map;

	}

}
