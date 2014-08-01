package com.googlecode.mgwt.ui.client.theme.platform.tabbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarAbstractAppearance;

public class TabBarIOSAppearance extends TabBarAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.barCss().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"tabbar-base.css", "tabbar-ios.css"})
    TabBarCss barCss();
    @Source({"tabbar-button-base.css", "tabbar-button-ios.css"})
    TabBarButtonCss css();
  }

  @Override
  public TabBarButtonCss css() {
    return Resources.INSTANCE.css();
  }

  @Override
  public TabBarCss barCss() {
    return Resources.INSTANCE.barCss();
  }
}
