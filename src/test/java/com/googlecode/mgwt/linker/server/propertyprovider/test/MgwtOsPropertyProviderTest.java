package com.googlecode.mgwt.linker.server.propertyprovider.test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.googlecode.mgwt.linker.server.propertyprovider.MgwtOsPropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProviderException;

public class MgwtOsPropertyProviderTest {

  private MgwtOsPropertyProvider provider;

  @Before
  public void setUp() throws Exception {

    provider = new MgwtOsPropertyProvider();
  }

  @Test
  public void testGetPropertyName() {
    Assert.assertEquals("mgwt.os", provider.getPropertyName());
  }

  @Test
  public void testGetPropertyValueIphoneWithoutScreenCookie() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.IPHONE_IOS5_USER_AGENT);
    Mockito.when(mockServletRequest.getCookies()).thenReturn(new Cookie[0]);

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("iphone_undefined", propertyValue);

  }

  @Test
  public void testGetPropertyValueIphoneScreenCookieRetina() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.IPHONE_IOS5_USER_AGENT);
    Mockito.when(mockServletRequest.getCookies()).thenReturn(
        new Cookie[] {new Cookie("mgwt_ios_retina", "1")});

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("retina", propertyValue);

  }

  @Test
  public void testGetPropertyValueIphoneScreenCookieNonRetina() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.IPHONE_IOS5_USER_AGENT);
    Mockito.when(mockServletRequest.getCookies()).thenReturn(
        new Cookie[] {new Cookie("mgwt_ios_retina", "0")});
    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("iphone", propertyValue);

  }

  @Test
  public void testGetPropertyValueIpad() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.IPAD_IOS5_USER_AGENT);

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("ipad_undefined", propertyValue);

  }

  @Test
  public void testGetPropertyValueIpadNonRetina() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.IPAD_IOS5_USER_AGENT);
    Mockito.when(mockServletRequest.getCookies()).thenReturn(
        new Cookie[] {new Cookie("mgwt_ios_retina", "0")});

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("ipad", propertyValue);

  }

  @Test
  public void testGetPropertyValueIpadRetina() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.IPAD_IOS5_USER_AGENT);
    Mockito.when(mockServletRequest.getCookies()).thenReturn(
        new Cookie[] {new Cookie("mgwt_ios_retina", "1")});

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("ipad_retina", propertyValue);
  }

  @Test
  public void testGetPropertyValueAndroid() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.ANDROID_PHONE_2x_USER_AGENT);

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("android", propertyValue);
  }

  @Test
  public void testGetPropertyValueAndroidTablet() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.ANDROID_TABLET_USER_AGENT);

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("android_tablet", propertyValue);
  }

  @Test
  public void testGetPropertyValueDesktopChrome() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.DESKTOP_USER_AGENT_CHROME);

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("desktop", propertyValue);
  }

  @Test
  public void testGetPropertyValueDesktopSafari() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.DESKTOP_USER_AGENT_SAFARI);

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("desktop", propertyValue);
  }

  @Test
  public void testGetPropertyValueDesktopFirefox() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.DESKTOP_USER_AGENT_FIREFOX);

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("desktop", propertyValue);
  }

  @Test
  public void testGetPropertyValueBlackberry() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn(
        UserAgents.BLACKBERRY_USER_AGENT);

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("blackberry", propertyValue);
  }

  @Test
  public void testGetPropertyValueDesktopAsDefault() throws PropertyProviderException {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getHeader("User-Agent")).thenReturn("");

    String propertyValue = provider.getPropertyValue(mockServletRequest);

    Assert.assertEquals("desktop", propertyValue);
  }

  @Test
  public void testGetRetinaCookieValueValid() {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getCookies()).thenReturn(
        new Cookie[] {new Cookie("test", "bla"), new Cookie("mgwt_ios_retina", "bla")});
    String retinaCookieValue = provider.getRetinaCookieValue(mockServletRequest);

    Assert.assertEquals("bla", retinaCookieValue);
  }

  @Test
  public void testGetRetinaCookieValueInvalid() {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);
    Mockito.when(mockServletRequest.getCookies()).thenReturn(
        new Cookie[] {new Cookie("test", "bla")});

    String retinaCookieValue = provider.getRetinaCookieValue(mockServletRequest);

    Assert.assertNull(retinaCookieValue);
  }

  @Test
  public void testGetRetinaCookieValueWithoutCookiesValues() {
    HttpServletRequest mockServletRequest = Mockito.mock(HttpServletRequest.class);

    String retinaCookieValue = provider.getRetinaCookieValue(mockServletRequest);

    Assert.assertNull(retinaCookieValue);
  }

}
