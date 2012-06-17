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
package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

/**
 * The base class for all tab bar buttonss
 * 
 * @author Daniel Kurka
 */

public class TabBarButtonBase extends ButtonBase {

	protected final TabBarButtonStylerHandler tabBarButtonStylerHandler = GWT.create(TabBarButtonStylerHandler.class);

  /**
   * 
   * @author Daniel Kurka
   * 
   */
	public interface TabBarButtonStylerHandler {
    /**
     * set an image as background
     * 
     * @param element the element to set the image on
     * @param imageResource the image to use
     */
		public void applyImage(Element element, ImageResource imageResource);
	}

	// used by deferred binding...
	@SuppressWarnings("unused")
	private static class TabBarButtonStylerHandlerWebkit implements TabBarButtonStylerHandler {

		@Override
		public void applyImage(Element element, ImageResource imageResource) {
			if (imageResource == null)
				return;
			element.getStyle().setProperty("WebkitMaskBoxImage", "url(" + imageResource.getSafeUri().asString() + ")");
			element.getStyle().setHeight(imageResource.getHeight(), Unit.PX);
			element.getStyle().setWidth(imageResource.getWidth(), Unit.PX);
		}

	}

	@SuppressWarnings("unused")
	private static class TabBarButtonStylerHandlerBackground implements TabBarButtonStylerHandler {

		@Override
		public void applyImage(Element element, ImageResource imageResource) {
			if (imageResource == null)
				return;
			element.getStyle().setBackgroundImage("url(" + imageResource.getSafeUri().asString() + ")");
			element.getStyle().setHeight(imageResource.getHeight(), Unit.PX);
			element.getStyle().setWidth(imageResource.getWidth(), Unit.PX);
		}

	}

	protected final TabBarCss css;
	protected Element icon;
	protected Element text;
	protected final ImageResource imageResource;

  /**
   * Construct
   * 
   * @param css
   * @param imageResource
   */
	public TabBarButtonBase(TabBarCss css, ImageResource imageResource) {
		super(css);
		this.css = css;
		this.imageResource = imageResource;
		addStyleName(css.button());

		icon = DOM.createDiv();
		icon.addClassName(css.icon());
		getElement().appendChild(icon);

		text = DOM.createDiv();
		text.addClassName(css.text());
		getElement().appendChild(text);

		tabBarButtonStylerHandler.applyImage(icon, imageResource);
	}

	/**
	 * <p>
	 * setSelected
	 * </p>
	 * 
	 * @param selected a boolean.
	 */
	public void setSelected(boolean selected) {
		if (selected) {
			addStyleName(css.selected());
		} else {
			removeStyleName(css.selected());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText() {
		return icon.getInnerText();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setText(String newText) {
		text.setInnerText(newText);
	}

}
