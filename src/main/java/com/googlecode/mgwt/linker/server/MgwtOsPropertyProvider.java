package com.googlecode.mgwt.linker.server;

import javax.servlet.http.HttpServletRequest;

public class MgwtOsPropertyProvider extends PropertyProviderBaseImpl {

	@Override
	public String getPropertyName() {
		return "mgwt.os";
	}

	@Override
	public String getPropertyValue(HttpServletRequest req) throws PropertyProviderException {
		String userAgent = getUserAgent(req);

		//android
		if (userAgent.contains("android")) {
			if (userAgent.contains("mobile")) {
				return "android";
			} else {
				return "android_tablet";
			}

		}

		if (userAgent.contains("ipad")) {
			return "ipad";
		}

		if (userAgent.contains("iphone")) {

			return "iphone";
		}

		if (userAgent.contains("blackberry")) {
			return "blackberry";
		}

		return "desktop";

	}
}
