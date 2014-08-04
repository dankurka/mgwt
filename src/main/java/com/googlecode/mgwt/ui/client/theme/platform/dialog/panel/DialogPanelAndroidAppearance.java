package com.googlecode.mgwt.ui.client.theme.platform.dialog.panel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.dialog.panel.DialogPanelAbstractAppearance;

public class DialogPanelAndroidAppearance extends DialogPanelAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.cssPanel().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"com/googlecode/mgwt/ui/client/widget/dialog/panel/dialog-button.css", "dialog-button-android.css"})
    DialogButtonCss css();

    @Source({"com/googlecode/mgwt/ui/client/widget/dialog/panel/dialog.css", "dialog-android.css"})
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
