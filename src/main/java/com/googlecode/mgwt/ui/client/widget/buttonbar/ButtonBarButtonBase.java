/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiFactory;

import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.ButtonBase;

/**
 * <h1>Base class for all buttons in the button bar</h1>
 *
 * The class accepts two icons in its constructor which are used as background
 * images. They can be null, which will lead to no background images for the
 * button.
 *
 * <h2>Styling<h2>
 * The markup looks like:
 *
 * <pre>
 * &lt;div class="mgwt-BarButton">&lt;/div>
 *
 * <pre>
 *
 * <h3>Children Styles:</h3>
 * The following styles may be applied as well:
 * <ul>
 * <li>.mgwt-BarButton-active - this classes is set if the button is pressed</li>
 * </ul>
 *
 *
 * @author Daniel Kurka
 *
 */
public class ButtonBarButtonBase extends ButtonBase {

	public interface IconHandler {
		public void setIcons(Element element, ImageResource icon, ImageResource highlight, boolean active);
	}

	public static class DefaultIconHandler implements IconHandler {

		@Override
		public void setIcons(Element element, ImageResource icon, ImageResource highlight, boolean active) {
			if (icon == null)
				return;

			if (!active) {
				element.getStyle().setBackgroundImage("url(" + icon.getSafeUri().asString() + ")");

				if (MGWT.getOsDetection().isRetina() || MGWT.getOsDetection().isIPadRetina()) {
					element.getStyle().setProperty("backgroundSize", (icon.getWidth() / 2) + "px " + (icon.getHeight() / 2) + "px");
				}
			} else {

				// don't set active state if we don't have a hightlight icon...
				if (highlight == null) {
					return;
				}

				element.getStyle().setBackgroundImage("url(" + highlight.getSafeUri().asString() + "), url(" + icon.getSafeUri().asString() + ")");
				if (MGWT.getOsDetection().isRetina() || MGWT.getOsDetection().isIPadRetina()) {
					element.getStyle().setProperty("backgroundSize", (highlight.getWidth()) + "px " + (highlight.getHeight()) + "px, " + (icon.getWidth() / 2) + "px " + (icon.getHeight() / 2) + "px");
				}
			}

		}
	}

  protected static final IconHandler ICON_HANDLER = GWT.create(IconHandler.class);

  protected final ButtonBarAppearance appearance;


	public ButtonBarButtonBase(ImageResource icon) {
		this(ButtonBar.DEFAULT_APPEARANCE, icon, ButtonBar.DEFAULT_APPEARANCE.icons().highlightImage());
	}

	public ButtonBarButtonBase(ImageResource icon, ImageResource highlight) {
		this(ButtonBar.DEFAULT_APPEARANCE, icon, highlight);
	}

	public ButtonBarButtonBase(ButtonBarAppearance appearance, final ImageResource icon, final ImageResource highlight) {
		super(appearance);
		this.appearance = appearance;
		setElement(appearance.uiBinder().createAndBindUi(this));
		ICON_HANDLER.setIcons(getElement(), icon, highlight, false);

		addTouchHandler(new TouchHandler() {

			@Override
			public void onTouchCancel(TouchCancelEvent event) {
				ICON_HANDLER.setIcons(getElement(), icon, highlight, false);

			}

			@Override
			public void onTouchEnd(TouchEndEvent event) {
				ICON_HANDLER.setIcons(getElement(), icon, highlight, false);

			}

			@Override
			public void onTouchMove(TouchMoveEvent event) {

			}

			@Override
			public void onTouchStart(TouchStartEvent event) {
				ICON_HANDLER.setIcons(getElement(), icon, highlight, true);

			}
		});
	}

	@UiFactory
	public ButtonBarAppearance getAppearance() {
		return appearance;
	}
}
