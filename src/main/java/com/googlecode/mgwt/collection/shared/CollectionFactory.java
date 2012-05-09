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

public class CollectionFactory {
	public static <V> LightMap<V> constructMap() {
		if (GWT.isClient()) {
			return new JsLightMap<V>();
		} else {
			return new JavaLightMap<V>();
		}
	}

	public static <V> LightArray<V> constructArray() {
		if (GWT.isClient()) {
			return new JsLightArray<V>();
		} else {
			return new JavaLightArray<V>();
		}
	}
	
	public static LightArrayInt constructIntegerArray(){
		if(GWT.isClient()){
			return new JsLightArrayInteger();
		}else{
			return new JavaLightArrayInt();
		}
	}
}
