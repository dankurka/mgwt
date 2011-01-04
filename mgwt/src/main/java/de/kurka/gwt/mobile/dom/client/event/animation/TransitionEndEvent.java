package de.kurka.gwt.mobile.dom.client.event.animation;

import com.google.gwt.event.dom.client.DomEvent;

public class TransitionEndEvent extends DomEvent<TransitionEndHandler> {

	private static final Type<TransitionEndHandler> TYPE = new Type<TransitionEndHandler>("webkittransitionend", new TransitionEndEvent());

	/**
	   * Gets the event type associated with mouse down events.
	   * 
	   * @return the handler type
	   */
	public static Type<TransitionEndHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.dom.client.DomEvent.Type<TransitionEndHandler> getAssociatedType() {
		return TYPE;
	}

	/**
	   * Protected constructor, use
	   * {@link DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent, com.google.gwt.event.shared.HasHandlers)}
	   * to fire mouse down events.
	   */
	protected TransitionEndEvent() {

	}

	@Override
	protected void dispatch(TransitionEndHandler handler) {
		handler.onTransitionEnd(this);

	}

}