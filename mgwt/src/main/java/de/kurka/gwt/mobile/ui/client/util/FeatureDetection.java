/**
 * 19.10.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.util;

/**
 * @author kurt
 *
 */
public class FeatureDetection {

	private static boolean has3d;
	private static boolean testedFor3d;

	public static boolean has3d() {
		if (!testedFor3d) {
			has3d = _has3d();
		}
		return has3d;
	}

	private static native boolean _has3d()/*-{
		return ('WebKitCSSMatrix' in $wnd && 'm11' in new WebKitCSSMatrix())
	}-*/;
}
