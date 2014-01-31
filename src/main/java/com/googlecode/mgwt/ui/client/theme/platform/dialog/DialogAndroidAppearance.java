package com.googlecode.mgwt.ui.client.theme.platform.dialog;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.dialog.DialogAbstractAppearance;

public class DialogAndroidAppearance extends DialogAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.cssPanel().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"dialog-button-base.css", "dialog-button-android.css"})
    DialogButtonCss css();
    
    @Source({"dialog-base.css", "dialog-android.css"})
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
