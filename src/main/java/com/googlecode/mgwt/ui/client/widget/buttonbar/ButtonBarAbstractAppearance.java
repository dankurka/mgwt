package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Panel;

public abstract class ButtonBarAbstractAppearance implements ButtonBarAppearance {

  @UiTemplate("ButtonBarButtonAbstractAppearance.ui.xml")
  interface BinderButton extends UiBinder<Element, ButtonBarButtonBase> {
  }
  
  @UiTemplate("ButtonBarAbstractAppearance.ui.xml")
  interface BinderBar extends UiBinder<Panel, ButtonBar> {
  }
  
  @UiTemplate("ButtonBarSpacerAbstractAppearance.ui.xml")
  interface BinderSpacer extends UiBinder<Element, ButtonBarSpacer> {
  }
  
  @UiTemplate("ButtonBarTextAbstractAppearance.ui.xml")
  interface BinderText extends UiBinder<Element, ButtonBarText> {
  }

  private static final BinderButton UI_BINDER_BUTTON = GWT.create(BinderButton.class);
  
  private static final BinderBar UI_BINDER_BAR = GWT.create(BinderBar.class);
  
  private static final BinderSpacer UI_BINDER_SPACER = GWT.create(BinderSpacer.class);
  
  private static final BinderText UI_BINDER_TEXT = GWT.create(BinderText.class);
  
  @Override
  public UiBinder<Element, ButtonBarButtonBase> uiBinder() {
    return UI_BINDER_BUTTON;
  }

  @Override
  public UiBinder<? extends Panel, ButtonBar> barBinder() {
    return UI_BINDER_BAR;
  }

  @Override
  public UiBinder<? extends Element, ButtonBarSpacer> barSpacer() {
    return UI_BINDER_SPACER;
  }

  @Override
  public UiBinder<? extends Element, ButtonBarText> barText() {
    return UI_BINDER_TEXT;
  }
}
