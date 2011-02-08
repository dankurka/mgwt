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

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.Button;
import de.kurka.gwt.mobile.ui.client.panel.RoundPanel;

/**
 * @author kurt
 *
 */
public class AnimationDoneViewImpl implements AnimationDoneView {

	private RoundPanel panel;
	private Button button;

	/**
	 * 
	 */
	public AnimationDoneViewImpl() {
		panel = new RoundPanel();

		panel.add(new HTML("toll, wa?"));

		button = new Button();
		button.setText("Back");
		panel.add(button);

	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return button;
	}

}
