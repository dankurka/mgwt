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
package de.kurka.gwt.mobile.ui.client.canvas;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Daniel Kurka
 *
 */
public class CssCanvas {

	private JavaScriptObject context;

	protected CssCanvas(String contextType, String id, int width, int height) {
		context = getContext(contextType, id, width, height);
	}

	protected native JavaScriptObject getContext(String contextType, String id, int width, int height)/*-{
																										return $doc.getCSSCanvasContext(contextType, id, width, height);
																										}-*/;

	public static final CssCanvas getCssCanvasContext(String contextType, String id, int width, int height) {
		return new CssCanvas(contextType, id, width, height);
	}

	public native void setFillStyle(String style)/*-{
													this.@de.kurka.gwt.mobile.ui.client.canvas.CssCanvas::context.fillStyle = style;
													}-*/;

	public native void beginPath()/*-{
									this.@de.kurka.gwt.mobile.ui.client.canvas.CssCanvas::context.beginPath();
									}-*/;

	public native void arc(double x, double y, double radius, double startAngle, double endAngle, boolean antiClockwise)/*-{
																														this.@de.kurka.gwt.mobile.ui.client.canvas.CssCanvas::context.arc(x, y, radius, startAngle, endAngle, antiClockwise);
																														}-*/;

	public native void lineTo(double x, double y)/*-{
													this.@de.kurka.gwt.mobile.ui.client.canvas.CssCanvas::context.lineTo(x, y);
													}-*/;

	public native void closePath()/*-{
									this.@de.kurka.gwt.mobile.ui.client.canvas.CssCanvas::context.closePath();
									}-*/;

	public native void fill()/*-{
								this.@de.kurka.gwt.mobile.ui.client.canvas.CssCanvas::context.fill();
								}-*/;

}
