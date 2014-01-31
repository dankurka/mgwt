package com.googlecode.mgwt.ui.client.widget.layout;

public class RootLayoutPanel extends LayoutPanel {

  public RootLayoutPanel() {
    this(DEFAULT_APPERANCE);
  }

  public RootLayoutPanel(LayoutAppearance appearance) {
    super(appearance);
    addStyleName(appearance.css().rootLayoutPanel());
  }
}
