package com.googlecode.mgwt.ui.client.widget.dialog.panel;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBaseAppearance;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.DialogOverlayAppearance;

public interface DialogPanelAppearance extends ButtonBaseAppearance, DialogOverlayAppearance {
  interface DialogButtonCss extends ButtonBaseAppearance.ButtonBaseCss {
    public String okbutton();

    public String cancelbutton();

    @Override
    public String active();

    @Override
    @ClassName("button")
    public String button();
  }

  interface DialogCss extends MGWTCssResource {
    @ClassName("mgwt-DialogPanel")
    String dialogPanel();

    @ClassName("mgwt-DialogPanel-container")
    String container();

    @ClassName("mgwt-DialogPanel-title")
    String title();

    @ClassName("mgwt-DialogPanel-content")
    String content();

    @ClassName("mgwt-DialogPanel-footer")
    String footer();
  }

  DialogCss dialogCss();

  @Override
  DialogButtonCss css();

  UiBinder<Widget, DialogPanel> dialogBinder();

  @Override
  UiBinder<Element, DialogButton> uiBinder();
}
