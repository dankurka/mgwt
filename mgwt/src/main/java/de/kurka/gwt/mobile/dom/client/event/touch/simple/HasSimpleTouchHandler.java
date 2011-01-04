/**
 * 15.11.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.dom.client.event.touch.simple;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * @author kurt
 *
 */
public interface HasSimpleTouchHandler {
	public HandlerRegistration addSimpleTouchHandler(SimpleTouchHandler handler);
}
