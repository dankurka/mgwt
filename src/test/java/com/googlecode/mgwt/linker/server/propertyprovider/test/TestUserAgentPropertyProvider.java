package com.googlecode.mgwt.linker.server.propertyprovider.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProviderException;
import com.googlecode.mgwt.linker.server.propertyprovider.UserAgentPropertyProvider;

public class TestUserAgentPropertyProvider {

	private UserAgentPropertyProvider provider;

	@Before
	public void setUp() throws Exception {
		provider = new UserAgentPropertyProvider();
	}

	@Test
	public void testGetPropertyValue() {
		Assert.assertEquals("user.agent", provider.getPropertyName());
	}

	@Test
	public void testGetPropertyNameBlackberry() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.BLACKBERRY_USER_AGENT);
		Assert.assertEquals("safari", provider.getPropertyValue(mockServletRequest));
	}

	@Test
	public void testGetPropertyNameChrome() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.DESKTOP_USER_AGENT_CHROME);
		Assert.assertEquals("safari", provider.getPropertyValue(mockServletRequest));
	}

	@Test
	public void testGetPropertyNameFireFox() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.DESKTOP_USER_AGENT_FIREFOX);
		Assert.assertEquals("gecko1_8", provider.getPropertyValue(mockServletRequest));
	}

	@Test
	public void testGetPropertyNameOpera() throws PropertyProviderException {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.DESKTOP_OPERA);
		Assert.assertEquals("opera", provider.getPropertyValue(mockServletRequest));
	}

	@Test
	public void testGetPropertyNameIE9() {
		MockServletRequest mockServletRequest = new MockServletRequest();
		mockServletRequest.setUserAgent(UserAgents.DESKTOP_IE9);
		try {
			provider.getPropertyValue(mockServletRequest);
			Assert.fail("expected exception did not occur");
		} catch (PropertyProviderException e) {

		}
	}

}
