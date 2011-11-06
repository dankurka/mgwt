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
package com.googlecode.mgwt.ui.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort;
import com.googlecode.mgwt.ui.client.theme.base.UtilCss;

/**
 * The MGWT Object is used to apply settings for an MGWT App. It also provides
 * an instance of {@link OsDetection}, as well a way to determine the device
 * orientation
 * 
 * 
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class MGWT {
	private static final Logger logger = Logger.getLogger("MGWT");

	static {
		if (GWT.isClient()) {
			setupOrientation();
		}

	}

	private static final OsDetection OS_DETECTION = GWT.create(OsDetection.class);

	private final static EventBus manager = new SimpleEventBus();

	private static ORIENTATION currentOrientation;
	private static Timer timer;

	private static boolean scrollingDisabled;
	private static JavaScriptObject nativeJsFunction;

	/**
	 * Add a orientation handler to detect the device orientation
	 * 
	 * @param handler the handler to add
	 *            {@link com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler}
	 *            .
	 * @return a {@link com.google.gwt.event.shared.HandlerRegistration} object.
	 */
	public static HandlerRegistration addOrientationChangeHandler(OrientationChangeHandler handler) {
		return manager.addHandler(OrientationChangeEvent.getType(), handler);
	}

	/**
	 * Apply settings for this mgwt app. Every app should call this method with
	 * the options its wants for their app
	 * 
	 * The options are documented in @link {@link MGWTSettings}
	 * 
	 * @param settings the settings for this app
	 * 
	 */
	public static void applySettings(MGWTSettings settings) {

		// This is a very nasty workaround because GWT CssResource does not
		// support @media correctly!
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

		ViewPort viewPort = settings.getViewPort();

		if (viewPort != null) {
			MetaElement fixViewPortElement = Document.get().createMetaElement();
			fixViewPortElement.setName("viewport");
			fixViewPortElement.setContent(viewPort.getContent());
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

		}

		scrollingDisabled = settings.isPreventScrolling();
		if (settings.isPreventScrolling() && getOsDetection().isIOs()) {
			BodyElement body = Document.get().getBody();
			setupPreventScrolling(body);

		}

		if (settings.isDisablePhoneNumberDetection()) {
			MetaElement fullScreenMetaTag = Document.get().createMetaElement();
			fullScreenMetaTag.setName("format-detection");
			fullScreenMetaTag.setContent("telephone=no");
			head.appendChild(fullScreenMetaTag);
		}

		if (settings.getStatusBarStyle() != null) {
			MetaElement statusBarTag = Document.get().createMetaElement();
			statusBarTag.setName("apple-mobile-web-app-status-bar-style");
			switch (settings.getStatusBarStyle()) {
			case DEFAULT:
				statusBarTag.setContent("default");
				break;
			case BLACK:
				statusBarTag.setContent("black");
				break;
			case BLACK_TRANSLUCENT:
				statusBarTag.setContent("black-translucent");
				break;
			default:
				statusBarTag.setContent("default");
				break;
			}

			head.appendChild(statusBarTag);
		}

	}

	/**
	 * Detect if a web app is in full screen mode
	 * 
	 * fullscreen currently only exits on ios
	 * 
	 * @return true if the web app is in full screen mode, otherwise false
	 */
	public static native boolean isFullScreen()/*-{
		if ($wnd.navigator.standalone) {
			return true;
		}
		return false;
	}-*/;

	/**
	 * 
	 * Considered internal don`t call!
	 * <p>
	 * fixIOSScrollIssueBlur
	 * </p>
	 */
	public static void fixIOSScrollIssueBlur() {
		if (!scrollingDisabled) {
			return;
		}
		if (timer != null) {
			timer.cancel();

		}

		timer = new Timer() {

			@Override
			public void run() {
				Window.scrollTo(0, 0);

			}

		};

		timer.schedule(100);
	}

	/**
	 * Considered internal don`t call!
	 * <p>
	 * fixIOSScrollIssueFocus
	 * </p>
	 */
	public static void fixIOSScrollIssueFocus() {
		if (!scrollingDisabled) {
			return;
		}
		if (timer != null) {
			timer.cancel();

		}
		timer = null;
	}

	/**
	 * Get the os detection interface
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.OsDetection} object.
	 */
	public static OsDetection getOsDetection() {
		return OS_DETECTION;
	}

	/**
	 * Get the current orientation of the device
	 * 
	 * @return the current orientation of the device
	 */
	public static ORIENTATION getOrientation() {

		if (!orientationSupport()) {
			int height = Window.getClientHeight();
			int width = Window.getClientWidth();

			if (width > height) {
				return ORIENTATION.LANDSCAPE;
			} else {
				return ORIENTATION.PORTRAIT;
			}

		} else {
			int orientation = getOrientation0();

			ORIENTATION o;
			switch (orientation) {
			case 0:
			case 180:
				o = ORIENTATION.PORTRAIT;
				break;

			case 90:
			case -90:
				o = ORIENTATION.LANDSCAPE;
				break;

			default:
				throw new IllegalStateException("this should not happen!?");
			}

			return o;
		}

	}

	private static void fireOrientationChangedEvent(ORIENTATION orientation) {
		setClasses(orientation);
		manager.fireEvent(new OrientationChangeEvent(orientation));
	}

	private static Element getHead() {
		NodeList<Element> elementsByTagName = Document.get().getElementsByTagName("head");

		if (elementsByTagName.getLength() != 1) {
			throw new RuntimeException("there is no head element");
		}

		return elementsByTagName.getItem(0);
	}

	private static native int getOrientation0()/*-{
		if (typeof ($wnd.orientation) == 'undefined') {
			return 0;
		}

		return $wnd.orientation;
	}-*/;

	private static void onorientationChange(int orientation) {

		ORIENTATION o;
		switch (orientation) {
		case 0:
		case 180:
			o = ORIENTATION.PORTRAIT;
			break;

		case 90:
		case -90:
			o = ORIENTATION.LANDSCAPE;
			break;

		default:
			logger.warning("on orientation changed called with invalid value: '" + orientation + "' - defaulting to Portrait");
			o = ORIENTATION.PORTRAIT;
			break;
		}
		currentOrientation = o;
		fireOrientationChangedEvent(o);

	}

	// update styles on body
	private static void setClasses(ORIENTATION o) {
		UtilCss utilCss = MGWTStyle.getDefaultClientBundle().getUtilCss();
		switch (o) {

		case PORTRAIT:
			Document.get().getBody().addClassName(utilCss.portrait());
			Document.get().getBody().removeClassName(utilCss.landscape());
			break;
		case LANDSCAPE:
			Document.get().getBody().addClassName(utilCss.landscape());
			Document.get().getBody().removeClassName(utilCss.portrait());
			break;

		default:
			break;
		}
	}

	private native static boolean orientationSupport()/*-{
		var ua = window.navigator.userAgent.toLowerCase();
		if (ua.indexOf('android') != -1) {
			return false;
		}
		if (ua.indexOf('iphone') != -1) {
			return true;
		}
		if (ua.indexOf('ipad') != -1) {
			return true;
		}

		return false;
	}-*/;

	private static void setupOrientation() {

		if (!orientationSupport()) {
			Window.addResizeHandler(new ResizeHandler() {

				@Override
				public void onResize(ResizeEvent event) {
					ORIENTATION orientation = getOrientation();
					if (orientation != currentOrientation) {
						currentOrientation = orientation;
						fireOrientationChangedEvent(orientation);
					}
				}
			});
		} else {
			nativeJsFunction = setupOrientation0();
			Window.addCloseHandler(new CloseHandler<Window>() {

				@Override
				public void onClose(CloseEvent<Window> event) {
					destroyOrientation(nativeJsFunction);

				}
			});
		}

	}

	private static native JavaScriptObject setupOrientation0()/*-{

		var func = $entry(function() {
			@com.googlecode.mgwt.ui.client.MGWT::onorientationChange(I)($wnd.orientation);
		});
		$doc.body.onorientationchange = func;
		$doc.addEventListener("orientationChanged", func);
		return func;
	}-*/;

	private static native void destroyOrientation(JavaScriptObject o)/*-{
		$doc.body.onorientationchange = null;
		$doc.removeEventListener("orientationChanged", o);
	}-*/;

	private static native void setupPreventScrolling(Element el)/*-{
		var func = function(event) {
			event.preventDefault();
			return false;
		};

		el.ontouchmove = func;

	}-*/;

}
