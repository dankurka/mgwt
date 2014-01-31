package com.googlecode.mgwt.ui.client.widget.layout;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Panel;

public class LayoutDefaultAppearance implements LayoutAppearance {
  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"layout.css"})
    LayoutCss css();
  }

  @UiTemplate("LayoutBaseAppearance.ui.xml")
  interface Binder extends UiBinder<Panel, LayoutPanel> {
  }

  private static final Binder UI_BINDER = GWT.create(Binder.class);

  @Override
  public UiBinder<Panel, LayoutPanel> uiBinder() {
    return UI_BINDER;
  }

  @Override
  public LayoutCss css() {
    return Resources.INSTANCE.css();
  }
}
