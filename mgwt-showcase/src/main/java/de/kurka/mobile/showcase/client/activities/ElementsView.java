/**
 * 30.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.user.client.ui.IsWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;

/**
 * @author kurt
 *
 */
public interface ElementsView extends IsWidget {

	public HasSimpleTouchHandler getBackButton();

}
