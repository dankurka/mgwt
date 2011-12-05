package com.googlecode.mgwt.dom.client.event;

public class MozEventTypes implements EventTypes{

	@Override
	public String getAnimationEnd() {
		return "animationend";
	}

	@Override
	public String getTransistionEnd() {
		return "transitionend";
	}

}
