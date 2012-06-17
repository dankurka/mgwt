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
package com.googlecode.mgwt.collection.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.mgwt.collection.shared.LightArrayBoolean;

/**
 * An implementation of {@link LightArrayBoolean} that uses native javascript arrays
 * 
 * @author Daniel Kurka
 * 
 */
public class JsLightArrayBoolean implements LightArrayBoolean {

	private JavaScriptObject array;

  /**
   * Construct a {@link JsLightArrayBoolean}
   */
	public JsLightArrayBoolean() {
		this(JavaScriptObject.createArray());
	}

  /**
   * Construct a {@link JsLightArrayBoolean} with a given javascript array
   * 
   * @param array the javascript array to use
   */
	public JsLightArrayBoolean(JavaScriptObject array) {
		this.array = array;
	}

	@Override
	public native void push(boolean value)/*-{
		this.@com.googlecode.mgwt.collection.client.JsLightArrayBoolean::array[this.@com.googlecode.mgwt.collection.client.JsLightArrayBoolean::array.length] = value;
  }-*/;

	@Override
	public native boolean shift() /*-{
		return this.@com.googlecode.mgwt.collection.client.JsLightArrayBoolean::array
				.shift();
  }-*/;

	@Override
	public native boolean get(int index) /*-{
		return this.@com.googlecode.mgwt.collection.client.JsLightArrayBoolean::array[index];
  }-*/;

	@Override
	public native void set(int index, boolean value) /*-{
		this.@com.googlecode.mgwt.collection.client.JsLightArrayBoolean::array[index] = value;
  }-*/;

	@Override
	public native int length()/*-{
		return this.@com.googlecode.mgwt.collection.client.JsLightArrayBoolean::array.length;
  }-*/;

	@Override
	public native void unshift(boolean value)/*-{
		this.@com.googlecode.mgwt.collection.client.JsLightArrayBoolean::array
				.unshift(value);
  }-*/;

  /**
   * get the underlying javascript array
   * 
   * @return the javascript array
   */
	public JavaScriptObject getArray() {
		return array;
	}

}
