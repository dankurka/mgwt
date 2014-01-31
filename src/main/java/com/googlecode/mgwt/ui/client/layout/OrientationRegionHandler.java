package com.googlecode.mgwt.ui.client.layout;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.animation.AnimatableDisplay;

/**
 * @author Daniel Kurka
 */
public class OrientationRegionHandler {
	private final HasOneWidget landscapeDisplay;
	private final HasOneWidget portraitDisplay;
	private final AnimatableDisplay display;

	/**
	 * <p>
	 * Constructor for OrientationRegionHandler.
	 * </p>
	 * 
	 * @param landscapeDisplay a
	 *            {@link com.google.gwt.user.client.ui.HasOneWidget} object.
	 * @param portraitDisplay a
	 *            {@link com.google.gwt.user.client.ui.HasOneWidget} object.
	 * @param display a {@link com.googlecode.mgwt.mvp.client.AnimatableDisplay}
	 *            object.
	 */
	public OrientationRegionHandler(HasOneWidget landscapeDisplay, HasOneWidget portraitDisplay, AnimatableDisplay display) {
		this.landscapeDisplay = landscapeDisplay;
		this.portraitDisplay = portraitDisplay;
		this.display = display;
		MGWT.addOrientationChangeHandler(new InternalOrientationChangeHandler());
		changeDisplay(MGWT.getOrientation());
	}

	private void changeDisplay(ORIENTATION o) {

		switch (o) {
		case LANDSCAPE:
			landscapeDisplay.setWidget(display);
			break;
		case PORTRAIT:
			portraitDisplay.setWidget(display);
			break;

		default:
			break;
		}
	}

	private class InternalOrientationChangeHandler implements OrientationChangeHandler {
		@Override
		public void onOrientationChanged(OrientationChangeEvent event) {
			changeDisplay(event.getOrientation());

		}
	}

}
