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
package com.googlecode.mgwt.examples.showcase.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.examples.showcase.client.BasicCell;
import com.googlecode.mgwt.examples.showcase.client.activities.home.Topic;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellListWithHeader;
import com.googlecode.mgwt.ui.client.widget.celllist.HasCellSelectedHandler;

/**
 * @author Daniel Kurka
 * 
 */
public class ShowCaseListViewGwtImpl implements ShowCaseListView {

	private LayoutPanel main;
	private HeaderButton forwardButton;
	private HeaderPanel headerPanel;
	private CellListWithHeader<Topic> cellList;

	public ShowCaseListViewGwtImpl() {
		main = new LayoutPanel();

		headerPanel = new HeaderPanel();

		forwardButton = new HeaderButton();
		forwardButton.setForwardButton(true);
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

		cellList.getCellList().setRound(true);

		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setWidget(cellList);
		scrollPanel.setScrollingEnabledX(false);
		main.add(scrollPanel);

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
	public HasTapHandlers getAboutButton() {
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
