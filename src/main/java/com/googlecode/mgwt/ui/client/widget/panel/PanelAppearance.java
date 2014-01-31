package com.googlecode.mgwt.ui.client.widget.panel;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public interface PanelAppearance {
  public interface PanelCss extends CssResource {
    @ClassName("mgwt-Panel")
    public String panel();

    @ClassName("mgwt-Panel-round")
    public String panelRound();
  }
  
  PanelCss css();
  
  UiBinder<Widget, Panel> uiBinder();
}
