package de.kurka.gwt.mobile.dom.client.event.animation;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasTransitionEndEvent {
	public HandlerRegistration addTransitionEndHandler(TransitionEndHandler handler);
}
