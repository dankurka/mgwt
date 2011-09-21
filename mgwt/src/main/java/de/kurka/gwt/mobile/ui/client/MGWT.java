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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;

import de.kurka.gwt.mobile.dom.client.event.orientation.HasOrientationChangeHandler;
import de.kurka.gwt.mobile.dom.client.event.orientation.OrientationChangeEvent;
import de.kurka.gwt.mobile.dom.client.event.orientation.OrientationChangeHandler;
import de.kurka.gwt.mobile.ui.client.theme.base.MGWTClientBundle;
import de.kurka.gwt.mobile.ui.client.theme.base.UtilCss;

/**
 * @author Daniel Kurka
 * 
 */
public class MGWT implements HasOrientationChangeHandler {

	private final static FeatureDetection FEATURE_DETECTION = GWT.create(FeatureDetection.class);

	private final HandlerManager manager = new HandlerManager(this);
	private FlowPanel flowPanel;

	public static final FeatureDetection getFeatureDetection() {
		return FEATURE_DETECTION;
	}

	private Element getHead() {
		NodeList<Element> elementsByTagName = Document.get().getElementsByTagName("head");

		if (elementsByTagName.getLength() != 1) {
			throw new RuntimeException("there is no head element");
		}

		return elementsByTagName.getItem(0);
	}

	public void applySettings(MGWTSettings settings) {

		//This is a very nasty workaround because GWT CssResource does not support @media correctly!
		StyleInjector.inject(MGWTStyle.getDefaultClientBundle().utilTextResource().getText());

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
			// Window.alert("" + getWindowInnerHeight());
			// RootPanel.get().setHeight((getWindowInnerHeight() + 200) + "px");

			// flowPanel = new FlowPanel();
			// //flowPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
			// flowPanel.setSize((600 + 1) + "px", (600 + 1) + "px");
			// RootPanel.get().add(flowPanel);
			//
			// new Timer() {
			//
			// @Override
			// public void run() {
			//
			// //make sure we have more height / width than client area
			//
			// //hide nav bar
			//
			// Window.scrollTo(0, 1);
			//
			// Window.alert("" + getWindowInnerHeight());
			//
			// }
			// }.schedule(1000);

		}

		if (settings.isPreventScrolling() && FEATURE_DETECTION.isIOs()) {
			BodyElement body = Document.get().getBody();
			setUpPreventScrolling(body);
		}

		if (settings.isOrientationSupport()) {
			setupOrientation();
			onorientationChange(getOrientation());
		}

	}

	private native void setUpPreventScrolling(Element el)/*-{
															var func = function(event) {
															event.preventDefault();
															//event.stopPropagation();
															return false;
															};

															el.ontouchmove = func;
															
															}-*/;

	private native int getOrientation()/*-{
										if (typeof ($wnd.orientation) == 'undefined') {
										return 0;
										}

										return $wnd.orientation;
										}-*/;

	private void onorientationChange(int orientation) {
		UtilCss utilCss = MGWTStyle.getDefaultClientBundle().getUtilCss();
		switch (orientation) {
		case 0:
		case 180:
			Document.get().getBody().addClassName(utilCss.portrait());
			Document.get().getBody().removeClassName(utilCss.landscape());

			break;

		case 90:
		case -90:
			Document.get().getBody().addClassName(utilCss.landscape());
			Document.get().getBody().removeClassName(utilCss.portrait());

			break;

		default:
			break;
		}

		manager.fireEvent(new OrientationChangeEvent(orientation));

	}

	private native void setupOrientation()/*-{
											var instance = this;
											var func = function() {

											instance.@de.kurka.gwt.mobile.ui.client.MGWT::onorientationChange(I)($wnd.orientation);
											};
											$doc.body.onorientationchange = func;
											$doc.addEventListener("orientationChanged", func);
											}-*/;

	/* (non-Javadoc)
	 * @see de.kurka.gwt.mobile.dom.client.event.orientation.HasOrientationChangeHandler#addOrientationChangeHandler(de.kurka.gwt.mobile.dom.client.event.orientation.OrientationChangeHandler)
	 */
	@Override
	public HandlerRegistration addOrientationChangeHandler(OrientationChangeHandler handler) {
		return manager.addHandler(OrientationChangeEvent.getType(), handler);

	}

	public native int getWindowInnerHeight()/*-{
											return $wnd.innerHeight;
											}-*/;

	public native int getWindowInnerWidth()/*-{
											return $wnd.innerWidth;
											}-*/;

	public native boolean isFullScreen()/*-{
										if ($wnd.navigator.standalone) {
										return true;
										}
										return false;
										}-*/;

	/**
	 * 
	 */
	public void loadStyle(MGWTClientBundle bundle) {
		bundle.getMainCss().ensureInjected();
	}

	public void loadStyle() {
		loadStyle(MGWTStyle.getDefaultClientBundle());
	}
}
