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
import com.googlecode.mgwt.collection.shared.LightArray;

public class JsLightArray<T> implements LightArray<T> {

	private JavaScriptObject array;

	public JsLightArray() {
		this(JavaScriptObject.createArray());
	}

	public JsLightArray(JavaScriptObject array) {
		this.array = array;
	}

	@Override
	public native T get(int index) /*-{
		return this.@com.googlecode.mgwt.collection.client.JsLightArray::array[index];
	}-*/;

	public JavaScriptObject getArray() {
		return array;
	}

	@Override
	public native int length()/*-{
		return this.@com.googlecode.mgwt.collection.client.JsLightArray::array.length;
	}-*/;

	@Override
	public native void push(T value)/*-{
		this.@com.googlecode.mgwt.collection.client.JsLightArray::array[this.@com.googlecode.mgwt.collection.client.JsLightArray::array.length] = value;
	}-*/;

	@Override
	public native void set(int index, T value) /*-{
		this.@com.googlecode.mgwt.collection.client.JsLightArray::array[index] = value;
	}-*/;

	@Override
	public native T shift() /*-{
		return this.@com.googlecode.mgwt.collection.client.JsLightArray::array
				.shift();
	}-*/;

	@Override
	public native void unshift(T value)/*-{
		this.@com.googlecode.mgwt.collection.client.JsLightArray::array
				.unshift(value);
	}-*/;

}
