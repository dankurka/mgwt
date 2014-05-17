package com.googlecode.mgwt.ui.client.widget.form;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface FormAppearance {
  public interface FormCss extends MGWTCssResource {

    @ClassName("mgwt-Form")
    String form();

    @ClassName("mgwt-Form-Entry-first")
    String formEntryFirstChild();

    @ClassName("mgwt-Form-Entry-last")
    String formEntryLastChild();

    @ClassName("mgwt-Form-round")
    String round();

    @ClassName("mgwt-Form-Entry")
    String formEntry();

    @ClassName("mgwt-Form-Entry-invalid")
    String formEntryInvalid();

    @ClassName("mgwt-Form-Entry-label")
    String formEntryLabel();

    @ClassName("mgwt-Form-Entry-container")
    String formEntryContainer();

    @ClassName("mgwt-Form-Header")
    String formHeader();
  }

  FormCss css();

  UiBinder<Widget, Form> uiBinder();

  UiBinder<Widget, FormEntry> uiBinderEntry();
}
