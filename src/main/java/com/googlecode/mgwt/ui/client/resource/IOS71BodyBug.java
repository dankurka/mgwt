package com.googlecode.mgwt.ui.client.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

import com.googlecode.mgwt.ui.client.MGWT;

/**
 * This class fixes an iPad bug on iOS7.1.
 * <p>
 * On iPads with iOS 7.1 in landscape using a body height of 100% results in a
 * 20px white stripe on the bottom of screen, since the iPad reports its screen to be 20px less
 * in height. Fixed by applying a special CSS to iPads in landscape mode.
 */
public class IOS71BodyBug {

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
