package com.googlecode.mgwt.ui.client.widget.header;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class HeaderDefaultAppearance extends HeaderAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.cssPanel().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"header-button.css"})
    HeaderButtonCss css();
    
    @Source({"header.css"})
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
