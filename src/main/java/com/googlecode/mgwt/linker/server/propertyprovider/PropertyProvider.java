package com.googlecode.mgwt.linker.server.propertyprovider;

import javax.servlet.http.HttpServletRequest;

public interface PropertyProvider {

	public String getPropertyName();

	public String getPropertyValue(HttpServletRequest req) throws PropertyProviderException;
}
