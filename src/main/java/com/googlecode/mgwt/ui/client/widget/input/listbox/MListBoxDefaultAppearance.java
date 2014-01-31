package com.googlecode.mgwt.ui.client.widget.input.listbox;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class MListBoxDefaultAppearance implements MListBoxAppearance{

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"mlistbox.css"})
    MListBoxCss css();
  }

  @Override
  public MListBoxCss css() {
    return Resources.INSTANCE.css();
  }

}
