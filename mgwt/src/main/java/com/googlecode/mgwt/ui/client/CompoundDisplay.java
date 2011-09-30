package com.googlecode.mgwt.ui.client;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;

public class CompoundDisplay {
	private final HasOneWidget landscapeDisplay;
	private final HasOneWidget portraitDisplay;
	private final AnimatableDisplay display;

	public CompoundDisplay(HasOneWidget landscapeDisplay, HasOneWidget portraitDisplay, AnimatableDisplay display) {
		this.landscapeDisplay = landscapeDisplay;
		this.portraitDisplay = portraitDisplay;
		this.display = display;
		MGWTUtil.addOrientationChangeHandler(new OrientationChangeHandler() {

			@Override
			public void onOrientationChanged(OrientationChangeEvent event) {
				changeDisplay(event.getOrientation());

			}
		});

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

}
