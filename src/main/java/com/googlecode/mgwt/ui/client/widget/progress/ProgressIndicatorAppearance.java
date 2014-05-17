package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface ProgressIndicatorAppearance {
  /**
   * The CSS resource for the mgwt progress indicator.
   */
  interface ProgressIndicatorCss extends MGWTCssResource {
    @ClassName("mgwt-ProgressIndicator")
    String progressIndicator();
    @ClassName("mgwt-ProgressIndicator-child")
    String child();
  }

  ProgressIndicatorCss css();

  UiBinder<? extends Element, ProgressIndicator> uiBinder();
}
