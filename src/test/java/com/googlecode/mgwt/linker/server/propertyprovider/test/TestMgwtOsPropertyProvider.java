package com.googlecode.mgwt.linker.server.propertyprovider.test;

import javax.servlet.http.Cookie;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.mgwt.linker.server.propertyprovider.MgwtOsPropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProviderException;

public class TestMgwtOsPropertyProvider {

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
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.IPHONE_IOS5_USER_AGENT);
		mockServletRequest.setCookies(new Cookie[0]);

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("iphone_undefined", propertyValue);

	}

	@Test
	public void testGetPropertyValueIphoneScreenCookieRetina() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.IPHONE_IOS5_USER_AGENT);
		Cookie[] cookies = new Cookie[1];
		cookies[0] = new Cookie("mgwt_ios_retina", "1");
		mockServletRequest.setCookies(cookies);
		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("retina", propertyValue);

	}

	@Test
	public void testGetPropertyValueIphoneScreenCookieNonRetina() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.IPHONE_IOS5_USER_AGENT);
		Cookie[] cookies = new Cookie[1];
		cookies[0] = new Cookie("mgwt_ios_retina", "0");
		mockServletRequest.setCookies(cookies);
		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("iphone", propertyValue);

	}

	@Test
	public void testGetPropertyValueIpad() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.IPAD_IOS5_USER_AGENT);

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("ipad", propertyValue);

	}

	@Test
	public void testGetPropertyValueAndroid() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.ANDROID_PHONE_2x_USER_AGENT);

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("android", propertyValue);

	}

	@Test
	public void testGetPropertyValueAndroidTablet() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.ANDROID_TABLET_USER_AGENT);

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("android_tablet", propertyValue);

	}

	@Test
	public void testGetPropertyValueDesktopChrome() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.DESKTOP_USER_AGENT_CHROME);

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("desktop", propertyValue);

	}

	@Test
	public void testGetPropertyValueDesktopSafari() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.DESKTOP_USER_AGENT_SAFARI);

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("desktop", propertyValue);

	}

	@Test
	public void testGetPropertyValueDesktopFirefox() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.DESKTOP_USER_AGENT_FIREFOX);

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("desktop", propertyValue);

	}

	@Test
	public void testGetPropertyValueBlackberry() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.BLACKBERRY_USER_AGENT);

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("blackberry", propertyValue);

	}

	@Test
	public void testGetPropertyValueDesktopAsDefault() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent("");

		String propertyValue = provider.getPropertyValue(mockServletRequest);

		Assert.assertEquals("desktop", propertyValue);

	}

	@Test
	public void testGetRetinaCookieValueValid() {
		MockServletRequest mockServletRequest = new MockServletRequest();
		Cookie[] cookies = new Cookie[2];
		cookies[0] = new Cookie("test", "bla");
		cookies[1] = new Cookie("mgwt_ios_retina", "bla");

		mockServletRequest.setCookies(cookies);
		String retinaCookieValue = provider.getRetinaCookieValue(mockServletRequest);

		Assert.assertEquals("bla", retinaCookieValue);
	}

	@Test
	public void testGetRetinaCookieValueInvalid() {
		MockServletRequest mockServletRequest = new MockServletRequest();
		Cookie[] cookies = new Cookie[1];
		cookies[0] = new Cookie("test", "bla");

		mockServletRequest.setCookies(cookies);
		String retinaCookieValue = provider.getRetinaCookieValue(mockServletRequest);

		Assert.assertNull(retinaCookieValue);
	}

}
