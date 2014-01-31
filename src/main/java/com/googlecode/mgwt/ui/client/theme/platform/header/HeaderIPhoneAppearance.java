package com.googlecode.mgwt.ui.client.theme.platform.header;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.header.HeaderAbstractAppearance;

public class HeaderIPhoneAppearance extends HeaderAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.cssPanel().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"header-button-base.css", "header-button-iphone.css"})
    HeaderButtonCss css();
    
    @Source({"header-base.css", "header-iphone.css"})
    HeaderPanelCss cssPanel();
  }
  
  @Override
  public HeaderButtonCss css() {
    return Resources.INSTANCE.css();
  }

  @Override
  public HeaderPanelCss cssPanel() {
    return Resources.INSTANCE.cssPanel();
  }

}
