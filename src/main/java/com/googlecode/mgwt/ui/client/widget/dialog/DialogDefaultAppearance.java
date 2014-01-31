package com.googlecode.mgwt.ui.client.widget.dialog;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class DialogDefaultAppearance extends DialogAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.cssPanel().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"dialog-button.css"})
    DialogButtonCss css();
    
    @Source({"dialog.css"})
    DialogCss cssPanel();
  }

  @Override
  public DialogCss dialogCss() {
    return Resources.INSTANCE.cssPanel();
  }

  @Override
  public DialogButtonCss css() {
    return Resources.INSTANCE.css();
  }
}
