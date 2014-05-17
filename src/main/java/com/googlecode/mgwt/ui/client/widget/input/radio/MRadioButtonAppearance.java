package com.googlecode.mgwt.ui.client.widget.input.radio;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface MRadioButtonAppearance {
  public interface MRadioButtonCss extends MGWTCssResource {
    @ClassName("mgwt-RadioButton-disabled")
    String disabled();
    @ClassName("mgwt-RadioButton")
    String radio();
    @ClassName("mgwt-RadioButton-label")
    String label();
    @ClassName("mgwt-RadioButton-input")
    String  input();
  }

  MRadioButtonCss css();

  UiBinder<? extends Element, MRadioButton> uiBinder();
}
