/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.user.client.ui.IsWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;

/**
 * @author kurt
 *
 */
public interface AboutView extends IsWidget {
	public void setBackButtonText(String text);

	public HasSimpleTouchHandler getBackButton();

}
