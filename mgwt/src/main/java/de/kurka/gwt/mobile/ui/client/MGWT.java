/**
 * 30.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client;

import com.google.gwt.dom.client.BodyElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.NodeList;

/**
 * @author kurt
 *
 */
public class MGWT {

	private static Element getHead() {
		NodeList<Element> elementsByTagName = Document.get().getElementsByTagName("head");

		if (elementsByTagName.getLength() != 1) {
			throw new RuntimeException("there is no head element");
		}

		return elementsByTagName.getItem(0);
	}

	public static void applySettings(MGWTSettings settings) {

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
		}

		if (settings.isPreventScrolling()) {
			BodyElement body = Document.get().getBody();
			setUpPreventScrolling(body);
		}

	}

	private static native void setUpPreventScrolling(Element el)/*-{
		var func = function(event){
		event.preventDefault();
		};

		el.ontouchmove = func;
	}-*/;
}
