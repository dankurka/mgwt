package com.googlecode.mgwt.ui.client.widget.input;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class InputDefaultAppearance implements InputAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"input.css"})
    InputCss css();
  }

  @Override
  public InputCss css() {
    return Resources.INSTANCE.css();
  }
}
