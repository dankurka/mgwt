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
import com.googlecode.mgwt.collection.shared.LightMap;

public class JsLightMap<V> implements LightMap<V> {
	private JavaScriptObject map;

	public JsLightMap() {
		this(JavaScriptObject.createObject());
	}

	public JsLightMap(JavaScriptObject data) {
		if (data == null) {
			throw new IllegalArgumentException("data must not be null");
		}
		this.map = data;
	}

	@Override
	public final void clear() {
		clearData();
	}

	private final native void clearData() /*-{
		this.@com.googlecode.mgwt.collection.client.JsLightMap::map = {};
	}-*/;

	@Override
	public final native boolean containsKey(String key) /*-{
		return (this.@com.googlecode.mgwt.collection.client.JsLightMap::map)[key] != null;
	}-*/;

	@Override
	public void remove(String key) {
		nativeDelete(key);
	}

	private native V nativeDelete(String key) /*-{
		delete (this.@com.googlecode.mgwt.collection.client.JsLightMap::map)[key];
	}-*/;

	@Override
	public V get(String key) {
		return nativeGet(key);
	}

	private native V nativeGet(String key) /*-{
		return (this.@com.googlecode.mgwt.collection.client.JsLightMap::map)[key];
	}-*/;

	@Override
	public void put(String key, V value) {
		nativePut(key, value);

	}

	private native V nativePut(String key, V value) /*-{
		(this.@com.googlecode.mgwt.collection.client.JsLightMap::map)[key] = value;
	}-*/;

	@Override
	public LightArray<String> getKeys() {
		JavaScriptObject array = getNativeKeyArray();
		return new JsLightArray<String>(array);
	}

	private native JavaScriptObject getNativeKeyArray()/*-{
		var array = [];
		for ( var key in this.@com.googlecode.mgwt.collection.client.JsLightMap::map) {
			array.push(key);
		}
		return array;
	}-*/;

	public JavaScriptObject getMap() {
		return map;
	}

}
