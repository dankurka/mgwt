package com.googlecode.mgwt.ui.client.theme.platform.input.listbox;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.input.listbox.MListBoxAppearance;

public class MListBoxAndroidAppearance implements MListBoxAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"mlistbox-base.css", "mlistbox-android.css"})
    MListBoxCss css();
  }

  @Override
  public MListBoxCss css() {
    return Resources.INSTANCE.css();
  }

}
