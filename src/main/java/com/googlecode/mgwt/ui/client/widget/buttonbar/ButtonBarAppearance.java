package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface ButtonBarAppearance {

  public interface ButtonBarCss extends MGWTCssResource {

    @ClassName("mgwt-ButtonBar")
    public String buttonBar();

    @ClassName("mgwt-ButtonBar-text")
    public String text();
  }

  ButtonBarCss barCss();

  UiBinder<? extends Widget, ButtonBar> barBinder();

  UiBinder<? extends Element, ButtonBarText> barText();
}
