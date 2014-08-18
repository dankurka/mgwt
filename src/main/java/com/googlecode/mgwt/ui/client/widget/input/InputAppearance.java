package com.googlecode.mgwt.ui.client.widget.input;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface InputAppearance {
  public interface InputCss extends MGWTCssResource {

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

    @ClassName("mgwt-InputBox-invalid")
    String invalid();
  }

  InputCss css();
}
