package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

public class ProgressIndicatorAndroidAppearance extends ProgressIndicatorBaseAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"progressindicator-base.css", "progressindicator-android.css"})
    ProgressIndicatorCss css();

    @Source("spinner_white.png")
    DataResource spinnerImage();
  }
  
  @Override
  public ProgressIndicatorCss css() {
    return Resources.INSTANCE.css();
  }
}
