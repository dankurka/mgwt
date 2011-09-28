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
package com.googlecode.mgwt.examples.showcase.client.activities.animationdone;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.RoundPanel;


/**
 * @author Daniel Kurka
 *
 */
public class AnimationDoneViewGwtImpl implements AnimationDoneView {

	private RoundPanel panel;
	private Button button;

	/**
	 * 
	 */
	public AnimationDoneViewGwtImpl() {
		panel = new RoundPanel();
		panel.setHeight("200px");
		HTML html = new HTML("<p style='text-align: center; position: relative; top: 75px; font-size: 20px'>great, yeah!<p>");

		panel.add(html);

		button = new Button();
		button.getElement().getStyle().setTop(125, Unit.PX);
		button.getElement().getStyle().setPosition(Position.RELATIVE);
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
