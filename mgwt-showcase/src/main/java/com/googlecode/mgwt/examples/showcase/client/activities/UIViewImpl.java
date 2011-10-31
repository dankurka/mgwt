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

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapEvent;
import com.googlecode.mgwt.examples.showcase.client.BasicCell;
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
public class UIViewImpl implements UIView {

	private LayoutPanel main;
	private HeaderPanel headerPanel;
	private HeaderButton headerBackButton;
	private CellListWithHeader<Item> cellListWithHeader;

	public UIViewImpl() {
		main = new LayoutPanel();

		main.getElement().setId("testdiv");

		headerPanel = new HeaderPanel();
		main.add(headerPanel);

		headerBackButton = new HeaderButton();
		headerBackButton.setBackButton(true);
		headerPanel.setLeftWidget(headerBackButton);

		ScrollPanel scrollPanel = new ScrollPanel();

		cellListWithHeader = new CellListWithHeader<Item>(new BasicCell<Item>() {

			@Override
			public String getDisplayString(Item model) {
				return model.getDisplayString();
			}

			@Override
			public boolean canBeSelected(Item model) {
				return true;
			}
		});
		cellListWithHeader.getCellList().setRound(true);
		scrollPanel.setWidget(cellListWithHeader);
		scrollPanel.setScrollingEnabledX(false);

		main.add(scrollPanel);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void setBackButtonText(String text) {
		headerBackButton.setText(text);

	}

	@Override
	public HasTapEvent getBackButton() {
		return headerBackButton;
	}

	@Override
	public void setTitle(String title) {
		headerPanel.setCenter(title);

	}

	@Override
	public HasCellSelectedHandler getList() {
		return cellListWithHeader.getCellList();
	}

	@Override
	public void renderItems(List<Item> items) {
		cellListWithHeader.getCellList().render(items);

	}

	@Override
	public void setSelectedIndex(int index, boolean selected) {
		cellListWithHeader.getCellList().setSelectedIndex(index, selected);

	}

}
