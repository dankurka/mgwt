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
 * A light array implements the same interface as a javascript array.
 * 
 * @author Daniel Kurka
 * 
 * @param <T> the type of object to store
 */
public interface LightArray<T> {

  /**
   * get the object at a specific index
   * 
   * @param index the index
   * @return the object or null
   */
	public T get(int index);

  /**
   * put an object to a given index. the array autoexpands and fills up missing values with null
   * 
   * @param index the index
   * @param value the value to store
   */
	public void set(int index, T value);

  /**
   * the length of the array
   * 
   * @return the length of the array
   */
	public int length();

  /**
   * push a value on to the array
   * 
   * @param value
   */
	void push(T value);

  /**
   * pop a value from the array
   * 
   * @return the value
   */
	public T shift();

  /**
   * push a value onto the array
   * 
   * @param value to push
   */
	public void unshift(T value);

}
