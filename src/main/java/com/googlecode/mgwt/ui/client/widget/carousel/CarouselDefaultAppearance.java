package com.googlecode.mgwt.ui.client.widget.carousel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class CarouselDefaultAppearance implements CarouselAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"carousel.css"})
    CarouselCss css();
  }

  @Override
  public CarouselCss css() {
    return Resources.INSTANCE.css();
  }
}
