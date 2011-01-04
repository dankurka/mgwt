/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.dom.client.event.animation;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author kurt
 *
 */
public interface AnimationEndHandler extends EventHandler {
	public void onAnimationEnd(AnimationEndEvent event);
}
