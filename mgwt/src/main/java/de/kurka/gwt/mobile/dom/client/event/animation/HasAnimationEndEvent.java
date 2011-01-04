/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.dom.client.event.animation;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * @author kurt
 *
 */
public interface HasAnimationEndEvent {
	public HandlerRegistration addAnimationEndHandler(AnimationEndHandler handler);
}
