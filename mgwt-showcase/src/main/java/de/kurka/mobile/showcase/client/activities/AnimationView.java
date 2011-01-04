/**
 * 29.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;

/**
 * @author kurt
 *
 */
public interface AnimationView extends IsWidget {
	public void setTitle(String text);

	public void setLeftButtonText(String text);

	public HasSimpleTouchHandler getBackButton();

	public HasCellSelectedHandler getCellSelectedHandler();

	public void setAnimations(List<Animation> animations);

	public HasText getFirstHeader();
}
