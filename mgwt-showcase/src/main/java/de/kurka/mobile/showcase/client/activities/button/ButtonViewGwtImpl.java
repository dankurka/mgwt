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
package de.kurka.mobile.showcase.client.activities.button;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.MGWTUtil;
import de.kurka.gwt.mobile.ui.client.widget.Button;
import de.kurka.gwt.mobile.ui.client.widget.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.widget.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.LayoutPanel;
import de.kurka.gwt.mobile.ui.client.widget.ScrollPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class ButtonViewGwtImpl implements ButtonView {

	private LayoutPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton backButton;

	public ButtonViewGwtImpl() {
		main = new LayoutPanel();

		headerPanel = new HeaderPanel();

		headerPanel.setCenter("Buttons");
		backButton = new HeaderBackButton();

		backButton.setText("UI");

		if (MGWTUtil.getFeatureDetection().isPhone()) {
			headerPanel.setLeftWidget(backButton);
		}

		main.add(headerPanel);

		FlowPanel content = new FlowPanel();

		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledX(false);

		Button normalButton = new Button("Normal");
		content.add(normalButton);

		Button roundButton = new Button("Round");
		roundButton.setRound(true);
		content.add(roundButton);

		Button smallButton = new Button("Small");
		smallButton.setSmall(true);
		content.add(smallButton);

		Button importantButton = new Button("Important");
		importantButton.setImportant(true);
		content.add(importantButton);

		Button importantRoundButton = new Button("Round");
		importantRoundButton.setImportant(true);
		importantRoundButton.setRound(true);
		content.add(importantRoundButton);

		Button importantSmallButton = new Button("Small");
		importantSmallButton.setImportant(true);
		importantSmallButton.setSmall(true);

		content.add(importantSmallButton);

		Button conmfirmButton = new Button("Confirm");
		conmfirmButton.setConfirm(true);
		content.add(conmfirmButton);

		Button confirmRoundButton = new Button("Round");
		confirmRoundButton.setConfirm(true);
		importantRoundButton.setRound(true);
		content.add(confirmRoundButton);

		Button confirmSmallButton = new Button("Small");
		confirmSmallButton.setConfirm(true);
		confirmSmallButton.setSmall(true);

		content.add(confirmSmallButton);

		scrollPanel.setWidget(content);

		main.add(scrollPanel);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return backButton;
	}

}
