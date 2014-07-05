package com.googlecode.mgwt.ui.client.theme.platform.header;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.header.HeaderAbstractAppearance;

public class HeaderIOSAppearance extends HeaderAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.cssPanel().ensureInjected();
    Resources.INSTANCE.cssTitle().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({
        "com/googlecode/mgwt/ui/client/widget/header/header-button.css",
 "header-button-ios.css"})
    HeaderButtonCss css();

    @Source({"com/googlecode/mgwt/ui/client/widget/header/header.css", "header-ios.css"})
    HeaderPanelCss cssPanel();

    @Source({"com/googlecode/mgwt/ui/client/widget/header/header-title.css", "header-title-ios.css"})
    HeaderTitleCss cssTitle();
  }

  @Override
  public HeaderButtonCss css() {
    return Resources.INSTANCE.css();
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
