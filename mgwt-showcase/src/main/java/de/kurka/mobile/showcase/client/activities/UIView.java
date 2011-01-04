/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;

/**
 * @author kurt
 *
 */
public interface UIView extends IsWidget {
	public void setBackButtonText(String text);

	public HasSimpleTouchHandler getBackButton();

	public void setTitle(String title);

	public HasCellSelectedHandler getList();

	public void renderItems(List<Item> items);
}
