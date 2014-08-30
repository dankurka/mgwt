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
package com.googlecode.mgwt.css.client.updater;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Timer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CssUpdater {
  private class Watcher extends Timer {

    private int count;
    private long unique;

    @Override
    public void run() {

      Set<String> urls = styleSheetsByUrl.keySet();
      final int limit = urls.size();
      count = 0;

      for (final String url : urls) {
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url + "?random=" + unique++);
        requestBuilder.setCallback(new RequestCallback() {

          @Override
          public void onResponseReceived(Request request, Response response) {
            count++;

            if (response.getStatusCode() != 200) {
              GWT.log("Got a nun 200 return code: " + response.getStatusCode());
            } else {
              String newCss = response.getText();
              String oldCss = cssContentByUrl.get(url);
              if (!oldCss.equals(newCss)) {
                cssContentByUrl.put(url, newCss);
                styleSheetsByUrl.get(url).setInnerText(newCss);
              }
            }

            if (count == limit) {
              schedule(interval);
            }
          }

          @Override
          public void onError(Request request, Throwable exception) {
            count++;
            GWT.log("failed to update stylesheet: " + url, exception);

            if (count == limit) {
              schedule(interval);
            }
          }
        });
        try {
          requestBuilder.send();
        } catch (RequestException e) {
          GWT.log("failed to send update request for: " + url, e);
        }
      }
    }
  }

  private Map<String, StyleElement> styleSheetsByUrl = new HashMap<String, StyleElement>();
  private Map<String, String> cssContentByUrl = new HashMap<String, String>();
  private Watcher watcher;
  private final int interval;

  public CssUpdater(int interval) {
    this.interval = interval;
  }

  public void watchStyleSheet(String url) {
    if (!styleSheetsByUrl.containsKey(url)) {
      StyleElement styleElement = Document.get().createStyleElement();
      getHead().appendChild(styleElement);
      styleSheetsByUrl.put(url, styleElement);
      cssContentByUrl.put(url, "");
      maybeStartWatcher();
    }
  }

  private void maybeStartWatcher() {
    if (watcher == null) {
      watcher = new Watcher();
      watcher.schedule(1);
    }
  }

  private Element getHead() {
    NodeList<Element> nodeList = Document.get().getElementsByTagName("head");
    if (nodeList.getLength() != 1) {
      throw new RuntimeException("can not find head element, does your html include a head section?");
    }
    return nodeList.getItem(0);
  }
}
