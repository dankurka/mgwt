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
import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.Button;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.panel.PopupPanel;

/**
 * @author Daniel Kurka
 *
 */
public class PopupViewGwtImpl implements PopupView {

	private FlowPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton backButton;

	/**
	 * 
	 */
	public PopupViewGwtImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();

		headerPanel.getTitleWidget().setText("Popups");
		backButton = new HeaderBackButton();

		backButton.setText("UI");

		headerPanel.setLeftWidget(backButton);

		main.add(headerPanel);

		Button button = new Button("Popup");
		main.add(button);

		final PopupPanel popupPanel = new PopupPanel();

		final Button redButton = new Button("Important");
		redButton.setImportant(true);
		popupPanel.add(redButton);

		final Button okButton = new Button("Ok");
		okButton.setConfirm(true);
		popupPanel.add(okButton);

		final Button button2 = new Button("Close");
		popupPanel.add(button2);

		button2.addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {
				popupPanel.hide();

			}
		});

		popupPanel.add(button2);

		button.addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {

				popupPanel.show();

			}
		});

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
