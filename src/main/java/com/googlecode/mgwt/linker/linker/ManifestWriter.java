package com.googlecode.mgwt.linker.linker;

import java.util.Date;
import java.util.Set;

public class ManifestWriter {
	public String writeManifest(Set<String> staticResources, Set<String> cacheResources) {
		if (staticResources == null)
			throw new IllegalArgumentException("staticResources can not be null");
		if (cacheResources == null)
			throw new IllegalArgumentException("cacheResources can not be null");

		StringBuilder sb = new StringBuilder();
		sb.append("CACHE MANIFEST\n");
		//build something unique so that the manifest file changes on recompile
		sb.append("# Unique id #" + (new Date()).getTime() + "." + Math.random() + "\n");
		sb.append("\n");
		sb.append("CACHE:\n");
		sb.append("# Static app files\n");
		for (String resources : staticResources) {
			sb.append(resources + "\n");
		}

		sb.append("\n# GWT compiled files\n");
		for (String resources : cacheResources) {
			sb.append(resources + "\n");
		}

		sb.append("\n\n");
		sb.append("# All other resources require the client to be online.\n");
		sb.append("NETWORK:\n");
		sb.append("*\n");
		return sb.toString();
	}
}
