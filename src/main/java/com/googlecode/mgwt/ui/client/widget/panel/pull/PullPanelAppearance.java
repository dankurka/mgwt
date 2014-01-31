package com.googlecode.mgwt.ui.client.widget.panel.pull;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public interface PullPanelAppearance {
  public interface PullPanelCss extends CssResource {
    
    @ClassName("mgwt-PullPanel")
    String pullPanel();
    
    @ClassName("mgwt-PullPanel-main")
    String pullPanelMain();
    
    @ClassName("mgwt-PullPanel-container")
    String pullPanelContainer();
    
    @ClassName("mgwt-PullToRefresh")
    public String pullToRefresh();

    @ClassName("mgwt-PullToRefresh-arrow")
    public String arrow();

    @ClassName("mgwt-PullToRefresh-text")
    public String text();

    @ClassName("mgwt-PullToRefresh-spinner")
    public String spinner();

    @ClassName("mgwt-PullToRefresh-error")
    public String error();

  }

  UiBinder<ScrollPanel, PullPanel> uiBinder();

  PullPanelCss css();

  UiBinder<Widget, PullArrowBase> arrow();
}
