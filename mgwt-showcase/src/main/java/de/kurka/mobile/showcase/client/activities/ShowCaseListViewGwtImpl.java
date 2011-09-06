/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.kurka.mobile.showcase.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.HeaderRoundButton;
import de.kurka.gwt.mobile.ui.client.widget.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.celllist.CellListWithHeader;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;
import de.kurka.mobile.showcase.client.BasicCell;
import de.kurka.mobile.showcase.client.activities.home.Topic;

/**
 * @author Daniel Kurka
 *
 */
public class ShowCaseListViewGwtImpl implements ShowCaseListView {

	private FlowPanel main;
	private HeaderRoundButton forwardButton;
	private HeaderPanel headerPanel;
	private CellListWithHeader<Topic> cellList;

	public ShowCaseListViewGwtImpl() {
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
		headerPanel.setCenter(text);

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

	@Override
	public void setTopics(List<Topic> createTopicsList) {
		cellList.getCellList().render(createTopicsList);

	}

	@Override
	public HasText getFirstHeader() {
		return cellList.getHeader();
	}

}
