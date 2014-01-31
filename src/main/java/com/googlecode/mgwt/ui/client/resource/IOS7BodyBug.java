package com.googlecode.mgwt.ui.client.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

import com.googlecode.mgwt.ui.client.MGWT;

public class IOS7BodyBug {

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"ios71bodybug.css"})
    TextResource css();
  }

  public static void applyWorkaround() {
    if (MGWT.getOsDetection().isIPad()) {
      if (isIOS71()) {
        String text = Resources.INSTANCE.css().getText();
        StyleInjector.inject(text);
        Document.get().getBody().addClassName("__fixIOS7BodyBug");
      }
    }
  }

  private native static boolean isIOS71() /*-{
		return $wnd.navigator.userAgent.match(/iPad;.*CPU.*OS 7_\d/i);
  }-*/;
}
