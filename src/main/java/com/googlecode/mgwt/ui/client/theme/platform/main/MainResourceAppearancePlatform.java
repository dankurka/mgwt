package com.googlecode.mgwt.ui.client.theme.platform.main;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.resource.MainResourceAppearance;

public class MainResourceAppearancePlatform implements MainResourceAppearance {

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"main.css"})
    MainCss css();
  }

  @Override
  public MainCss css() {
    return Resources.INSTANCE.css();
  }

  @Override
  public UtilCss utilCss() {
    // TODO Auto-generated method stub
    return null;
  }
}
