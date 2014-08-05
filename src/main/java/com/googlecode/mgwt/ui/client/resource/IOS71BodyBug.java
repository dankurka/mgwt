/*
 * Copyright 2014 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.resource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.Window;
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
    if (MGWT.getOsDetection().isIPad() || MGWT.getOsDetection().isIPadRetina()) {
      if (isIOS71() && windowInnerHeight() == 672) {
        String text = Resources.INSTANCE.css().getText();
        StyleInjector.inject(text);
        Document.get().getBody().addClassName("__fixIOS7BodyBug");
      }
    }
  }

  private native static boolean isIOS71() /*-{
		return !!$wnd.navigator.userAgent.match(/iPad;.*CPU.*OS 7_\d/i);
  }-*/;

  private native static int windowInnerHeight() /*-{
    return $wnd.innerHeight;
  }-*/;
}
