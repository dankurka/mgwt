package com.googlecode.mgwt.ui.client.theme.platform.input;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.input.InputAppearance;

public class InputIOSAppearance implements InputAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Css extends InputCss {}

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"com/googlecode/mgwt/ui/client/widget/input/input.css", "input-ios.css"})
    Css css();
  }

  @Override
  public InputCss css() {
    return Resources.INSTANCE.css();
  }

}
