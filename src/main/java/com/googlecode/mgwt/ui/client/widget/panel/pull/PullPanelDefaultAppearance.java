package com.googlecode.mgwt.ui.client.widget.panel.pull;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public class PullPanelDefaultAppearance extends PullPanelAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"pullpanel.css"})
    PullPanelCss css();
    
    @Source("nextslide.png")
    ImageResource getButtonBarNextSlideImage();
    
    @Source("error.png")
    ImageResource errorImage();

  }
  
  @Override
  public PullPanelCss css() {
    return Resources.INSTANCE.css();
  }
}
