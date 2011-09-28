package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;

public interface MSearchBoxCss extends CssResource {

	@ClassName("mgwt-SearchBox")
	public String searchBox();

	public String round();

	public String clear();

	@ClassName("clear-active")
	public String clearActive();

	public String input();
}
