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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.MGWTUtil;
import de.kurka.gwt.mobile.ui.client.button.Button;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.panel.RoundPanel;

/**
 * @author Daniel Kurka
 *
 */
public class AboutViewGwtImpl implements AboutView {

	private FlowPanel main;
	private RoundPanel round;
	private Button backbutton;

	public AboutViewGwtImpl() {
		main = new FlowPanel();
		HeaderPanel panel = new HeaderPanel();

		panel.getTitleWidget().setText("About");

		main.add(panel);

		round = new RoundPanel();

		round.add(new HTML("mgwt"));
		round.add(new HTML("Version 1.0a"));
		round.add(new HTML("Built by Daniel Kurka, @dankurka on Twitter"));

		round.add(new HTML("Using GWT to build mobile apps"));

		round.add(new HTML("<br/><br/><a target='_blank' href='http://www.m-gwt.com'>www.m-gwt.com</a><br/><br/>"));

		main.add(round);

		backbutton = new Button();

		if (!MGWTUtil.getFeatureDetection().isTablet()) {

			main.add(backbutton);
		}

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void setBackButtonText(String text) {
		backbutton.setText(text);

	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return backbutton;
	}

}
