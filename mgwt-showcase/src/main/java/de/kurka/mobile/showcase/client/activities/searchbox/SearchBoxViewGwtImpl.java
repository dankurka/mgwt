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
package de.kurka.mobile.showcase.client.activities.searchbox;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.MGWTUtil;
import de.kurka.gwt.mobile.ui.client.widget.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.widget.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.MSearchBox;

/**
 * @author Daniel Kurka
 *
 */
public class SearchBoxViewGwtImpl implements SearchBoxView {

	private FlowPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton backButton;

	public SearchBoxViewGwtImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();

		headerPanel.setCenter("SearchBox");
		backButton = new HeaderBackButton();

		backButton.setText("UI");
		if (MGWTUtil.getFeatureDetection().isPhone()) {
			headerPanel.setLeftWidget(backButton);
		}
		main.add(headerPanel);
		FormPanel formPanel = new FormPanel("");
		formPanel.addSubmitHandler(new SubmitHandler() {

			@Override
			public void onSubmit(SubmitEvent event) {
				event.cancel();

			}
		});
		MSearchBox searchBox = new MSearchBox();
		formPanel.setWidget(searchBox);
		main.add(formPanel);
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	/* (non-Javadoc)
	 * @see de.kurka.mobile.showcase.client.activities.SearchBoxView#getBackButton()
	 */
	@Override
	public HasSimpleTouchHandler getBackButton() {
		return backButton;
	}

}
