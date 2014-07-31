/*
 * Copyright 2014 Katharina Fahnenbruck
 */
package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;

public abstract class ProgressSpinnerAbstractAppearance implements
    ProgressSpinnerAppearance {

  @UiTemplate("ProgressSpinnerBaseAppearance.ui.xml")
  interface Binder extends UiBinder<Element, ProgressSpinner> {
  }

  private static final Binder UI_BINDER = GWT.create(Binder.class);

  @Override
  public UiBinder<? extends Element, ProgressSpinner> uiBinder() {
    return UI_BINDER;
  }
}

