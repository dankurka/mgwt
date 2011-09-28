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
package com.googlecode.mgwt.ui.client.util;

import com.google.gwt.user.client.Element;

/**
 * @author Daniel Kurka
 *
 */
public class CssUtil {
	public static void translate(Element el, int x, int y) {
		String cssText = null;
		if (FeatureDetection.has3d()) {
			cssText = "translate3d(" + x + "px, " + y + "px, 0px)";
		} else {
			cssText = "translate( " + x + "px, " + y + "px )";
		}

		_translate(el, cssText);

	}

	private native static void _translate(Element el, String css)/*-{
		el.style.webkitTransform = css;
	}-*/;

	public native static void setWebKitTransitionsDelay(Element el, int milliseconds)/*-{
		el.style.webkitTransitionDelay = milliseconds + "ms";
	}-*/;

	public native static void setOpacity(Element el, double opacity)/*-{
		el.style.opacity = opacity;
	}-*/;

	public native static void setWebKitTransitionDuration(Element el, int time)/*-{
		el.style.webkitTransitionDuration = time + "ms";
	}-*/;
}
