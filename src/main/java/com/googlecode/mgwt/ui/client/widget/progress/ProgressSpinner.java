/*
 * Copyright 2014 Katharina Fahnenbruck
 */
package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Widget;

public class ProgressSpinner extends Widget {
  private static final ProgressSpinnerAppearance DEFAULT_APPEARANCE = GWT
      .create(ProgressSpinnerAppearance.class);

  private final ProgressSpinnerAppearance appearance;

  public ProgressSpinner() {
    this(DEFAULT_APPEARANCE);
  }

  public ProgressSpinner(ProgressSpinnerAppearance appearance) {
    this.appearance = appearance;
    setElement(this.appearance.uiBinder().createAndBindUi(this));
  }

  @UiFactory
  protected ProgressSpinnerAppearance getAppearance() {
    return appearance;
  }
}
