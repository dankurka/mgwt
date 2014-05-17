package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface ProgressBarAppearance {
  /**
   * The CSS resource for the mgwt progressbar.
   */
  interface ProgressBarCss extends MGWTCssResource {
    @ClassName("mgwt-ProgressBar")
    public String progressBar();
  }

  ProgressBarCss css();

  UiBinder<? extends Element, ProgressBar> uiBinder();
}
