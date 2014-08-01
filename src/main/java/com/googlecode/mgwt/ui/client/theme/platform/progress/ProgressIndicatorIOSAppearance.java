package com.googlecode.mgwt.ui.client.theme.platform.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicatorAppearance;

public class ProgressIndicatorIOSAppearance implements
    ProgressIndicatorAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface IOSProgressIndicatorCss extends ProgressIndicatorCss {
    @ClassName("mgwt-ProgressSpinner")
    String progressIndicator();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({ "com/googlecode/mgwt/ui/client/widget/progress/progressspinner.css" })
    IOSProgressIndicatorCss css();
  }

  @Override
  public ProgressIndicatorCss css() {
    return Resources.INSTANCE.css();
  }

  @UiTemplate("com.googlecode.mgwt.ui.client.widget.progress.ProgressSpinnerBaseAppearance.ui.xml")
  interface Binder extends UiBinder<Element, ProgressIndicator> {
  }

  private static final Binder UI_BINDER = GWT.create(Binder.class);

  @Override
  public UiBinder<? extends Element, ProgressIndicator> uiBinder() {
    return UI_BINDER;
  }
}
