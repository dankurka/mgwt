package com.googlecode.mgwt.ui.client.theme.platform.header;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.header.HeaderAbstractAppearance;

public class HeaderAndroidAppearance extends HeaderAbstractAppearance {

  static {
    Resources.INSTANCE.cssPanel().ensureInjected();
  }

  interface CssPanel extends HeaderPanelCss {}
  interface CssTitle extends HeaderTitleCss {}

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"com/googlecode/mgwt/ui/client/widget/header/header.css", "header-android.css"})
    CssPanel cssPanel();

    @Source({
        "com/googlecode/mgwt/ui/client/widget/header/header-title.css", "header-title-android.css"})
    CssTitle cssTitle();
  }

  @Override
  public HeaderPanelCss cssPanel() {
    return Resources.INSTANCE.cssPanel();
  }

  @Override
  public HeaderTitleCss cssTitle() {
    return Resources.INSTANCE.cssTitle();
  }
}
