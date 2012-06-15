package com.googlecode.mgwt.linker.linker.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.mgwt.linker.linker.ManifestWriter;

public class TestManifestWriter {

	private ManifestWriter writer;

	@Before
	public void setup() throws Exception {
		writer = new ManifestWriter();
	}

	@Test
	public void testWriteManifestTestException() {
		try {
			writer.writeManifest(null, null);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void testWriteManifestTestException1() {
		try {
			writer.writeManifest(new HashSet<String>(), null);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void testWriteManifestTestException2() {
		try {
			writer.writeManifest(null, new HashSet<String>());
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void testWriteEmptyManifest() {

		String manifest = writer.writeManifest(new HashSet<String>(), new HashSet<String>());

		List<String> lines = Arrays.asList(manifest.split("\\r?\\n"));

		Assert.assertEquals(12, lines.size());

		Assert.assertEquals("CACHE MANIFEST", lines.get(0));
		Assert.assertTrue(lines.get(1).startsWith("# Unique id #"));

		Assert.assertTrue(lines.contains("CACHE:"));
		Assert.assertTrue(lines.contains("NETWORK:"));
		int indexOf = lines.indexOf("NETWORK:");
		Assert.assertEquals("*", lines.get(indexOf + 1));

	}

	@Test
	public void testWriteSmallManifestWithOnlyStaticFiles() {

		HashSet<String> set = new HashSet<String>();
		set.add("a.stuff");
		set.add("b.stuff");

		String manifest = writer.writeManifest(set, new HashSet<String>());

		List<String> lines = Arrays.asList(manifest.split("\\r?\\n"));

		Assert.assertEquals(14, lines.size());

		Assert.assertEquals("CACHE MANIFEST", lines.get(0));
		Assert.assertTrue(lines.get(1).startsWith("# Unique id #"));

		Assert.assertTrue(lines.contains("CACHE:"));
		int indexOf2 = lines.indexOf("CACHE:");
		Assert.assertEquals("a.stuff", lines.get(indexOf2 + 2));
		Assert.assertEquals("b.stuff", lines.get(indexOf2 + 3));

		Assert.assertTrue(lines.contains("NETWORK:"));
		int indexOf = lines.indexOf("NETWORK:");
		Assert.assertEquals("*", lines.get(indexOf + 1));

	}

	@Test
	public void testWriteSmallManifestWithOnlyGWTFiles() {

		HashSet<String> set = new HashSet<String>();
		set.add("a.stuff");
		set.add("b.stuff");

		String manifest = writer.writeManifest(new HashSet<String>(), set);
		List<String> lines = Arrays.asList(manifest.split("\\r?\\n"));

		Assert.assertEquals(14, lines.size());

		Assert.assertEquals("CACHE MANIFEST", lines.get(0));
		Assert.assertTrue(lines.get(1).startsWith("# Unique id #"));

		Assert.assertTrue(lines.contains("CACHE:"));
		int indexOf2 = lines.indexOf("CACHE:");
		Assert.assertEquals("a.stuff", lines.get(indexOf2 + 4));
		Assert.assertEquals("b.stuff", lines.get(indexOf2 + 5));

		Assert.assertTrue(lines.contains("NETWORK:"));
		int indexOf = lines.indexOf("NETWORK:");
		Assert.assertEquals("*", lines.get(indexOf + 1));

	}

}
