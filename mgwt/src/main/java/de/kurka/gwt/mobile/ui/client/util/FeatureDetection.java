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
package de.kurka.gwt.mobile.ui.client.util;

/**
 * @author Daniel Kurka
 *
 */
public class FeatureDetection {

	private static boolean has3d;
	private static boolean testedFor3d;

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

	private static boolean isPad;
	private static boolean testedForIpad;

	private static boolean testedForIPhone;
	private static boolean isiPhone;

	public static boolean isPad() {
		if (!testedForIpad) {
			isPad = _isiPad();
			testedForIpad = true;
		}
		return isPad;
	}

	public static boolean isiPhone() {
		if (!testedForIPhone) {
			isiPhone = _isiPhone();
			testedForIPhone = true;
		}
		return isiPhone;
	}

	/**
	 * @return
	 */
	private static native boolean _isiPad() /*-{
		return $wnd.navigator.userAgent.toUpperCase().indexOf("IPAD")!=-1
	}-*/;

	private static native boolean _isiPhone() /*-{
		return $wnd.navigator.userAgent.toUpperCase().indexOf("IPHONE")!=-1
	}-*/;

	/**
	 * @return
	 */
	public static boolean isiOs() {
		return isiPhone() || isPad();
	}
}
