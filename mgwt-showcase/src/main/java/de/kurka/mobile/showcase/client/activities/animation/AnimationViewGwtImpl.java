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
package de.kurka.mobile.showcase.client.activities.animation;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.widget.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.widget.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.celllist.CellListWithHeader;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;
import de.kurka.mobile.showcase.client.BasicCell;

/**
 * @author Daniel Kurka
 *
 */
public class AnimationViewGwtImpl implements AnimationView {

	private CellListWithHeader<Animation> list;
	private FlowPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton headerBackButton;

	/**
	 * 
	 */
	public AnimationViewGwtImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();

		headerBackButton = new HeaderBackButton();
		headerPanel.setLeftWidget(headerBackButton);

		main.add(headerPanel);

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
		main.add(list);

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
	public HasSimpleTouchHandler getBackButton() {
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
