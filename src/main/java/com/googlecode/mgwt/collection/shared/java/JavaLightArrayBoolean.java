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

import com.googlecode.mgwt.collection.shared.LightArrayBoolean;

/**
 * 
 * Implementation of {@link LightArrayBoolean} for java environments
 * 
 * @author Daniel Kurka
 * 
 */
public class JavaLightArrayBoolean implements LightArrayBoolean {

	private JavaLightArray<Boolean> array;

  /**
   * Construct a {@link JavaLightArrayBoolean}
   */
	public JavaLightArrayBoolean() {
		array = new JavaLightArray<Boolean>();
	}

	@Override
	public boolean get(int index) {
		Boolean boolean1 = array.get(index);
		if (boolean1 != null)
			return boolean1;
		return false;
	}

	@Override
	public int length() {
		return array.length();
	}

	@Override
	public void push(boolean value) {
		array.push(value);

	}

	@Override
	public void set(int index, boolean value) {
		array.set(index, value);

	}

	@Override
	public boolean shift() {
		Boolean shift = array.shift();
		if (shift != null)
			return shift;
		return false;
	}

	@Override
	public void unshift(boolean value) {
		array.unshift(value);

	}

}
