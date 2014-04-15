package com.googlecode.mgwt.ui.client.theme.platform.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

import com.googlecode.mgwt.ui.client.widget.progress.ProgressBarAbstractAppearance;

public class ProgressBarAndroidAppearance extends ProgressBarAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"progressbar-base.css", "progressbar-android.css"})
    ProgressBarCss css();
  }

  @Override
  public ProgressBarCss css() {
    return Resources.INSTANCE.css();
  }
}
