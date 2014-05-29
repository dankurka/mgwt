package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class ButtonBarDefaultAppearance extends ButtonBarAbstractAppearance {

  static {
    Resources.INSTANCE.barCss().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"buttonbar.css"})
    ButtonBarCss barCss();
  }

  @Override
  public ButtonBarCss barCss() {
    return Resources.INSTANCE.barCss();
  }
}
