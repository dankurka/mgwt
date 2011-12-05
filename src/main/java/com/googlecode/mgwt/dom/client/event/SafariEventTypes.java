package com.googlecode.mgwt.dom.client.event;

public class SafariEventTypes implements EventTypes{

	@Override
	public String getAnimationEnd() {
		return "webkitAnimationEnd";
	}

	@Override
	public String getTransistionEnd() {
		return "webkitTransitionEnd";
	}
}
