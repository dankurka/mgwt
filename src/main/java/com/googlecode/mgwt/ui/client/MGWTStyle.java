package com.googlecode.mgwt.ui.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.NodeList;

/**
 * The {@link MGWTStyle} class provides an easy access to the default theme of every gwt app.
 * 
 * If widgets are created without a specific theme they ask MGWTStyle for the default theme.
 * 
 * The default theme can be changed at start up time of an mgwt app <b>once</b>.
 * 
 * After startup the bundle can not be changed. If no bundle is set, MGWTStyle creates the MGWT
 * Default theme.
 * 
 * If you like to create your own theme consult the docs at: <a
 * href="http://code.google.com/p/mgwt/wiki/Styling" >http://code.google.com/p/mgwt/wiki/Styling</a>
 * 
 * 
 * @author Daniel Kurka
 */
public class MGWTStyle {


  /**
   * Inject a given css file at the end of the head
   * 
   * @param url the url of the css file
   */
  public static void injectStyleSheet(String url) {
    NodeList<Element> nodeList = Document.get().getElementsByTagName("head");
    if (nodeList.getLength() != 1) {
      throw new RuntimeException("can not find head element, does your html include a head section?");
    }
    final Element head = nodeList.getItem(0);
    final LinkElement linkElement = Document.get().createLinkElement();
    linkElement.setRel("stylesheet");
    linkElement.setType("text/css");
    linkElement.setHref(url);
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @Override
      public void execute() {
        head.appendChild(linkElement);

      }
    });

  }

  /**
   * this routine provides access to match media from java
   * 
   * @param mediaQuery the query to perform
   * @return true if the query matches otherwise false
   */
  public native boolean matchMedia(String mediaQuery)/*-{
		return $wnd.matchMedia(mediaQuery);
  }-*/;
}
