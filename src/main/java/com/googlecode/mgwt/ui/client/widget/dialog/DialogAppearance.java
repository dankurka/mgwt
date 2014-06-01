package com.googlecode.mgwt.ui.client.widget.dialog;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBaseAppearance;

public interface DialogAppearance extends ButtonBaseAppearance {
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

    @ClassName("mgwt-BottomPanel")
    String getBottomPanel();

    String container();

    String title();

    String content();

    String footer();

    @ClassName("mgwt-DialogAnimationContainer")
    String animationContainer();

    @ClassName("mgwt-DialogAnimationContainer-Shadow")
    String animationContainerShadow();

    String z_index();

    @ClassName("mgwt-DialogAnimationContainer-center")
    String animationContainerCenter();
  }

  DialogCss dialogCss();

  @Override
  DialogButtonCss css();

  UiBinder<Widget, DialogPanel> dialogBinder();

  @Override
  UiBinder<Element, DialogButton> uiBinder();
}
