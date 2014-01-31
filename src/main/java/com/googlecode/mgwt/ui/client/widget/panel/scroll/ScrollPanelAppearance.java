package com.googlecode.mgwt.ui.client.widget.panel.scroll;

import com.google.gwt.resources.client.CssResource;

public interface ScrollPanelAppearance {
  public interface ScrollPanelCss extends CssResource {

    @ClassName("mgwt-ScrollPanel")
    String scrollPanel();

    @ClassName("mgwt-ScrollPanel-container")
    String container();

    @ClassName("mgwt-Scrollbar")
    String scrollBar();

    @ClassName("mgwt-Scrollbar-horizontal")
    String scrollBarHorizontal();

    @ClassName("mgwt-Scrollbar-vertical")
    String scrollBarVertical();

    @ClassName("mgwt-Scrollbar-Bar")
    String scrollBarBar();

  }
  
  ScrollPanelCss css();
}
