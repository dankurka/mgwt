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
package de.kurka.mobile.showcase.client.activities.scrollwidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.panel.RoundPanel;
import de.kurka.gwt.mobile.ui.client.panel.ScrollPanel;

/**
 * @author Daniel Kurka
 *
 */
public class ScrollWidgetViewImpl implements ScrollWidgetView {

	private FlowPanel main;
	private HeaderBackButton headerBackButton;

	public ScrollWidgetViewImpl() {

		main = new FlowPanel();

		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.getTitleWidget().setText("Scroll Widget");

		headerBackButton = new HeaderBackButton();
		headerBackButton.setText("UI");
		headerPanel.setLeftWidget(headerBackButton);

		main.add(headerPanel);

		ScrollPanel scrollPanel = new ScrollPanel();

		scrollPanel.setSize("320px", "320px");

		RoundPanel roundPanel = new RoundPanel();
		for (int i = 0; i < 100; i++) {
			roundPanel.add(new HTML("big html content <br/> and stuff"));
		}

		scrollPanel.setWidget(roundPanel);

		main.add(scrollPanel);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return headerBackButton;
	}

}
