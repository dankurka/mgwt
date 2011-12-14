package com.googlecode.mgwt.linker.server.propertyprovider;

import javax.servlet.http.Cookie;
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
			String value = getRetinaCookieValue(req);
			if (value == null) {
				return "iphone_undefined";
			}

			if ("0".equals(value)) {
				return "iphone";
			}

			if ("1".equals(value)) {
				return "retina";
			}

		}

		if (userAgent.contains("blackberry")) {
			return "blackberry";
		}

		return "desktop";

	}

	public String getRetinaCookieValue(HttpServletRequest req) {

		Cookie[] cookies = req.getCookies();
		if (cookies == null)
			return null;

		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if ("mgwt_ios_retina".equals(cookie.getName()))
				return (cookie.getValue());
		}
		return null;
	}
}
