package com.googlecode.mgwt.linker.linker;

import java.util.Set;

import com.google.gwt.core.ext.Linker;
import com.google.gwt.core.ext.linker.Artifact;
import com.google.gwt.core.ext.linker.Transferable;

@Transferable
public class PermutationArtifact extends Artifact<PermutationArtifact>{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2097933260935878782L;
	private final Set<String> permutationFiles;
	private final String permutationName;

	public PermutationArtifact(Class<? extends Linker> linker, String permutationName, Set<String> permutationFiles) {
		super(linker);
		this.permutationName = permutationName;
		this.permutationFiles = permutationFiles;
	}

	@Override
	public int hashCode() {
		return permutationFiles.hashCode();
	}

	@Override
	protected int compareToComparableArtifact(PermutationArtifact o) {
		return permutationName.compareTo(o.permutationName);
	}

	@Override
	protected Class<PermutationArtifact> getComparableArtifactType() {
		return PermutationArtifact.class;
	}
	
	public Set<String> getPermutationFiles() {
		return permutationFiles;
	}
	
	public String getPermutationName() {
		return permutationName;
	}

}
