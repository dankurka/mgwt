/**
 * 17.10.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.panel;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.ui.client.canvas.CssCanvas;
import de.kurka.gwt.mobile.ui.client.util.CssUtil;
import de.kurka.gwt.mobile.ui.client.util.FeatureDetection;

/**
 * @author kurt
 *
 */
public class Scrollbar extends Widget {

	private final Orientation orientation;

	private static int global_uuid = 0;

	private int uuid;

	private Element bar;

	private final boolean has3d;

	private int maxSize;

	private int size;

	private int maxScroll;

	private double wrapperSize;

	private boolean shrink = true;

	private double wrapperProp;

	public static enum Orientation {
		HORIZONTAL, VERTICAL
	}

	/**
	 * 
	 */
	public Scrollbar(Orientation orientation, boolean has3d, int scrollAreaSize, int wholeSize) {
		this.has3d = has3d;
		this.orientation = orientation;
		uuid = ++global_uuid;
		maxSize = scrollAreaSize - 10;
		size = Math.max(Math.round(maxSize * maxSize / wholeSize), 6);
		maxScroll = maxSize - size;
		wrapperSize = ((double) scrollAreaSize) / (wholeSize);
		wrapperProp = ((double) maxScroll / (scrollAreaSize - wholeSize));

		System.out.println("whole: " + wholeSize);
		System.out.println("scrollarea: " + scrollAreaSize);

		System.out.println("size: " + size);
		System.out.println("maxscroll: " + maxScroll);
		System.out.println("wrapperSize: " + wrapperSize);

		setElement(DOM.createDiv());

		setStylePrimaryName("mgwt-Scrollbar");

		switch (orientation) {
		case HORIZONTAL:
			addStyleDependentName("horizontal");
			break;
		case VERTICAL:
			addStyleDependentName("vertical");

		}

		applyStyle(getElement(), "-webkit-mask:-webkit-canvas(scrollbar" + uuid + ");");

		bar = DOM.createDiv();

		bar.addClassName("mgwt-Scrollbar-Bar");

		String cssText = "";
		switch (orientation) {
		case HORIZONTAL:

			bar.addClassName("mgwt-Scrollbar-Bar-horizontal");
			break;

		case VERTICAL:

			bar.addClassName("mgwt-Scrollbar-Bar-vertical");
			break;

		}

		if (has3d) {
			cssText += "-webkit-transform: translate3d(0px,0px, 0px);";
		} else {
			cssText += "-webkit-transform: translate(0px,0px);";
		}

		switch (orientation) {
		case HORIZONTAL:
			cssText += "width: " + size + "px; height: 5px;";
			break;

		case VERTICAL:
			cssText += "height: " + size + "px; width: 5px;";
			break;

		}

		applyStyle(bar, cssText);

		DOM.appendChild(getElement(), bar);

		switch (orientation) {
		case HORIZONTAL:
			CssCanvas canvas = CssCanvas.getCssCanvasContext("2d", "scrollbar" + uuid, maxSize, 5);
			canvas.setFillStyle("rgb(0,0,0)");
			canvas.beginPath();
			canvas.arc(2.5, 2.5, 2.5, Math.PI / 2, -Math.PI / 2, false);
			canvas.lineTo(maxSize - 2.5, 0);
			canvas.arc(maxSize - 2.5, 2.5, 2.5, -Math.PI / 2, Math.PI / 2, false);
			canvas.closePath();
			canvas.fill();
			break;

		case VERTICAL:
			CssCanvas canvas1 = CssCanvas.getCssCanvasContext("2d", "scrollbar" + uuid, 5, maxSize);
			canvas1.setFillStyle("rgb(0,0,0)");
			canvas1.beginPath();
			canvas1.arc(2.5, 2.5, 2.5, Math.PI, 0, false);
			canvas1.lineTo(5, maxSize - 2.5);
			canvas1.arc(2.5, maxSize - 2.5, 2.5, 0, Math.PI, false);
			canvas1.closePath();
			canvas1.fill();
			break;

		}

	}

	private native void applyStyle(Element el, String styleText)/*-{
		el.style.cssText = styleText;
	}-*/;

	public void setPosition(int pos) {
		show();
		pos = (int) Math.round(wrapperProp * pos);

		System.out.println("scroll pos: " + pos);

		if (pos < 0) {
			pos = shrink ? pos + pos * 3 : 0;
			if (size + pos < 7) {
				pos = -size + 6;
			}
		} else if (pos > maxScroll) {
			pos = shrink ? pos + (pos - maxScroll) * 3 : maxScroll;
			if (size + maxScroll - pos < 7) {
				pos = size + maxScroll - 6;
			}
		}

		switch (orientation) {
		case HORIZONTAL:
			CssUtil.translate(bar, pos, 0);

			break;

		case VERTICAL:
			CssUtil.translate(bar, 0, pos);
			break;

		}

	}

	private native void setTransform(Element el, String transform)/*-{
		el.style.webkitTransform = transform;
	}-*/;

	public void hide() {
		if (FeatureDetection.has3d()) {
			CssUtil.setWebKitTransitionsDelay(getElement(), 350);
		}

		CssUtil.setOpacity(getElement(), 0);
	}

	public void show() {
		if (FeatureDetection.has3d()) {
			CssUtil.setWebKitTransitionsDelay(getElement(), 0);
		}

		CssUtil.setOpacity(getElement(), 1);

	}

	/**
	 * @param milliseconds
	 */
	public void setTransitionTime(int milliseconds) {
		if (FeatureDetection.has3d()) {
			CssUtil.setWebKitTransitionDuration(getElement(), 300);
		} else {
			CssUtil.setWebKitTransitionDuration(getElement(), 0);
		}

		CssUtil.setWebKitTransitionDuration(bar, milliseconds);

	}
}
