package com.googlecode.mgwt.ui.client.widget.panel.flex;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class FlexSpacer extends Widget {

  public FlexSpacer() {
    this(1);
  }

  public FlexSpacer(double flex) {
    setElement(Document.get().createDivElement());
    setFlex(flex);
  }

  public void setFlex(double flex) {
    getElement().getStyle().setProperty("MozFlex", Double.toString(flex));
    getElement().getStyle().setProperty("WebkitFlex", Double.toString(flex));
    getElement().getStyle().setProperty("flex", Double.toString(flex));
  }
}
