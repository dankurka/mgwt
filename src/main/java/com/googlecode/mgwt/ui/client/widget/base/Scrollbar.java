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
package com.googlecode.mgwt.ui.client.widget.base;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.internal.CssCanvas;
import com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.util.FeatureDetection;

/**
 * <p>
 * Scrollbar class.
 * </p>
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
@Deprecated
public class Scrollbar extends Widget {

	private final Orientation orientation;

	private static int global_uuid = 0;

	private int uuid;

	private Element bar;

	private final boolean has3d;

	private int maxSize;

	private int size;

	private int maxScroll;

	private double wrapperSize;

	private boolean shrink = true;

	private double wrapperProp;

	protected final ScrollPanelCss css;

	public static enum Orientation {
		HORIZONTAL, VERTICAL
	}

	/**
	 * <p>
	 * Constructor for Scrollbar.
	 * </p>
	 * 
	 * @param css a
	 *            {@link com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss}
	 *            object.
	 * @param orientation a
	 *            {@link com.googlecode.mgwt.ui.client.widget.base.Scrollbar.Orientation}
	 *            object.
	 * @param has3d a boolean.
	 * @param scrollAreaSize a int.
	 * @param wholeSize a int.
	 */
	public Scrollbar(ScrollPanelCss css, Orientation orientation, boolean has3d, int scrollAreaSize, int wholeSize) {
		this.css = css;
		// TODO remove this
		if (wholeSize == 0) {
			wholeSize = 300;
		}
		this.has3d = has3d;
		this.orientation = orientation;
		uuid = ++global_uuid;
		maxSize = scrollAreaSize - 10;
		size = Math.max(Math.round(((float) maxSize * maxSize) / wholeSize), 6);
		maxScroll = maxSize - size;
		wrapperSize = ((double) scrollAreaSize) / (wholeSize);
		wrapperProp = ((double) maxScroll / (scrollAreaSize - wholeSize));

		setElement(DOM.createDiv());

		addStyleName(css.scrollBar());

		switch (orientation) {
		case HORIZONTAL:
			addStyleName(css.horizontal());
			break;
		case VERTICAL:
			addStyleName(css.vertical());

		}

		applyStyle(getElement(), "-webkit-mask:-webkit-canvas(scrollbar" + uuid + ");");

		bar = DOM.createDiv();

		bar.addClassName(css.scrollBarBar());

		String cssText = "";
		switch (orientation) {
		case HORIZONTAL:

			bar.addClassName(css.horizontal());
			break;

		case VERTICAL:

			bar.addClassName(css.vertical());
			break;
		default:
			throw new RuntimeException("how did we get here?");

		}

		if (has3d) {
			cssText += "-webkit-transform: translate3d(0px,0px, 0px);";
		} else {
			cssText += "-webkit-transform: translate(0px,0px);";
		}

		switch (orientation) {
		case HORIZONTAL:
			cssText += "width: " + size + "px; height: 5px;";
			break;

		case VERTICAL:
			cssText += "height: " + size + "px; width: 5px;";
			break;
		default:
			throw new RuntimeException("how did we get here?");

		}

		applyStyle(bar, cssText);

		DOM.appendChild(getElement(), bar);

		// quick and dirty hack for ff
		if (!CssCanvas.isSupported())
			return;

		switch (orientation) {
		case HORIZONTAL:
			CssCanvas canvas = CssCanvas.getCssCanvasContext("2d", "scrollbar" + uuid, maxSize, 5);
			canvas.setFillStyle("rgb(0,0,0)");
			canvas.beginPath();
			canvas.arc(2.5, 2.5, 2.5, Math.PI / 2, -Math.PI / 2, false);
			canvas.lineTo(maxSize - 2.5, 0);
			canvas.arc(maxSize - 2.5, 2.5, 2.5, -Math.PI / 2, Math.PI / 2, false);
			canvas.closePath();
			canvas.fill();
			break;

		case VERTICAL:
			CssCanvas canvas1 = CssCanvas.getCssCanvasContext("2d", "scrollbar" + uuid, 5, maxSize);
			canvas1.setFillStyle("rgb(0,0,0)");
			canvas1.beginPath();
			canvas1.arc(2.5, 2.5, 2.5, Math.PI, 0, false);
			canvas1.lineTo(5, maxSize - 2.5);
			canvas1.arc(2.5, maxSize - 2.5, 2.5, 0, Math.PI, false);
			canvas1.closePath();
			canvas1.fill();
			break;
		default:
			throw new RuntimeException("how did we get here?");

		}

	}

	private native void applyStyle(Element el, String styleText)/*-{
		el.style.cssText = styleText;
	}-*/;

	/**
	 * <p>
	 * setPosition
	 * </p>
	 * 
	 * @param pos a int.
	 */
	public void setPosition(int pos) {
		show();
		pos = (int) Math.round(wrapperProp * pos);

		if (pos < 0) {
			pos = shrink ? pos + pos * 3 : 0;
			if (size + pos < 7) {
				pos = -size + 6;
			}
		} else if (pos > maxScroll) {
			pos = shrink ? pos + (pos - maxScroll) * 3 : maxScroll;
			if (size + maxScroll - pos < 7) {
				pos = size + maxScroll - 6;
			}
		}

		switch (orientation) {
		case HORIZONTAL:
			CssUtil.translate(bar, pos, 0);

			break;

		case VERTICAL:
			CssUtil.translate(bar, 0, pos);
			break;

		default:
			throw new RuntimeException("how did we get here?");
		}

	}

	private native void setTransform(Element el, String transform)/*-{
		el.style.webkitTransform = transform;
	}-*/;

	/**
	 * <p>
	 * hide
	 * </p>
	 */
	public void hide() {
		if (FeatureDetection.has3d()) {
			CssUtil.setTransitionsDelay(getElement(), 350);
		}

		CssUtil.setOpacity(getElement(), 0);
	}

	/**
	 * <p>
	 * show
	 * </p>
	 */
	public void show() {
		if (FeatureDetection.has3d()) {
			CssUtil.setTransitionsDelay(getElement(), 0);
		}

		CssUtil.setOpacity(getElement(), 1);

	}

	/**
	 * <p>
	 * setTransitionTime
	 * </p>
	 * 
	 * @param milliseconds a int.
	 */
	public void setTransitionTime(int milliseconds) {
		if (FeatureDetection.has3d()) {
			CssUtil.setTransitionDuration(getElement(), 300);
		} else {
			CssUtil.setTransitionDuration(getElement(), 0);
		}

		CssUtil.setTransitionDuration(bar, milliseconds);

	}
}
