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

	public static final String EXTERNAL_FILES_CONFIGURATION_PROPERTY_NAME = "html5manifestlinker_files";
	public static final String PERMUTATION_MANIFEST_FILE_ENDING = ".manifest";
	public static final String PERMUTATION_FILE_ENDING = ".perm.xml";
	public static final String MANIFEST_MAP_FILE_NAME = "manifest.map";

	private XMLPermutationProvider xmlPermutationProvider;

	public PermutationMapLinker() {
		xmlPermutationProvider = new XMLPermutationProvider();
		manifestWriter = new ManifestWriter();
	}

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

			// since we are in onePermutation there should be just one
			// strongName
			// better make sure..
			if (permutationMap.size() != 1) {
				logger.log(Type.ERROR, "There should be only one permutation right now, but there were: '" + permutationMap.size() + "'");
				throw new UnableToCompleteException();
			}

			Entry<String, Set<BindingProperty>> next = entrySet.iterator().next();
			String strongName = next.getKey();
			Set<BindingProperty> bindingProperties = next.getValue();

			// all artifacts for this compilation
			Set<String> artifactsForCompilation = getArtifactsForCompilation(logger, context, artifacts);

			ArtifactSet toReturn = new ArtifactSet(artifacts);
			PermutationArtifact permutationArtifact = new PermutationArtifact(PermutationMapLinker.class, strongName, artifactsForCompilation, bindingProperties);
			toReturn.add(permutationArtifact);
			return toReturn;
		}

		ArtifactSet toReturn = new ArtifactSet(artifacts);
		Map<String, Set<BindingProperty>> map = buildPermutationMap(logger, context, artifacts);

		if (map.size() == 0) {
			// hosted mode
			return toReturn;
		}

		Map<String, PermutationArtifact> permutationArtifactAsMap = getPermutationArtifactAsMap(artifacts);

		Set<String> externalFiles = getExternalFiles(logger, context);

		Set<String> allPermutationFiles = getAllPermutationFiles(permutationArtifactAsMap);

		// get all artifacts
		Set<String> allArtifacts = getArtifactsForCompilation(logger, context, artifacts);

		for (Entry<String, PermutationArtifact> entry : permutationArtifactAsMap.entrySet()) {
			PermutationArtifact permutationArtifact = entry.getValue();
			// make a copy of all artifacts
			HashSet<String> filesForCurrentPermutation = new HashSet<String>(allArtifacts);
			// remove all permutations
			filesForCurrentPermutation.removeAll(allPermutationFiles);
			// add files of the one permutation we are interested in
			// leaving the common stuff for all permutations in...
			filesForCurrentPermutation.addAll(entry.getValue().getPermutationFiles());

			String permXml = buildPermXml(logger, permutationArtifact, filesForCurrentPermutation, externalFiles);

			// emit permutation information file
			SyntheticArtifact emitString = emitString(logger, permXml, permutationArtifact.getPermutationName() + PERMUTATION_FILE_ENDING);
			toReturn.add(emitString);

			// build manifest
			String maniFest = buildManiFest(entry.getKey(), filesForCurrentPermutation, externalFiles);
			toReturn.add(emitString(logger, maniFest, entry.getKey() + PERMUTATION_MANIFEST_FILE_ENDING));

		}

		toReturn.add(createPermutationMap(logger, map));
		return toReturn;

	}

	protected String buildPermXml(TreeLogger logger, PermutationArtifact permutationArtifact, Set<String> gwtCompiledFiles, Set<String> otherResources) throws UnableToCompleteException {
		HashSet<String> namesForPermXml = new HashSet<String>(gwtCompiledFiles);
		namesForPermXml.addAll(otherResources);

		try {
			return xmlPermutationProvider.writePermutationInformation(permutationArtifact.getPermutationName(), permutationArtifact.getBindingProperties(), namesForPermXml);
		} catch (XMLPermutationProviderException e) {
			logger.log(Type.ERROR, "can not build xml for permutation file", e);
			throw new UnableToCompleteException();
		}

	}

	/**
	 * @param permutationArtifactAsMap
	 * @return
	 */
	protected Set<String> getAllPermutationFiles(Map<String, PermutationArtifact> permutationArtifactAsMap) {
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

	protected boolean shouldArtifactBeInManifest(String pathName) {
		if (pathName.endsWith("symbolMap") || pathName.endsWith(".xml.gz") || pathName.endsWith("rpc.log") || pathName.endsWith("gwt.rpc") || pathName.endsWith("manifest.txt")
				|| pathName.startsWith("rpcPolicyManifest") || pathName.startsWith("soycReport")) {
			return false;
		}

		// TODO reg exp

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

	protected String buildManiFest(String moduleName, Set<String> cacheResources, Set<String> externalFiles) {
		return manifestWriter.writeManifest(externalFiles, cacheResources);
	}

	protected Set<String> getExternalFiles(TreeLogger logger, LinkerContext context) {
		HashSet<String> set = new HashSet<String>();
		SortedSet<ConfigurationProperty> properties = context.getConfigurationProperties();
		for (ConfigurationProperty configurationProperty : properties) {
			String name = configurationProperty.getName();
			if (EXTERNAL_FILES_CONFIGURATION_PROPERTY_NAME.equals(name)) {
				for (String value : configurationProperty.getValues()) {
					set.add(value);
				}
			}
		}

		return set;
	}

	protected EmittedArtifact createPermutationMap(TreeLogger logger, Map<String, Set<BindingProperty>> map) throws UnableToCompleteException {

		try {
			String string = xmlPermutationProvider.serializeMap(map);
			return emitString(logger, string, MANIFEST_MAP_FILE_NAME);
		} catch (XMLPermutationProviderException e) {
			logger.log(Type.ERROR, "can not build manifest map", e);
			throw new UnableToCompleteException();
		}

	}

	protected Map<String, Set<BindingProperty>> buildPermutationMap(TreeLogger logger, LinkerContext context, ArtifactSet artifacts) throws UnableToCompleteException {

		HashMap<String, Set<BindingProperty>> map = new HashMap<String, Set<BindingProperty>>();

		for (SelectionInformation result : artifacts.find(SelectionInformation.class)) {
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
