/*
 * Copyright 2014 Katharina Fahnenbruck
 */
package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface ProgressSpinnerAppearance {
  /**
   * The CSS resource for the mgwt progress spinner.
   */
  interface ProgressSpinnerCss extends MGWTCssResource {
    @ClassName("mgwt-ProgressSpinner")
    String progressSpinner();
  }

  ProgressSpinnerCss css();

  UiBinder<? extends Element, ProgressSpinner> uiBinder();
}