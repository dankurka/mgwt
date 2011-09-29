package com.googlecode.mgwt.ui.client.theme.base;


public interface HeaderCss extends ButtonBaseCss {

	@ClassName("mgwt-HeaderButton")
	public String headerButton();

	public String active();

	public String back();

	public String forward();

	public String round();

	@ClassName("mgwt-HeaderPanel")
	String headerPanel();

	@ClassName("mgwt-HeaderPanel-left")
	String left();

	@ClassName("mgwt-HeaderPanel-center")
	String center();

	@ClassName("mgwt-HeaderPanel-right")
	String right();

	//TODO remote this
	@ClassName("mgwt-DropDownMenu")
	String test();
}
