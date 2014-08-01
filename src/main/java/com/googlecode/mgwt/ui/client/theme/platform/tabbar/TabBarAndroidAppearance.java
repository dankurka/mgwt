package com.googlecode.mgwt.ui.client.theme.platform.tabbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarAbstractAppearance;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

public class TabBarAndroidAppearance extends TabBarAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.barCss().ensureInjected();
  }

  @UiTemplate("TabPanelAndroidAppearance.ui.xml")
  interface PanelBinder extends UiBinder<Widget, TabPanel> {
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"tabbar-base.css", "tabbar-android.css"})
    TabBarCss barCss();
    @Source({"tabbar-button-base.css", "tabbar-button-android.css"})
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
