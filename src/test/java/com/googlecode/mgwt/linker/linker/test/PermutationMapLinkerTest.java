package com.googlecode.mgwt.linker.linker.test;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.ConfigurationProperty;
import com.google.gwt.core.ext.linker.SyntheticArtifact;
import com.google.gwt.core.ext.linker.impl.SelectionInformation;
import com.google.gwt.dev.util.log.PrintWriterTreeLogger;

import com.googlecode.mgwt.linker.linker.PermutationArtifact;
import com.googlecode.mgwt.linker.linker.PermutationMapLinker;
import com.googlecode.mgwt.linker.server.BindingProperty;

import junit.framework.Assert;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

@RunWith(MockitoJUnitRunner.class)
public class PermutationMapLinkerTest {

  // Values in TreeSet must implement Comparable, Configuration Property (or
  // StandardConfigurationProperty) does not.
  private static interface MyConfigurationProperty extends ConfigurationProperty,
      Comparable<ConfigurationProperty> {
  }

  @Mock LinkerContext linkerContext;

  private PermutationMapLinker permutationMapLinker;

  @Before
  public void setUp() {
    when(linkerContext.getModuleName()).thenReturn("strong");
    permutationMapLinker = new PermutationMapLinker();
  }

  @Test
  public void testGetDescription() {
    // just for code coverage duh
    permutationMapLinker.getDescription();
  }

  @Test
  public void testLinkWithOnePermutation() throws UnableToCompleteException,
      UnsupportedEncodingException {

    ArtifactSet artifactSet = new ArtifactSet();
    TreeMap<String, String> map = new TreeMap<String, String>();
    map.put("prop1", "v1");
    map.put("prop2", "v2");
    SelectionInformation selectionInformation = new SelectionInformation("strong", 0, map);
    artifactSet.add(selectionInformation);

    // put in some files
    SyntheticArtifact a1 =
        new SyntheticArtifact(PermutationMapLinker.class, "1.test", "bla".getBytes("UTF-8"));
    artifactSet.add(a1);
    SyntheticArtifact a2 =
        new SyntheticArtifact(PermutationMapLinker.class, "2.test", "bla1".getBytes("UTF-8"));
    artifactSet.add(a2);

    ArtifactSet linkedSet = permutationMapLinker.link(null, linkerContext, artifactSet, true);

    SortedSet<PermutationArtifact> pas = linkedSet.find(PermutationArtifact.class);

    Assert.assertEquals(1, pas.size());

    PermutationArtifact permutationArtifact = pas.iterator().next();

    // is the permutation name okay?
    Assert.assertEquals("strong", permutationArtifact.getPermutationName());

    // are the bindind properties okay?
    Set<BindingProperty> bindingProperties = permutationArtifact.getBindingProperties();
    Assert.assertEquals(2, bindingProperties.size());
    Assert.assertTrue(bindingProperties.contains(new BindingProperty("prop1", "v1")));
    Assert.assertTrue(bindingProperties.contains(new BindingProperty("prop2", "v2")));

    // are the files okay?
    Set<String> permutationFiles = permutationArtifact.getPermutationFiles();

    Assert.assertEquals(2, permutationFiles.size());

    Assert.assertTrue(permutationFiles.contains("strong/2.test"));
    Assert.assertTrue(permutationFiles.contains("strong/1.test"));

  }

  @Test
  public void testLinkWithOnePermutationFail() throws UnsupportedEncodingException {

    ArtifactSet artifactSet = new ArtifactSet();
    TreeMap<String, String> map = new TreeMap<String, String>();
    map.put("prop1", "v1");
    map.put("prop2", "v2");
    SelectionInformation selectionInformation = new SelectionInformation("strong", 0, map);
    artifactSet.add(selectionInformation);
    SelectionInformation selectionInformation1 = new SelectionInformation("strong1", 0, map);
    artifactSet.add(selectionInformation1);

    try {
      permutationMapLinker.link(new PrintWriterTreeLogger(), linkerContext, artifactSet, true);
      Assert.fail("expected exception did not occur");
    } catch (UnableToCompleteException e) {

    }
  }

  @Test
  public void testLinkWithAllPermutations() throws UnableToCompleteException, IOException {

    ArtifactSet artifactSet = new ArtifactSet();
    TreeMap<String, String> map = new TreeMap<String, String>();
    map.put("prop1", "v1");
    map.put("prop2", "v2");
    SelectionInformation selectionInformation = new SelectionInformation("perm1", 0, map);
    artifactSet.add(selectionInformation);

    TreeMap<String, String> map1 = new TreeMap<String, String>();
    map1.put("prop3", "v3");
    map1.put("prop4", "v4");
    selectionInformation = new SelectionInformation("perm2", 0, map1);
    artifactSet.add(selectionInformation);
    // put in some files
    SyntheticArtifact a1 =
        new SyntheticArtifact(PermutationMapLinker.class, "1.test", "bla".getBytes("UTF-8"));
    artifactSet.add(a1);
    SyntheticArtifact a2 =
        new SyntheticArtifact(PermutationMapLinker.class, "2.test", "bla1".getBytes("UTF-8"));
    artifactSet.add(a2);

    ConfigurationProperty standardConfigurationProperty =
        mock(MyConfigurationProperty.class);

    when(standardConfigurationProperty.getValues()).thenReturn(Arrays.asList("index.html"));
    when(standardConfigurationProperty.getName()).thenReturn(
        PermutationMapLinker.EXTERNAL_FILES_CONFIGURATION_PROPERTY_NAME);

    TreeSet<ConfigurationProperty> set = new TreeSet<ConfigurationProperty>();
    set.add(standardConfigurationProperty);

    when(linkerContext.getConfigurationProperties()).thenReturn(set);

    // lets put in two permutationartifacts
    HashSet<String> files = new HashSet<String>();
    files.add("perm1_file1");
    files.add("perm1_file2");
    HashSet<BindingProperty> bpSet = new HashSet<BindingProperty>();
    bpSet.add(new BindingProperty("sel1", "1"));

    artifactSet.add(new PermutationArtifact(PermutationMapLinker.class, "perm1", files, bpSet));

    files = new HashSet<String>();
    files.add("perm2_file1");
    files.add("perm2_file2");
    bpSet = new HashSet<BindingProperty>();
    bpSet.add(new BindingProperty("sel1", "2"));

    artifactSet.add(new PermutationArtifact(PermutationMapLinker.class, "perm2", files, bpSet));

    ArtifactSet linkedSet = permutationMapLinker.link(null, linkerContext, artifactSet, false);

    SortedSet<SyntheticArtifact> pas = linkedSet.find(SyntheticArtifact.class);

    Assert.assertEquals(7, pas.size());

    // manifest for first permutation
    SyntheticArtifact artifact =
        getArtifact("perm1" + PermutationMapLinker.PERMUTATION_MANIFEST_FILE_ENDING, pas);
    Assert.assertNotNull(artifact);
    InputStream contents = artifact.getContents(null);
    // test some things on artifact...
    StringWriter writer = new StringWriter();
    IOUtils.copy(contents, writer, "UTF-8");
    String theString = writer.toString();
    Assert.assertTrue(theString.startsWith("CACHE MANIFEST"));

    Assert.assertTrue(theString.contains("perm1_file2"));
    Assert.assertTrue(theString.contains("strong/1.test"));
    Assert.assertTrue(theString.contains("perm1_file1"));
    Assert.assertTrue(theString.contains("strong/2.test"));
    Assert.assertTrue(theString.contains("index.html"));
    Assert.assertTrue(!theString.contains("perm2_file2"));
    Assert.assertTrue(!theString.contains("perm2_file1"));

    // manifest for second permutation
    artifact = getArtifact("perm2" + PermutationMapLinker.PERMUTATION_MANIFEST_FILE_ENDING, pas);
    Assert.assertNotNull(artifact);
    contents = artifact.getContents(null);
    // test some things on artifact...
    writer = new StringWriter();
    IOUtils.copy(contents, writer, "UTF-8");
    theString = writer.toString();
    Assert.assertTrue(theString.startsWith("CACHE MANIFEST"));
    Assert.assertTrue(theString.contains("perm2_file2"));
    Assert.assertTrue(theString.contains("strong/1.test"));
    Assert.assertTrue(theString.contains("perm2_file1"));
    Assert.assertTrue(theString.contains("strong/2.test"));
    Assert.assertTrue(theString.contains("index.html"));
    Assert.assertTrue(!theString.contains("perm1_file2"));
    Assert.assertTrue(!theString.contains("perm1_file1"));

    // manifest map file
    artifact = getArtifact(PermutationMapLinker.MANIFEST_MAP_FILE_NAME, pas);
    Assert.assertNotNull(artifact);
    contents = artifact.getContents(null);
    // test some things on artifact...
    writer = new StringWriter();
    IOUtils.copy(contents, writer, "UTF-8");
    theString = writer.toString();
    Assert.assertTrue(theString.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    Assert.assertTrue(theString.contains("<permutation name=\"perm1\">"));
    Assert.assertTrue(theString.contains("<prop2>v2</prop2>"));
    Assert.assertTrue(theString.contains("<prop1>v1</prop1>"));
    Assert.assertTrue(theString.contains("<permutation name=\"perm2\">"));
    Assert.assertTrue(theString.contains("<prop3>v3</prop3>"));
    Assert.assertTrue(theString.contains("<prop4>v4</prop4>"));

    artifact = getArtifact("perm1" + PermutationMapLinker.PERMUTATION_FILE_ENDING, pas);
    Assert.assertNotNull(artifact);
    // test some things on artifact...

  }

  @Test
  public void testHostedMode() throws UnableToCompleteException, IOException {
    ArtifactSet artifactSet = new ArtifactSet();
    ArtifactSet linkedSet = permutationMapLinker.link(null, linkerContext, artifactSet, false);
    Assert.assertEquals(0, linkedSet.size());
  }

  private SyntheticArtifact getArtifact(String path, SortedSet<SyntheticArtifact> pas) {
    for (SyntheticArtifact syntheticArtifact : pas) {
      String partialPath = syntheticArtifact.getPartialPath();
      if ((path).equals(partialPath))
        return syntheticArtifact;
    }
    return null;

  }
}
