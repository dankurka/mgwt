/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.HeaderRoundButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.celllist.CellListWithHeader;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;
import de.kurka.mobile.showcase.client.BasicCell;
import de.kurka.mobile.showcase.client.Topic;

/**
 * @author kurt
 *
 */
public class HomeViewImpl implements HomeView {

	private FlowPanel main;
	private HeaderRoundButton forwardButton;
	private HeaderPanel headerPanel;
	private CellListWithHeader<Topic> cellList;

	public HomeViewImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();

		forwardButton = new HeaderRoundButton();
		headerPanel.setRightWidget(forwardButton);
		main.add(headerPanel);

		cellList = new CellListWithHeader<Topic>(new BasicCell<Topic>() {

			@Override
			public String getDisplayString(Topic model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(Topic model) {
				return true;
			}
		});
		main.add(cellList);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void setTitle(String text) {
		headerPanel.getTitleWidget().setText(text);

	}

	@Override
	public void setRightButtonText(String text) {
		forwardButton.setText(text);

	}

	@Override
	public HasSimpleTouchHandler getAboutButton() {
		return forwardButton;
	}

	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {
		return cellList.getCellList();
	}

	/* (non-Javadoc)
	 * @see de.kurka.mobile.showcase.client.activities.HomeView#setTopics(java.util.List)
	 */
	@Override
	public void setTopics(List<Topic> createTopicsList) {
		cellList.getCellList().render(createTopicsList);

	}

	@Override
	public HasText getFirstHeader() {
		return cellList.getHeader();
	}

}
