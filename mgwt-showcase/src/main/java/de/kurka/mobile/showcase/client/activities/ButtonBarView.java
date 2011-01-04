/**
 * 04.01.2011
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.user.client.ui.IsWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;

/**
 * @author kurt
 *
 */
public interface ButtonBarView extends IsWidget {
	public HasSimpleTouchHandler getBackButton();
}
