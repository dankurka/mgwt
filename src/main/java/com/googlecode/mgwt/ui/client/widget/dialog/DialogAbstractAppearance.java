package com.googlecode.mgwt.ui.client.widget.dialog;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public abstract class DialogAbstractAppearance implements DialogAppearance {

  @UiTemplate("ButtonAbstractAppearance.ui.xml")
  interface Binder extends UiBinder<Element, DialogButton> {
  }

  private static final Binder UI_BINDER = GWT.create(Binder.class);
  
  @UiTemplate("DialogPanelAbstractAppearance.ui.xml")
  interface DialogBinder extends UiBinder<Widget, DialogPanel> {
  }

  private static final DialogBinder UI_DIALOG_BINDER = GWT.create(DialogBinder.class);

  @Override
  public UiBinder<Element, DialogButton> uiBinder() {
    return UI_BINDER;
  }

  @Override
  public UiBinder<Widget, DialogPanel> dialogBinder() {
    return UI_DIALOG_BINDER;
  }
}
