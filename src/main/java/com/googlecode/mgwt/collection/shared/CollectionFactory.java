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
package com.googlecode.mgwt.collection.shared;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.collection.client.JsLightArray;
import com.googlecode.mgwt.collection.client.JsLightArrayInteger;
import com.googlecode.mgwt.collection.client.JsLightMap;
import com.googlecode.mgwt.collection.shared.java.JavaLightArray;
import com.googlecode.mgwt.collection.shared.java.JavaLightArrayInt;
import com.googlecode.mgwt.collection.shared.java.JavaLightMap;

/**
 * A factory to create instances of the light collections api.
 * 
 * If this is run in production code javascript objects are used to speed up collections. If run
 * inside a JVM / dev mode the java implementation is used
 * 
 * @author Daniel Kurka
 * 
 */
public class CollectionFactory {
  /**
   * Construct a LightMap
   * 
   * @param <V> the type of the map
   * @return the map
   */
	public static <V> LightMap<V> constructMap() {
		if (GWT.isProdMode()) {
			return new JsLightMap<V>();
		} else {
			return new JavaLightMap<V>();
		}
	}

  /**
   * Construct a {@link LightArray}
   * 
   * @param <V> the type of objects that go into the array
   * @return the array
   */
	public static <V> LightArray<V> constructArray() {
		if (GWT.isProdMode()) {
			return new JsLightArray<V>();
		} else {
			return new JavaLightArray<V>();
		}
	}

  /**
   * Construct an array of integers
   * 
   * @return the array
   */
	public static LightArrayInt constructIntegerArray() {
		if (GWT.isProdMode()) {
			return new JsLightArrayInteger();
		} else {
			return new JavaLightArrayInt();
		}
	}
}
