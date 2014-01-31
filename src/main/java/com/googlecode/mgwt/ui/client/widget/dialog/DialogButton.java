package com.googlecode.mgwt.ui.client.widget.dialog;

import com.googlecode.mgwt.ui.client.widget.button.ButtonBase;

public class DialogButton extends ButtonBase {
  private DialogAppearance appearance;

  public DialogButton(DialogAppearance appearance, String text) {
    super(appearance);
    this.appearance = appearance;
    setElement(appearance.uiBinder().createAndBindUi(this));
    setText(text);
  }

  public void setCancel(boolean cancel) {
    removeStyleNames();
    if (cancel) {
      addStyleName(appearance.css().cancelbutton());
    }
  }
  
  public void setOK(boolean ok) {
    removeStyleNames();
    if (ok) {
      addStyleName(appearance.css().okbutton());
    }
  }

  private void removeStyleNames() {
    removeStyleName(appearance.css().cancelbutton());
    removeStyleName(appearance.css().okbutton());
  }
}
