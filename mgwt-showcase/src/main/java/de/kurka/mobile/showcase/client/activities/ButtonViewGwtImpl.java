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

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.Button;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;

/**
 * @author Daniel Kurka
 *
 */
public class ButtonViewGwtImpl implements ButtonView {

	private FlowPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton backButton;

	public ButtonViewGwtImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();

		headerPanel.getTitleWidget().setText("Buttons");
		backButton = new HeaderBackButton();

		backButton.setText("UI");

		headerPanel.setLeftWidget(backButton);

		main.add(headerPanel);

		Button normalButton = new Button("Normal");
		main.add(normalButton);

		Button roundButton = new Button("Round");
		roundButton.setRound(true);
		main.add(roundButton);

		Button smallButton = new Button("Small");
		smallButton.setSmall(true);
		main.add(smallButton);

		Button importantButton = new Button("Important");
		importantButton.setImportant(true);
		main.add(importantButton);

		Button importantRoundButton = new Button("Round");
		importantRoundButton.setImportant(true);
		importantRoundButton.setRound(true);
		main.add(importantRoundButton);

		Button importantSmallButton = new Button("Small");
		importantSmallButton.setImportant(true);
		importantSmallButton.setSmall(true);

		main.add(importantSmallButton);

		Button conmfirmButton = new Button("Confirm");
		conmfirmButton.setConfirm(true);
		main.add(conmfirmButton);

		Button confirmRoundButton = new Button("Round");
		confirmRoundButton.setConfirm(true);
		importantRoundButton.setRound(true);
		main.add(confirmRoundButton);

		Button confirmSmallButton = new Button("Small");
		confirmSmallButton.setConfirm(true);
		confirmSmallButton.setSmall(true);

		main.add(confirmSmallButton);

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
