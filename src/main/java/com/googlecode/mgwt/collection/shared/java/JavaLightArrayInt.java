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

import com.googlecode.mgwt.collection.shared.LightArrayInt;

/**
 * Java implemntation of {@link LightArrayInt}
 * 
 * @author Daniel Kurka
 * 
 */
public class JavaLightArrayInt implements LightArrayInt {

	private JavaLightArray<Integer> array;

  /**
   * Construct a {@link JavaLightArray}
   */
	public JavaLightArrayInt() {
		array = new JavaLightArray<Integer>();
	}

	@Override
	public int get(int index) {
		Integer integer = array.get(index);
		if (integer != null)
			return integer;
		return 0;
	}

	@Override
	public int length() {
		return array.length();
	}

	@Override
	public void push(int value) {
		array.push(Integer.valueOf(value));

	}

	@Override
	public void set(int index, int value) {
		array.set(index, Integer.valueOf(value));

	}

	@Override
	public int shift() {
		Integer shift = array.shift();
		if (shift != null)
			return shift;
		return 0;
	}

	@Override
	public void unshift(int value) {
		array.unshift(Integer.valueOf(value));

	}

}
