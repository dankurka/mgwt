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
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.panel.ScrollPanel;
import de.kurka.gwt.mobile.ui.client.widget.celllist.CellListWithHeader;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;
import de.kurka.mobile.showcase.client.BasicCell;

/**
 * @author Daniel Kurka
 *
 */
public class UIViewImpl implements UIView {

	private FlowPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton headerBackButton;
	private CellListWithHeader<Item> cellListWithHeader;

	public UIViewImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();
		main.add(headerPanel);

		headerBackButton = new HeaderBackButton();
		headerPanel.setLeftWidget(headerBackButton);

		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.constrainWidth(true);
		scrollPanel.constrainHeight(true);
		scrollPanel.setHasHeader(true);
		scrollPanel.setScrollingEnabledX(false);

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

		scrollPanel.setWidget(cellListWithHeader);

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
	public HasSimpleTouchHandler getBackButton() {
		return headerBackButton;
	}

	@Override
	public void setTitle(String title) {
		headerPanel.getTitleWidget().setText(title);

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
