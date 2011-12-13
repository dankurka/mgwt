package com.googlecode.mgwt.linker.server;

import javax.servlet.http.HttpServletRequest;

public class MobileUserAgentProvider extends PropertyProviderBaseImpl {

	@Override
	public String getPropertyName() {
		return "mobile.user.agent";
	}

	@Override
	public String getPropertyValue(HttpServletRequest req) throws PropertyProviderException {
		String ua = getUserAgent(req);

		if (ua.contains("android")) {
			return "mobilesafari";
		}
		if (ua.contains("iphone")) {
			return "mobilesafari";
		}
		if (ua.contains("ipad")) {
			return "mobilesafari";
		}
		if (ua.contains("blackberry")) {
			return "not_mobile";
		}
		return "not_mobile";
	}
}
