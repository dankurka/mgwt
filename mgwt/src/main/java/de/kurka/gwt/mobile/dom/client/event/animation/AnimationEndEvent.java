/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.dom.client.event.animation;

import com.google.gwt.event.dom.client.DomEvent;

/**
 * @author kurt
 *
 */
public class AnimationEndEvent extends DomEvent<AnimationEndHandler> {
	private static final Type<AnimationEndHandler> TYPE = new Type<AnimationEndHandler>("webkitAnimationEnd", new AnimationEndEvent());

	/**
	   * Gets the event type associated with mouse down events.
	   * 
	   * @return the handler type
	   */
	public static Type<AnimationEndHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.dom.client.DomEvent.Type<AnimationEndHandler> getAssociatedType() {
		return TYPE;
	}

	/**
	   * Protected constructor, use
	   * {@link DomEvent#fireNativeEvent(com.google.gwt.dom.client.NativeEvent, com.google.gwt.event.shared.HasHandlers)}
	   * to fire mouse down events.
	   */
	protected AnimationEndEvent() {

	}

	@Override
	protected void dispatch(AnimationEndHandler handler) {
		handler.onAnimationEnd(this);

	}
}
