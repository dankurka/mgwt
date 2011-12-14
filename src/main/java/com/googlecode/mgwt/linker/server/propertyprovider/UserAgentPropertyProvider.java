package com.googlecode.mgwt.linker.server.propertyprovider;

import javax.servlet.http.HttpServletRequest;

public class UserAgentPropertyProvider extends PropertyProviderBaseImpl implements PropertyProvider {

	@Override
	public String getPropertyValue(HttpServletRequest req) throws PropertyProviderException {

		String userAgent = getUserAgent(req);

		if (userAgent.contains("opera")) {
			return "opera";
		}

		if (userAgent.contains("safari")) {
			return "safari";
		}

		if (userAgent.contains("gecko")) {
			return "gecko1_8";
		}

		throw new PropertyProviderException("Can not find user agent property for userAgent: '" + userAgent + "'");

	}

	@Override
	public String getPropertyName() {
		return "user.agent";
	}

}
