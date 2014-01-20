package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;

public interface ProgressBarAppearance {
  /**
   * The CSS resource for the mgwt progressbar.
   */
  interface ProgressBarCss extends CssResource {
    @ClassName("mgwt-ProgressBar")
    public String progressBar();
  }

  ProgressBarCss css();

  UiBinder<? extends Element, ProgressBar> uiBinder();
}
