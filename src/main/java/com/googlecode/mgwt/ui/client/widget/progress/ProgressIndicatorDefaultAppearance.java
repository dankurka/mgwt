package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class ProgressIndicatorDefaultAppearance extends ProgressIndicatorAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"progressindicator.css"})
    ProgressIndicatorCss css();
  }


  @Override
  public ProgressIndicatorCss css() {
    return Resources.INSTANCE.css();
  }
}
