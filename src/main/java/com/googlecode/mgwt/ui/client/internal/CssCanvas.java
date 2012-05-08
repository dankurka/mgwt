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
package com.googlecode.mgwt.ui.client.internal;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Considered internal
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
@Deprecated
public class CssCanvas {

	private JavaScriptObject context;

	/**
	 * <p>
	 * Constructor for CssCanvas.
	 * </p>
	 * 
	 * @param contextType a {@link java.lang.String} object.
	 * @param id a {@link java.lang.String} object.
	 * @param width a int.
	 * @param height a int.
	 */
	protected CssCanvas(String contextType, String id, int width, int height) {
		context = getContext(contextType, id, width, height);
	}

	/**
	 * <p>
	 * Getter for the field <code>context</code>.
	 * </p>
	 * 
	 * @param contextType a {@link java.lang.String} object.
	 * @param id a {@link java.lang.String} object.
	 * @param width a int.
	 * @param height a int.
	 * @return a {@link com.google.gwt.core.client.JavaScriptObject} object.
	 */
	protected native JavaScriptObject getContext(String contextType, String id, int width, int height)/*-{
		return $doc.getCSSCanvasContext(contextType, id, width, height);
	}-*/;

	/**
	 * <p>
	 * getCssCanvasContext
	 * </p>
	 * 
	 * @param contextType a {@link java.lang.String} object.
	 * @param id a {@link java.lang.String} object.
	 * @param width a int.
	 * @param height a int.
	 * @return a {@link com.googlecode.mgwt.ui.client.internal.CssCanvas}
	 *         object.
	 */
	public static final CssCanvas getCssCanvasContext(String contextType, String id, int width, int height) {
		return new CssCanvas(contextType, id, width, height);
	}

	public native static final boolean isSupported()/*-{
		return typeof ($doc.getCSSCanvasContext) != "undefined";

	}-*/;

	/**
	 * <p>
	 * setFillStyle
	 * </p>
	 * 
	 * @param style a {@link java.lang.String} object.
	 */
	public native void setFillStyle(String style)/*-{
		this.@com.googlecode.mgwt.ui.client.internal.CssCanvas::context.fillStyle = style;
	}-*/;

	/**
	 * <p>
	 * beginPath
	 * </p>
	 */
	public native void beginPath()/*-{
		this.@com.googlecode.mgwt.ui.client.internal.CssCanvas::context
				.beginPath();
	}-*/;

	/**
	 * <p>
	 * arc
	 * </p>
	 * 
	 * @param x a double.
	 * @param y a double.
	 * @param radius a double.
	 * @param startAngle a double.
	 * @param endAngle a double.
	 * @param antiClockwise a boolean.
	 */
	public native void arc(double x, double y, double radius, double startAngle, double endAngle, boolean antiClockwise)/*-{
		this.@com.googlecode.mgwt.ui.client.internal.CssCanvas::context.arc(x,
				y, radius, startAngle, endAngle, antiClockwise);
	}-*/;

	/**
	 * <p>
	 * lineTo
	 * </p>
	 * 
	 * @param x a double.
	 * @param y a double.
	 */
	public native void lineTo(double x, double y)/*-{
		this.@com.googlecode.mgwt.ui.client.internal.CssCanvas::context.lineTo(
				x, y);
	}-*/;

	/**
	 * <p>
	 * closePath
	 * </p>
	 */
	public native void closePath()/*-{
		this.@com.googlecode.mgwt.ui.client.internal.CssCanvas::context
				.closePath();
	}-*/;

	/**
	 * <p>
	 * fill
	 * </p>
	 */
	public native void fill()/*-{
		this.@com.googlecode.mgwt.ui.client.internal.CssCanvas::context.fill();
	}-*/;

}
