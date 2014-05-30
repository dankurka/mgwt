package com.googlecode.mgwt.ui.client.widget.panel.flex;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class FixedSpacer extends Widget {

  public FixedSpacer() {
    this(60);
  }

  public FixedSpacer(int width) {
    setElement(Document.get().createDivElement());
    setWidth(width + "px");
  }
}
