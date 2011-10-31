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
package com.googlecode.mgwt.examples.showcase.client.activities.animation;

import java.util.List;

import com.google.gwt.user.client.ui.HasText;
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
public class AnimationViewGwtImpl implements AnimationView {

	private CellListWithHeader<Animation> list;
	private LayoutPanel main;
	private HeaderPanel headerPanel;
	private HeaderButton headerBackButton;

	/**
	 * 
	 */
	public AnimationViewGwtImpl() {
		main = new LayoutPanel();

		headerPanel = new HeaderPanel();

		headerBackButton = new HeaderButton();

		headerPanel.setLeftWidget(headerBackButton);
		headerBackButton.setBackButton(true);

		main.add(headerPanel);

		ScrollPanel scrollPanel = new ScrollPanel();

		list = new CellListWithHeader<Animation>(new BasicCell<Animation>() {

			@Override
			public String getDisplayString(Animation model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(Animation model) {
				return true;
			}
		});

		list.getCellList().setRound(true);

		scrollPanel.setWidget(list);
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
	public HasTapEvent getBackButton() {
		return headerBackButton;
	}

	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {
		return list.getCellList();
	}

	@Override
	public void setLeftButtonText(String text) {
		headerBackButton.setText(text);

	}

	@Override
	public void setAnimations(List<Animation> animations) {
		list.getCellList().render(animations);

	}

	@Override
	public HasText getFirstHeader() {
		return list.getHeader();
	}

}
