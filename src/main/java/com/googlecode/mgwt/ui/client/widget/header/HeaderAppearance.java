package com.googlecode.mgwt.ui.client.widget.header;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBaseAppearance;

public interface HeaderAppearance extends ButtonBaseAppearance {

  interface HeaderButtonCss extends MGWTCssResource, ButtonBaseCss {
    @ClassName("mgwt-HeaderButton")
    public String button();
    @Override
    @ClassName("mgwt-HeaderButton-active")
    public String active();

    @ClassName("mgwt-HeaderButton-back")
    public String back();

    @ClassName("mgwt-HeaderButton-forward")
    public String forward();

    @ClassName("mgwt-HeaderButton-round")
    public String round();

    @ClassName("mgwt-HeaderButton-text")
    public String buttonText();

    @ClassName("mgwt-HeaderButton-border-container")
    public String buttonBorderContainer();

    @ClassName("mgwt-HeaderButton-border-content")
    public String buttonBorderContent();
  }

  interface HeaderPanelCss extends MGWTCssResource {
    @ClassName("mgwt-HeaderPanel")
    String panel();

    @ClassName("mgwt-HeaderPanel-left")
    String left();

    @ClassName("mgwt-HeaderPanel-center")
    String center();

    @ClassName("mgwt-HeaderPanel-right")
    String right();
  }

  @Override
  HeaderButtonCss css();

  @Override
  UiBinder<? extends Element, HeaderButton> uiBinder();

  UiBinder<Widget, HeaderPanel> panelBinder();

  HeaderPanelCss cssPanel();
}
