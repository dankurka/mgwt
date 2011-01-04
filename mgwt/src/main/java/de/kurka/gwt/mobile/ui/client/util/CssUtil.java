/**
 * 19.10.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.util;

import com.google.gwt.user.client.Element;

/**
 * @author kurt
 *
 */
public class CssUtil {
	public static void translate(Element el, int x, int y) {
		String cssText = null;
		if (FeatureDetection.has3d()) {
			cssText = "translate3d(" + x + "px, " + y + "px, 0px)";
		} else {
			cssText = "translate( " + x + "px, " + y + "px )";
		}

		_translate(el, cssText);

	}

	private native static void _translate(Element el, String css)/*-{
		el.style.webkitTransform = css;
	}-*/;

	public native static void setWebKitTransitionsDelay(Element el, int milliseconds)/*-{
		el.style.webkitTransitionDelay = milliseconds + "ms";
	}-*/;

	public native static void setOpacity(Element el, double opacity)/*-{
		el.style.opacity = opacity;
	}-*/;

	public native static void setWebKitTransitionDuration(Element el, int time)/*-{
		el.style.webkitTransitionDuration = time + "ms";
	}-*/;
}
