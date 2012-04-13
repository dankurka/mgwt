/*
 * 
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
package com.googlecode.mgwt.ui.client.util;

/**
 * Considered internal
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class FeatureDetection {

	private static boolean has3d;
	private static boolean testedFor3d;

	/**
	 * @deprecated use: {@link CssUtil#has3d()}
	 * @return
	 */
	@Deprecated
	public static boolean has3d() {
		if (!testedFor3d) {

			has3d = _has3d();
			testedFor3d = true;
		}
		return has3d;
	}

	private static native boolean _has3d()/*-{
		return ('WebKitCSSMatrix' in $wnd && 'm11' in new WebKitCSSMatrix())
	}-*/;

}
