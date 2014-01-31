package com.googlecode.mgwt.ui.client.widget.layout;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Panel;

public interface LayoutAppearance {
  public interface LayoutCss extends CssResource {

    @ClassName("mgwt-LayoutPanel")
    String layoutPanel();

    @ClassName("mgwt-RootLayoutPanel")
    String rootLayoutPanel();

    @ClassName("mgwt-LayoutPanel-flex")
    String flexible();

    @ClassName("mgwt-LayoutPanel-vertical-fill")
    String centerVertically();

    @ClassName("mgwt-LayoutPanel-horizontal-fill")
    String centerHorizontically();

    @ClassName("mgwt-LayoutPanel-fill-parent")
    String fillParent();
  }

  LayoutCss css();

  UiBinder<Panel, LayoutPanel> uiBinder();

}
