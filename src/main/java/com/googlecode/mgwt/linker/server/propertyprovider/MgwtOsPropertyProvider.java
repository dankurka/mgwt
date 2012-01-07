package com.googlecode.mgwt.linker.server.propertyprovider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.googlecode.mgwt.linker.server.BindingProperty;

public class MgwtOsPropertyProvider extends PropertyProviderBaseImpl {

	public static final BindingProperty iPhone = new BindingProperty("mgwt.os", "iphone");
	public static final BindingProperty retina = new BindingProperty("mgwt.os", "retina");
	public static final BindingProperty iPhone_undefined = new BindingProperty("mgwt.os", "iphone_undefined");

	@Override
	public String getPropertyName() {
		return "mgwt.os";
	}

	@Override
	public String getPropertyValue(HttpServletRequest req) throws PropertyProviderException {
		String userAgent = getUserAgent(req);

		// android
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
