package com.googlecode.mgwt.linker.linker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
import com.google.gwt.core.ext.linker.impl.SelectionInformation;
import com.googlecode.mgwt.linker.server.BindingProperty;

@LinkerOrder(LinkerOrder.Order.POST)
@Shardable
public class PermutationMapLinker extends AbstractLinker {

	private XMLPermutationProvider xmlPermutationProvider;

	public PermutationMapLinker() {
		xmlPermutationProvider = new XMLPermutationProvider();
	}

	public static final String MANIFEST_MAP = "manifest.map";

	@Override
	public String getDescription() {
		return "PermutationMapLinker";
	}

	@Override
	public ArtifactSet link(TreeLogger logger, LinkerContext context, ArtifactSet artifacts, boolean onePermutation) throws UnableToCompleteException {
		if (onePermutation) {
			Map<String, List<BindingProperty>> permutationMap = buildPermutationMap(logger, context, artifacts);
			Set<Entry<String, List<BindingProperty>>> entrySet = permutationMap.entrySet();

			//since we are in onePermutation there should be just one strongName
			//better make sure..

			if (permutationMap.size() != 1) {
				logger.log(Type.ERROR, "There should be only one permutation right now, but there were: '" + permutationMap.size() + "'");
				throw new UnableToCompleteException();
			}

			Entry<String, List<BindingProperty>> next = entrySet.iterator().next();
			String strongName = next.getKey();
			List<BindingProperty> bindingProperties = next.getValue();

			Set<String> artifactsForCompilation = getArtifactsForCompilation(logger, context, artifacts);
			Set<String> externalFiles = getExternalFiles(logger, context);
			Set<String> set = new HashSet<String>();

			set.addAll(artifactsForCompilation);
			set.addAll(externalFiles);

			String permInformation = xmlPermutationProvider.writePermutationInformation(strongName, bindingProperties, set);

			ArtifactSet toReturn = new ArtifactSet(artifacts);
			toReturn.add(emitString(logger, permInformation, strongName + ".perm.xml"));
			//TODO fix this!!
			artifactsForCompilation.add("showcase.nocache.js");
			String manifest = buildManiFest(logger, artifactsForCompilation, externalFiles, context);
			toReturn.add(emitString(logger, manifest, strongName + ".manifest"));

			return toReturn;
		}

		ArtifactSet toReturn = new ArtifactSet(artifacts);
		Map<String, List<BindingProperty>> map = buildPermutationMap(logger, context, artifacts);

		if (map.size() == 0) {
			//hosted mode
			return toReturn;
		}

		toReturn.add(createPermutationMap(logger, map));
		return toReturn;

	}

	protected Set<String> getArtifactsForCompilation(TreeLogger logger, LinkerContext context, ArtifactSet artifacts) {
		SortedSet<EmittedArtifact> artifactSet = artifacts.find(EmittedArtifact.class);

		Set<String> artifactNames = new HashSet<String>();

		for (EmittedArtifact artifact : artifactSet) {

			EmittedArtifact ea = (EmittedArtifact) artifact;
			String pathName = ea.getPartialPath();

			if (pathName.endsWith("symbolMap") || pathName.endsWith(".xml.gz") || pathName.endsWith("rpc.log") || pathName.endsWith("gwt.rpc") || pathName.endsWith("manifest.txt")
					|| pathName.startsWith("rpcPolicyManifest") || pathName.startsWith("soycReport")) {
				// skip these resources
				//TODO put in reg exp
			} else {
				artifactNames.add(pathName);
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

	protected ArtifactSet createManifest(TreeLogger logger, LinkerContext context, ArtifactSet artifacts) throws UnableToCompleteException {
		ArtifactSet toReturn = new ArtifactSet(artifacts);
		SortedSet<EmittedArtifact> artifactSet = artifacts.find(EmittedArtifact.class);

		Set<String> strongNames = getCompilationStrongNames(artifacts);

		Set<String> externalFiles = getExternalFiles(logger, context);

		for (String currentStrong : strongNames) {

			HashSet<String> cacheResources = new HashSet<String>();

			for (EmittedArtifact artifact : artifactSet) {

				EmittedArtifact ea = (EmittedArtifact) artifact;
				String pathName = ea.getPartialPath();

				if (pathName.endsWith("symbolMap") || pathName.endsWith(".xml.gz") || pathName.endsWith("rpc.log") || pathName.endsWith("gwt.rpc") || pathName.endsWith("manifest.txt")
						|| pathName.startsWith("rpcPolicyManifest") || pathName.startsWith("soycReport")) {
					// skip these resources
				} else {

					if (pathName.startsWith(currentStrong)) {
						//add it
						cacheResources.add(pathName);
						continue;
					}

					//is is a script from another permutation?
					if (isCompilationResultFromDifferentStrong(currentStrong, pathName, strongNames)) {
						//dont add
						continue;
					}

					cacheResources.add(pathName);
				}
			}

			String manifest = buildManiFest(logger, cacheResources, externalFiles, context);

			toReturn.add(emitString(logger, manifest, currentStrong + ".manifest"));

		}

		return toReturn;

	}

	protected String buildManiFest(TreeLogger logger, Set<String> cacheResources, Set<String> externalFiles, LinkerContext context) {

		StringBuilder sb = new StringBuilder();
		sb.append("CACHE MANIFEST\n");
		//build something unique so that the manifest file changes on recompile
		sb.append("# Unique id #" + (new Date()).getTime() + "." + Math.random() + "\n");
		sb.append("\n");
		sb.append("CACHE:\n");
		sb.append("# Static app files\n");
		for (String resources : externalFiles) {
			sb.append(resources + "\n");
		}

		sb.append("\n# Generated app files\n");
		for (String resources : cacheResources) {
			sb.append(context.getModuleName() + "/" + resources + "\n");
		}

		sb.append("\n\n");
		sb.append("# All other resources require the user to be online.\n");
		sb.append("NETWORK:\n");
		sb.append("*\n");
		return sb.toString();
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

	private boolean isCompilationResultFromDifferentStrong(String currentStrong, String pathName, Set<String> strongNames) {
		for (String otherPerm : strongNames) {
			if (pathName.startsWith(otherPerm) && !pathName.startsWith(currentStrong)) {
				return true;
			}
		}
		return false;
	}

	protected EmittedArtifact createPermutationMap(TreeLogger logger, Map<String, List<BindingProperty>> map) throws UnableToCompleteException {
		String string = xmlPermutationProvider.serializeMap(map);
		System.out.println(string);
		return emitString(logger, string, MANIFEST_MAP);

	}

	protected Map<String, List<BindingProperty>> buildPermutationMap(TreeLogger logger, LinkerContext context, ArtifactSet artifacts) throws UnableToCompleteException {

		SortedSet<SelectionInformation> compilationResults = artifacts.find(SelectionInformation.class);

		HashMap<String, List<BindingProperty>> map = new HashMap<String, List<BindingProperty>>();

		for (SelectionInformation result : compilationResults) {
			ArrayList<BindingProperty> list = new ArrayList<BindingProperty>();
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
