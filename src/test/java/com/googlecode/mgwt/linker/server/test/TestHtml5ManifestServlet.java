package com.googlecode.mgwt.linker.server.test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.mgwt.linker.linker.PermutationMapLinker;
import com.googlecode.mgwt.linker.server.BindingProperty;
import com.googlecode.mgwt.linker.server.Html5ManifestServletBase;
import com.googlecode.mgwt.linker.server.MGWTHtml5ManifestServlet;
import com.googlecode.mgwt.linker.server.propertyprovider.test.UserAgents;

public class TestHtml5ManifestServlet {

	private Html5ManifestServletBase servlet;

	@Before
	public void before() {
		servlet = new MGWTHtml5ManifestServlet();
	}

	@Test
	public void testIsIphoneWithoutCookie() {

		HashSet<BindingProperty> set = new HashSet<BindingProperty>();

		set.add(new BindingProperty("mobile.user.agent", "not_mobile"));
		set.add(new BindingProperty("user.agent", "safari"));
		Assert.assertFalse(servlet.isIphoneWithoutCookie(set));
	}

	@Test
	public void testIsIphoneWithoutCookie1() {

		HashSet<BindingProperty> set = new HashSet<BindingProperty>();

		set.add(new BindingProperty("mobile.user.agent", "not_mobile"));
		set.add(new BindingProperty("user.agent", "safari"));
		set.add(new BindingProperty("mgwt.os", "blackberry"));
		Assert.assertFalse(servlet.isIphoneWithoutCookie(set));
	}

	@Test
	public void testIsIphoneWithoutCookie2() {

		HashSet<BindingProperty> set = new HashSet<BindingProperty>();

		set.add(new BindingProperty("mobile.user.agent", "not_mobile"));
		set.add(new BindingProperty("user.agent", "safari"));
		set.add(new BindingProperty("mgwt.os", "iphone_undefined"));
		Assert.assertTrue(servlet.isIphoneWithoutCookie(set));
	}

	@Test
	public void testCalculateBindinPropertiesForClient() throws ServletException {
		com.googlecode.mgwt.linker.server.propertyprovider.test.MockServletRequest mockServletRequest = new com.googlecode.mgwt.linker.server.propertyprovider.test.MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.BLACKBERRY_USER_AGENT);

		Set<BindingProperty> bindinPropertiesForClient = servlet.calculateBindinPropertiesForClient(mockServletRequest);

		Assert.assertEquals(3, bindinPropertiesForClient.size());

		Assert.assertTrue(bindinPropertiesForClient.contains(new BindingProperty("mobile.user.agent", "not_mobile")));
		Assert.assertTrue(bindinPropertiesForClient.contains(new BindingProperty("user.agent", "safari")));
		Assert.assertTrue(bindinPropertiesForClient.contains(new BindingProperty("mgwt.os", "blackberry")));

	}

	@Test
	public void testGetPermutationStrongNameWithInvalidArguments() throws ServletException {

		try {
			servlet.getPermutationStrongName(null, null, null);
			Assert.fail("Expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testGetPermutationStrongNameWithInvalidArguments1() throws ServletException {

		try {
			servlet.getPermutationStrongName(null, "asdf", null);
			Assert.fail("Expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testGetPermutationStrongNameWithInvalidArguments2() throws ServletException {

		try {
			servlet.getPermutationStrongName(null, null, new HashSet<BindingProperty>());
			Assert.fail("Expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testGetPermutationStrongName() throws ServletException, FileNotFoundException {

		ServletConfig config = new MockServletConfig() {

			@Override
			public ServletContext getServletContext() {
				return new MockServletContext() {
					@Override
					public String getRealPath(String arg0) {
						Assert.assertEquals("asdf/" + PermutationMapLinker.MANIFEST_MAP_FILE_NAME, arg0);
						URL url = TestHtml5ManifestServlet.class.getResource("/com/googlecode/mgwt/linker/server/test/resources/example.manifestmap.xml");
						try {
							return url.toURI().toString().substring("file:".length());
						} catch (URISyntaxException e) {
							Assert.fail("should not happen");
							return "asdf";
						}
					}
				};
			}
		};

		servlet.init(config);

		HashSet<BindingProperty> set = new HashSet<BindingProperty>();
		set.add(new BindingProperty("mgwt.os", "blackberry"));
		set.add(new BindingProperty("mobile.user.agent", "not_mobile"));
		set.add(new BindingProperty("user.agent", "safari"));
		String permutationStrongName = servlet.getPermutationStrongName("", "asdf", set);

		Assert.assertEquals("C83A451EFE8ADF0BDB46AEAAC44B0063", permutationStrongName);
	}

	@Test
	public void testGetPermutationStrongName1() throws ServletException, FileNotFoundException {

		ServletConfig config = new MockServletConfig() {

			@Override
			public ServletContext getServletContext() {
				return new MockServletContext() {
					@Override
					public String getRealPath(String arg0) {
						Assert.assertEquals("asdf/" + PermutationMapLinker.MANIFEST_MAP_FILE_NAME, arg0);
						URL url = TestHtml5ManifestServlet.class.getResource("/com/googlecode/mgwt/linker/server/test/resources/example.manifestmap.xml");
						try {
							return url.toURI().toString().substring("file:".length());
						} catch (URISyntaxException e) {
							Assert.fail("should not happen");
							return "asdf";
						}
					}
				};
			}
		};

		servlet.init(config);

		HashSet<BindingProperty> set = new HashSet<BindingProperty>();

		set.add(new BindingProperty("mobile.user.agent", "not_mobile"));
		set.add(new BindingProperty("user.agent", "safari"));
		String permutationStrongName = servlet.getPermutationStrongName("", "asdf", set);

		Assert.assertEquals(null, permutationStrongName);
	}

	@Test
	public void testGetModuleNameWithIllegalArgument() throws ServletException {
		try {
			servlet.getModuleName(null);
			Assert.fail("expected exception did not occur");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	public void testGetModuleNameWithInvalidURI() throws ServletException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setRequestURI("test");

		servlet.init(new MockServletConfig());
		try {
			servlet.getModuleName(mockServletRequest);
			Assert.fail("expected exception did not occur");
		} catch (ServletException e) {

		}
	}

	@Test
	public void testGetModuleNameWithValidURI() throws ServletException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setRequestURI("/test.manifest");

		servlet.init(new MockServletConfig());

		String moduleName = servlet.getModuleName(mockServletRequest);
		Assert.assertEquals("test", moduleName);

	}
}
