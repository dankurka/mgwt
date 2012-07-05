package com.googlecode.mgwt.ui.client.util.impl;

import com.google.gwt.user.client.Window;

public class SuperDevModeHelperOnDefaultImpl extends SuperDevModeHelperOnImpl{

	@Override
	protected String getServerUrl() {
		
		String protocol = Window.Location.getProtocol();
		String hostName = Window.Location.getHostName();
		
		return protocol + "//" +  hostName + ":9876/";
	}

}
