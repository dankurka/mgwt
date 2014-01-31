package com.googlecode.mgwt.ui.client.theme.platform.input;

import com.google.gwt.resources.client.CssResource;

public interface InputAppearance {
  public interface InputCss extends CssResource {

    @ClassName("mgwt-TextBox")
    String textBox();

    @ClassName("mgwt-TextArea")
    String textArea();

    @ClassName("mgwt-PasswordTextBox")
    String passwordBox();

    @ClassName("mgwt-InputBox-box")
    String box();

    @ClassName("mgwt-InputBox-disabled")
    String disabled();
  }
  
  InputCss css();
}
