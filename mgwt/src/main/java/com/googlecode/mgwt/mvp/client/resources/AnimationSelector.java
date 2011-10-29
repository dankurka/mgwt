/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.mvp.client.resources;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.mvp.client.AnimationNames;
import com.googlecode.mgwt.ui.client.MGWTUtil;

/**
 * Considered internal
 * 
 * @author Daniel Kurka
 * 
 */
public class AnimationSelector {

	private static final MVPBundle BUNDLE = GWT.create(MVPBundle.class);

	public static AnimationNames getNames() {
		if (MGWTUtil.getOsDetection().isAndroid()) {
			return BUNDLE.transitionCss();
		} else {
			return BUNDLE.animationCss();
		}
	}

	public static MVPBundle getBundle() {
		return BUNDLE;
	}

}
