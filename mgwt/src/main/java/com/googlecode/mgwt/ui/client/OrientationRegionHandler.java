package com.googlecode.mgwt.ui.client;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;

public class OrientationRegionHandler {
	private final HasOneWidget landscapeDisplay;
	private final HasOneWidget portraitDisplay;
	private final AnimatableDisplay display;

	public OrientationRegionHandler(HasOneWidget landscapeDisplay, HasOneWidget portraitDisplay, AnimatableDisplay display) {
		this.landscapeDisplay = landscapeDisplay;
		this.portraitDisplay = portraitDisplay;
		this.display = display;
		MGWTUtil.addOrientationChangeHandler(new InternalOrientationChangeHandler());
		changeDisplay(MGWTUtil.getOrientation());
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
