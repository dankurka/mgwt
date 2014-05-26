package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class ProgressBarDefaultAppearance extends ProgressBarAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"progressbar.css"})
    ProgressBarCss css();
  }

  @Override
  public ProgressBarCss css() {
    return Resources.INSTANCE.css();
  }
}
