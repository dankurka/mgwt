package com.googlecode.mgwt.linker.server.propertyprovider.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.mgwt.linker.server.propertyprovider.MobileUserAgentProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProviderException;

public class TestMobileUserAgentPropertyProvider {

	private MobileUserAgentProvider provider;

	@Before
	public void setUp() throws Exception {
		provider = new MobileUserAgentProvider();
	}

	@Test
	public void testGetPropertyName() {
		Assert.assertEquals("mobile.user.agent", provider.getPropertyName());
	}

	@Test
	public void testGetPropertyValueIphone() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.IPHONE_IOS5_USER_AGENT);
		Assert.assertEquals("mobilesafari", provider.getPropertyValue(mockServletRequest));
	}

	@Test
	public void testGetPropertyValueIpad() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.IPAD_IOS5_USER_AGENT);
		Assert.assertEquals("mobilesafari", provider.getPropertyValue(mockServletRequest));
	}

	@Test
	public void testGetPropertyValueAndroid() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.ANDROID_PHONE_2x_USER_AGENT);
		Assert.assertEquals("mobilesafari", provider.getPropertyValue(mockServletRequest));
	}

	@Test
	public void testGetPropertyValueBlackberry() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.BLACKBERRY_USER_AGENT);
		Assert.assertEquals("not_mobile", provider.getPropertyValue(mockServletRequest));
	}

	@Test
	public void testGetPropertyValueDesktop() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.DESKTOP_USER_AGENT_CHROME);
		Assert.assertEquals("not_mobile", provider.getPropertyValue(mockServletRequest));
	}

}
