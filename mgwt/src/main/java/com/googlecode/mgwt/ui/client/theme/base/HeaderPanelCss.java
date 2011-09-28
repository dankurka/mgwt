package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;

public interface HeaderPanelCss extends CssResource {
	@ClassName("mgwt-HeaderPanel")
	String headerPanel();

	@ClassName("mgwt-HeaderPanel-left")
	String left();

	@ClassName("mgwt-HeaderPanel-center")
	String center();

	@ClassName("mgwt-HeaderPanel-right")
	String right();
}
