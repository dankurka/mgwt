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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.googlecode.mgwt.ui.client.util.impl.CssUtilImpl;

/**
 *
 * Considered internal
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class CssUtil {

	private static final CssUtilImpl cssUtilImpl = GWT.create(CssUtilImpl.class);

	public static void translate(Element el, int x, int y) {
		cssUtilImpl.translate(el, x, y);

	}

	public static void setTransitionsDelay(Element el, int milliseconds) {
		cssUtilImpl.setDelay(el, milliseconds);
	}

	public static void setOpacity(Element el, double opacity) {
		cssUtilImpl.setOpacity(el, opacity);
	}

	public static void setTransitionDuration(Element el, int time) {
		cssUtilImpl.setDuration(el, time);
	}

	public static void rotate(Element element, int degree) {
		cssUtilImpl.rotate(element, degree);

	}
	
	public static boolean hasTransform(){
		return cssUtilImpl.hasTransform();
	}
	
	public static boolean hasTransistionEndEvent(){
		return cssUtilImpl.hasTransistionEndEvent();
	}
	
	public static boolean has3d(){
		return cssUtilImpl.has3d();
	}

	public static String getTransformProperty() {
		return cssUtilImpl.getTransformProperty();
	}

	

	public static void setTransistionProperty(Element element, String string) {
		// TODO Auto-generated method stub
		
	}

	public static void setTransFormOrigin(Element element, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	public static void setTransistionTimingFunction(Element element, String string) {
		// TODO Auto-generated method stub
		
	}

	public static void setTransForm(Element element, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	

	public static int[] getPositionFromTransForm(Element element) {
		return cssUtilImpl.getPositionFromTransForm(element);
	}

	

	public static int getLeftPositionFromCssPosition(Element element) {
		return cssUtilImpl.getLeftPositionFromCssPosition(element);
	}

	public static int getTopPositionFromCssPosition(Element element) {
		return cssUtilImpl.getTopPositionFromCssPosition(element);
	}
}
