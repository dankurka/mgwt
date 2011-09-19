package de.kurka.gwt.mobile.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;

public interface ListCss extends CssResource {

	@ClassName("mgwt-List")
	public String listCss();

	public String round();

	public String group();

	public String selected();

	@ClassName("mgwt-List-Header")
	public String listHeader();

}
