package com.googlecode.mgwt.ui.client.theme.platform.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

import com.googlecode.mgwt.ui.client.widget.progress.ProgressBarAbstractAppearance;

public class ProgressIndicatorBlackberryAppearance extends ProgressBarAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"progressbar-base.css", "progressbar-android.css"})
    ProgressBarCss css();

    @Source("spinner_white.png")
    DataResource spinnerImage();
  }

  @Override
  public ProgressBarCss css() {
    return Resources.INSTANCE.css();
  }
}
