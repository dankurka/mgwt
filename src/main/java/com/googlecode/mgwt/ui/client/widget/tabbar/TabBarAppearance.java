package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBaseAppearance;

public interface TabBarAppearance extends ButtonBaseAppearance {
  interface TabBarCss extends MGWTCssResource {

    @ClassName("mgwt-TabPanel")
    public String tabPanel();

    @ClassName("mgwt-TabPanel-container")
    public String tabPanelContainer();

    @ClassName("mgwt-TabBar")
    public String tabbar();

  }

  interface TabBarButtonCss extends ButtonBaseAppearance.ButtonBaseCss {
    @Override
    @ClassName("mgwt-TabBar-Button")
    public String button();

    @ClassName("mgwt-TabBar-Button-selected")
    public String selected();

    @Override
    @ClassName("mgwt-TabBar-Button-active")
    public String active();

    @ClassName("mgwt-TabBar-Button-icon")
    public String icon();

    @ClassName("mgwt-TabBar-Button-text")
    public String text();

    public String BUTTON_BACKGROUND_COLOR();

    public String BUTTON_BACKGROUND_SELECTED_COLOR();
  }

  @Override
  TabBarButtonCss css();

  @Override
  UiBinder<? extends Element, TabBarButtonBase> uiBinder();

  TabBarCss barCss();

  UiBinder<Widget, TabPanel> panelBinder();

  UiBinder<Widget, TabPanel.TabBar> barBinder();
}
