package com.googlecode.mgwt.ui.client.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class MainResourceDefaultAppearance implements MainResourceAppearance {

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"main.css"})
    MainCss css();
  }

  @Override
  public MainCss css() {
    return Resources.INSTANCE.css();
  }
}
