package com.googlecode.mgwt.linker.server.propertyprovider;

import javax.servlet.http.HttpServletRequest;

public abstract class PropertyProviderBaseImpl implements PropertyProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8038603215822600098L;

	protected String getUserAgent(HttpServletRequest req) {
		return req.getHeader("User-Agent").toLowerCase();
	}

}
