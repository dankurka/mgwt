package com.googlecode.mgwt.ui.client.widget.panel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class PanelDefaultAppearance extends PanelAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"panel.css"})
    PanelCss css();
  }
  
  @Override
  public PanelCss css() {
    return Resources.INSTANCE.css();
  }
}
