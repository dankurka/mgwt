package com.googlecode.mgwt.ui.client.widget.panel.scroll;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface ScrollPanelAppearance {
  public interface ScrollPanelCss extends MGWTCssResource {

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
