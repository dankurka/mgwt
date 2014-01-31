package com.googlecode.mgwt.ui.client.widget.list.widgetlist;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public class WidgetListDefaultAppearance extends WidgetListAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"widgetlist.css"})
    WidgetListCss css();
  }

  @Override
  public WidgetListCss css() {
    return Resources.INSTANCE.css();
  }
}
