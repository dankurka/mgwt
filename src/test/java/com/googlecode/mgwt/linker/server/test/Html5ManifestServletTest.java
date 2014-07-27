package com.googlecode.mgwt.linker.server.test;

import com.googlecode.mgwt.linker.server.BindingProperty;
import com.googlecode.mgwt.linker.server.Html5ManifestServletBase;
import com.googlecode.mgwt.linker.server.MGWTHtml5ManifestServlet;
import com.googlecode.mgwt.linker.server.propertyprovider.test.UserAgents;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class Html5ManifestServletTest {

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

    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(UserAgents.BLACKBERRY_USER_AGENT);

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
  public void testGetModuleNameWithIllegalArgument() throws ServletException {
    try {
      servlet.getModuleName(null);
      Assert.fail("expected exception did not occur");
    } catch (IllegalArgumentException e) {

    }

  }

  @Test
  public void testGetModuleNameWithInvalidURI() throws ServletException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);

    Mockito.when(mockServletRequest.getServletPath()).thenReturn("test");

    ServletConfig servletConfig = Mockito.mock(ServletConfig.class);
    Mockito.when(servletConfig.getServletContext()).thenReturn(Mockito.mock(ServletContext.class));

    servlet.init(servletConfig);
    try {
      servlet.getModuleName(mockServletRequest);
      Assert.fail("expected exception did not occur");
    } catch (ServletException ignored) {
    }
  }

  @Test
  public void testGetModuleNameWithValidURI() throws ServletException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);

    Mockito.when(mockServletRequest.getServletPath()).thenReturn("/test.manifest");

    ServletConfig servletConfig = Mockito.mock(ServletConfig.class);
    Mockito.when(servletConfig.getServletContext()).thenReturn(Mockito.mock(ServletContext.class));

    servlet.init(servletConfig);

    String moduleName = servlet.getModuleName(mockServletRequest);
    Assert.assertEquals("test", moduleName);
  }

  @Test
  public void testGetModuleNameWithValidURIWithUnderScoreInit() throws ServletException {

    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);

    Mockito.when(mockServletRequest.getServletPath()).thenReturn("/test_bla.manifest");

    ServletConfig servletConfig = Mockito.mock(ServletConfig.class);
    Mockito.when(servletConfig.getServletContext()).thenReturn(Mockito.mock(ServletContext.class));

    servlet.init(servletConfig);

    String moduleName = servlet.getModuleName(mockServletRequest);
    Assert.assertEquals("test_bla", moduleName);

  }
}
