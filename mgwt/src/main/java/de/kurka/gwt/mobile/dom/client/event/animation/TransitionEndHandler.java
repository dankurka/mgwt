package de.kurka.gwt.mobile.dom.client.event.animation;

import com.google.gwt.event.shared.EventHandler;

public interface TransitionEndHandler extends EventHandler {
	public void onTransitionEnd(TransitionEndEvent event);
}
