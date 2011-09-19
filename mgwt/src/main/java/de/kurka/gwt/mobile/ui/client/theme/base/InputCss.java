package de.kurka.gwt.mobile.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;

public interface InputCss extends CssResource {

	String cover();

	@ClassName("mgwt-TextBox")
	String textBox();

	@ClassName("mgwt-TextArea")
	String textArea();

	@ClassName("mgwt-PasswordTextBox")
	String passwordBox();

	String box();

}
