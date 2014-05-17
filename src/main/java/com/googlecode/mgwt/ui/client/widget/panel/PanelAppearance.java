package com.googlecode.mgwt.ui.client.widget.panel;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface PanelAppearance {
  public interface PanelCss extends MGWTCssResource {
    @ClassName("mgwt-Panel")
    public String panel();

    @ClassName("mgwt-Panel-round")
    public String panelRound();
  }

  PanelCss css();

  UiBinder<Widget, Panel> uiBinder();
}
