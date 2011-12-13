package com.googlecode.mgwt.linker.server;

import javax.servlet.http.HttpServletRequest;

public abstract class PropertyProviderBaseImpl implements PropertyProvider {

	protected String getUserAgent(HttpServletRequest req) {
		return req.getHeader("User-Agent").toLowerCase();
	}

}
