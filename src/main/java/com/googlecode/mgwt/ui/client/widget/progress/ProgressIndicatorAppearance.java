package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;

public interface ProgressIndicatorAppearance {
  /**
   * The CSS resource for the mgwt progress indicator.
   */
  interface ProgressIndicatorCss extends CssResource {
    @ClassName("mgwt-ProgressIndicator")
    String progressIndicator();
    @ClassName("mgwt-ProgressIndicator-child")
    String child();
  }

  ProgressIndicatorCss css();

  UiBinder<? extends Element, ProgressIndicator> uiBinder();
}
