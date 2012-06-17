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
 * An array that can store primitive ints
 * 
 * @author Daniel Kurka
 * 
 */
public interface LightArrayInt {
  /**
   * pop a value from the array
   * 
   * @return the value
   */
  public int shift();

  /**
   * get the object at a specific index
   * 
   * @param index the index
   * @return the object or null
   */
	public int get(int index);

  /**
   * put an object to a given index. the array autoexpands and fills up missing values with null
   * 
   * @param index the index
   * @param value the value to store
   */
	public void set(int index, int value);

  /**
   * the length of the array
   * 
   * @return the length of the array
   */
	public int length();

  /**
   * push a value onto the array
   * 
   * @param value to push
   */
	public void unshift(int value);

  /**
   * push a value on to the array
   * 
   * @param value
   */
  public void push(int value);
}
