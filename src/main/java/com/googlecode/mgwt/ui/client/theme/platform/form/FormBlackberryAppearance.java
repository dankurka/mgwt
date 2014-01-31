package com.googlecode.mgwt.ui.client.theme.platform.form;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.form.FormAbstractAppearance;

public class FormBlackberryAppearance extends FormAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"form-base.css", "form-blackberry.css"})
    FormCss css();
  }

  @Override
  public FormCss css() {
    return Resources.INSTANCE.css();
  }
}
