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
package com.googlecode.mgwt.collection.shared.java;

import java.util.HashMap;
import java.util.Set;

import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.collection.shared.LightMap;

/**
 * Java implementation of {@link LightMap}
 * 
 * @author Daniel Kurka
 * 
 * @param <V> type of objects to store
 */
public class JavaLightMap<V> implements LightMap<V> {

	private HashMap<String, V> map;

  /**
   * Construct a {@link JavaLightMap}
   */
	public JavaLightMap() {
		map = new HashMap<String, V>();
	}

	@Override
	public void clear() {
		map.clear();

	}

	@Override
	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	@Override
	public LightArray<String> getKeys() {
		Set<String> keySet = map.keySet();
		return JavaLightArray.fromSet(keySet);
	}

	@Override
	public void remove(String key) {
		map.remove(key);
	}

	@Override
	public V get(String key) {
		return map.get(key);
	}

	@Override
	public void put(String key, V value) {
		map.put(key, value);
	}

}
