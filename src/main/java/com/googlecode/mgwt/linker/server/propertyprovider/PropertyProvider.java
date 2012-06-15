package com.googlecode.mgwt.linker.server.propertyprovider;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public interface PropertyProvider extends Serializable {

	public String getPropertyName();

	public String getPropertyValue(HttpServletRequest req) throws PropertyProviderException;
}
