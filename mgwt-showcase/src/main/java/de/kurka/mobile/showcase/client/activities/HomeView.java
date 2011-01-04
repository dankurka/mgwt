/**
 * 15.11.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;
import de.kurka.mobile.showcase.client.Topic;

/**
 * @author kurt
 *
 */
public interface HomeView extends IsWidget {

	public void setTitle(String text);

	public void setRightButtonText(String text);

	public HasSimpleTouchHandler getAboutButton();

	public HasCellSelectedHandler getCellSelectedHandler();

	/**
	 * @param createTopicsList
	 */
	public void setTopics(List<Topic> createTopicsList);

	public HasText getFirstHeader();

}
