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

/**
 * 
 * A lightmap is a key value store that uses a native implementation if available.
 * 
 * @author Daniel Kurka
 * 
 * @param <V> the type of object to store
 */
public interface LightMap<V> {

  /**
   * remove all objects from this map
   */
	public void clear();

  /**
   * does the map contain a key
   * 
   * @param key the key to test for
   * @return true if the key is part of the map
   */
	public boolean containsKey(String key);

  /**
   * get all keys for this map
   * 
   * @return the keys of this map
   */
	public LightArray<String> getKeys();

  /**
   * remove a value from the map
   * 
   * @param key
   */
	public void remove(String key);

  /**
   * get a value from the map
   * 
   * @param key the key to use
   * @return the value
   */
	public V get(String key);

  /**
   * put a value into the map
   * 
   * @param key the key
   * @param value the value
   */
	public void put(String key, V value);
}
