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

import java.util.ArrayList;
import java.util.Set;

import com.googlecode.mgwt.collection.shared.LightArray;

public class JavaLightArray<T> implements LightArray<T> {

	private ArrayList<T> list;

	public JavaLightArray() {
		list = new ArrayList<T>();
	}

	@Override
	public T shift() {
		return list.remove(0);
	}

	@Override
	public T get(int index) {
		// behave like js!
		if (index < 0 || index >= list.size())
			return null;
		return list.get(index);
	}

	@Override
	public void set(int index, T value) {
		// behave like js!
		if (index < 0)
			return;
		if (index >= list.size()) {
			for (int i = list.size(); i < index; i++) {
				list.add(null);
			}
			list.add(value);
		} else {
			list.set(index, value);
		}

	}

	@Override
	public int length() {
		return list.size();
	}

	@Override
	public void unshift(T value) {
		list.add(0, value);

	}

	@Override
	public void push(T value) {
		list.add(value);

	}

	public static <T> JavaLightArray<T> fromSet(Set<T> set) {
		JavaLightArray<T> array = new JavaLightArray<T>();
		for (T t : set) {
			array.push(t);
		}
		return array;
	}

}
