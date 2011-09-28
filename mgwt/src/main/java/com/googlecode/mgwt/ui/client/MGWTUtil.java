package com.googlecode.mgwt.ui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MGWTUtil {
	private static final FeatureDetection FEATURE_DETECTION = GWT.create(FeatureDetection.class);

	public static FeatureDetection getFeatureDetection() {
		return FEATURE_DETECTION;
	}

	private static Element getHead() {
		NodeList<Element> elementsByTagName = Document.get().getElementsByTagName("head");

		if (elementsByTagName.getLength() != 1) {
			throw new RuntimeException("there is no head element");
		}

		return elementsByTagName.getItem(0);
	}

	public static void loadCss(String url, final AsyncCallback<Boolean> callback) {
		final LinkElement linkElement = Document.get().createLinkElement();
		linkElement.setHref(url);
		linkElement.setRel("stylesheet");
		linkElement.setType("text/css");

		getHead().appendChild(linkElement);

		new Timer() {
			private int count = 0;

			@Override
			public void run() {
				if (hasLoaded(linkElement)) {
					cancel();
					callback.onSuccess(true);
				}
				if (count > 10000) {
					callback.onFailure(null);
					cancel();
				}
				count++;

			}
		}.scheduleRepeating(10);

	}

	private static native boolean hasLoaded(Element cssStylesheet)/*-{
																	try {
																	if (cssStylesheet.sheet && cssStylesheet.sheet.cssRules.length > 0)
																	return true;
																	else if (cssStylesheet.styleSheet
																	&& cssStylesheet.styleSheet.cssText.length > 0)
																	return true;
																	else if (cssStylesheet.innerHTML
																	&& cssStylesheet.innerHTML.length > 0)
																	return true;
																	} catch (ex) {
																	}
																	return false;
																	}-*/;

	private static Timer timer;

	private static boolean scrollingDisabled;

	public static void setScrollingDisabled(boolean disabled) {
		scrollingDisabled = disabled;
	}

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

	public static void fixIOSScrollIssueFocus() {
		if (!scrollingDisabled) {
			return;
		}
		if (timer != null) {
			timer.cancel();

		}
		timer = null;
	}
}
