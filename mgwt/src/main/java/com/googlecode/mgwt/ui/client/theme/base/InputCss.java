package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;

public interface InputCss extends CssResource {

	String cover();

	@ClassName("mgwt-TextBox")
	String textBox();

	@ClassName("mgwt-TextArea")
	String textArea();

	@ClassName("mgwt-PasswordTextBox")
	String passwordBox();

	@ClassName("mgwt-RadioButton")
	String radioButton();

	String box();

	String disabled();

}
