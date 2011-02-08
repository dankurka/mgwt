/*
 * Copyright 2010 Daniel Kurka
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
package de.kurka.gwt.mobile.ui.client;

import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;

import de.kurka.gwt.mobile.dom.client.event.orientation.HasOrientationChangeHandler;
import de.kurka.gwt.mobile.dom.client.event.orientation.OrientationChangeEvent;
import de.kurka.gwt.mobile.dom.client.event.orientation.OrientationChangeHandler;

/**
 * @author Daniel Kurka
 *
 */
public class MGWT implements HasOrientationChangeHandler {

	private final HandlerManager manager = new HandlerManager(this);
	private FlowPanel flowPanel;

	private Element getHead() {
		NodeList<Element> elementsByTagName = Document.get().getElementsByTagName("head");

		if (elementsByTagName.getLength() != 1) {
			throw new RuntimeException("there is no head element");
		}

		return elementsByTagName.getItem(0);
	}

	public void applySettings(MGWTSettings settings) {

		Element head = getHead();

		if (settings.getIconUrl() != null) {

			LinkElement iconUrlLinkElement = Document.get().createLinkElement();
			if (settings.isAddGlosToIcon()) {
				iconUrlLinkElement.setRel("apple-touch-startup-image");
			} else {
				iconUrlLinkElement.setRel("apple-touch-startup-image-precomposed");
			}

			iconUrlLinkElement.setHref(settings.getIconUrl());
			head.appendChild(iconUrlLinkElement);

		}

		if (settings.getStartUrl() != null) {
			LinkElement startUrlLinkElement = Document.get().createLinkElement();
			startUrlLinkElement.setRel("apple-touch-startup-image");
			startUrlLinkElement.setHref(settings.getStartUrl());
			head.appendChild(startUrlLinkElement);
		}

		if (settings.isFixViewPort()) {
			MetaElement fixViewPortElement = Document.get().createMetaElement();
			fixViewPortElement.setName("viewport");
			fixViewPortElement.setContent("width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;");
			head.appendChild(fixViewPortElement);

		}

		if (settings.isFullscreen()) {
			MetaElement fullScreenMetaTag = Document.get().createMetaElement();
			fullScreenMetaTag.setName("apple-mobile-web-app-capable");
			fullScreenMetaTag.setContent("yes");
			head.appendChild(fullScreenMetaTag);

			if (settings.getStatusBar() != null) {
				MetaElement statusBarMetaTag = Document.get().createMetaElement();
				statusBarMetaTag.setName("apple-mobile-web-app-status-bar-style");
				statusBarMetaTag.setContent(settings.getStatusBar());
				head.appendChild(statusBarMetaTag);

			}
			//Window.alert("" + getWindowInnerHeight());
			//RootPanel.get().setHeight((getWindowInnerHeight() + 200) + "px");

			//			flowPanel = new FlowPanel();
			//			//flowPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
			//			flowPanel.setSize((600 + 1) + "px", (600 + 1) + "px");
			//			RootPanel.get().add(flowPanel);
			//
			//			new Timer() {
			//
			//				@Override
			//				public void run() {
			//
			//					//make sure we have more height / width than client area
			//
			//					//hide nav bar
			//
			//					Window.scrollTo(0, 1);
			//
			//					Window.alert("" + getWindowInnerHeight());
			//
			//				}
			//			}.schedule(1000);

		}

		if (settings.isPreventScrolling()) {
			BodyElement body = Document.get().getBody();
			setUpPreventScrolling(body);
		}

		if (settings.isOrientationSupport()) {
			setupOrientation();
			onorientationChange(getOrientation());
		}

		if (isFullScreen()) {
			Document.get().getBody().addClassName("fullscreen");
		} else {
			Document.get().getBody().addClassName("normalscreen");
		}

		//kombinierter 
		if (isFullScreen()) {
			if (getOrientation() == 0 || getOrientation() == 180) {
				Document.get().getBody().addClassName("mgwt-ViewPort-fullscreen-portrait");
			} else {
				Document.get().getBody().addClassName("mgwt-ViewPort-fullscreen-landscape");
			}

		} else {
			if (getOrientation() == 0 || getOrientation() == 180) {
				Document.get().getBody().addClassName("mgwt-ViewPort-normalscreen-portrait");
			} else {
				Document.get().getBody().addClassName("mgwt-ViewPort-normalscreen-landscape");
			}
		}

	}

	private native void setUpPreventScrolling(Element el)/*-{
		var func = function(event){
		event.preventDefault();
		//event.stopPropagation();
		return false;
		};

		el.ontouchmove = func;
		el.ontouchstart = func;
		el.ontouchend = func;
	}-*/;

	private native int getOrientation()/*-{
		if(typeof($wnd.orientation) == 'undefined')
		{return 0;}

		return $wnd.orientation;
	}-*/;

	private void onorientationChange(int orientation) {

		Document.get().getBody().removeClassName("mgwt-ViewPort-normalscreen-landscape");
		Document.get().getBody().removeClassName("mgwt-ViewPort-normalscreen-portrait");
		Document.get().getBody().removeClassName("mgwt-ViewPort-fullscreen-portrait");
		Document.get().getBody().removeClassName("mgwt-ViewPort-fullscreen-landscape");

		switch (orientation) {
		case 0:
		case 180:
			Document.get().getBody().addClassName("portrait");
			Document.get().getBody().removeClassName("landscape");

			if (isFullScreen()) {
				Document.get().getBody().addClassName("mgwt-ViewPort-fullscreen-portrait");
			} else {
				Document.get().getBody().addClassName("mgwt-ViewPort-normalscreen-portrait");
			}

			break;

		case 90:
		case -90:
			Document.get().getBody().addClassName("landscape");
			Document.get().getBody().removeClassName("portrait");

			if (isFullScreen()) {
				Document.get().getBody().addClassName("mgwt-ViewPort-fullscreen-landscape");
			} else {
				Document.get().getBody().addClassName("mgwt-ViewPort-normalscreen-landscape");
			}
			break;

		default:
			break;
		}

		manager.fireEvent(new OrientationChangeEvent(orientation));

	}

	private native void setupOrientation()/*-{
		var instance = this;
		var func = function(){

		instance.@de.kurka.gwt.mobile.ui.client.MGWT::onorientationChange(I)($wnd.orientation);
		};
		$doc.body.onorientationchange = func;
	}-*/;

	private native void clearOrientation(BodyElement elem)/*-{
		$doc.body.onorientationchange = null;
	}-*/;

	/* (non-Javadoc)
	 * @see de.kurka.gwt.mobile.dom.client.event.orientation.HasOrientationChangeHandler#addOrientationChangeHandler(de.kurka.gwt.mobile.dom.client.event.orientation.OrientationChangeHandler)
	 */
	@Override
	public HandlerRegistration addOrientationChangeHandler(OrientationChangeHandler handler) {
		return manager.addHandler(OrientationChangeEvent.getType(), handler);

	}

	private native int getWindowInnerHeight()/*-{
		return $wnd.innerHeight;
	}-*/;

	public native boolean isFullScreen()/*-{
		if($wnd.navigator.standalone)
		{return true;}
		return false;
	}-*/;
}
