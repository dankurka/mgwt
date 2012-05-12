package com.googlecode.mgwt.linker.server;

import com.googlecode.mgwt.linker.server.propertyprovider.MgwtOsPropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.MobileUserAgentProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.PhoneGapPropertyProvider;
import com.googlecode.mgwt.linker.server.propertyprovider.UserAgentPropertyProvider;

public class MGWTPhonegapHtml5ManifestServlet extends Html5ManifestServletBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3480215265307651028L;

	public MGWTPhonegapHtml5ManifestServlet() {
		addPropertyProvider(new MgwtOsPropertyProvider());
		addPropertyProvider(new PhoneGapPropertyProvider());
		addPropertyProvider(new UserAgentPropertyProvider());
		addPropertyProvider(new MobileUserAgentProvider());

	}

}
