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
package com.googlecode.mgwt.examples.showcase.client.activities;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.HeaderRoundButton;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.RoundPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class AboutViewGwtImpl implements AboutView {

	private LayoutPanel main;
	private RoundPanel round;
	private Button backbutton;
	private HeaderRoundButton tabletButton;

	public AboutViewGwtImpl() {
		main = new LayoutPanel();
		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.setCenter("About");
		main.add(headerPanel);

		ScrollPanel scrollPanel = new ScrollPanel();

		round = new RoundPanel();

		round.add(new HTML("mgwt"));
		round.add(new HTML("Version 1.0a"));
		round.add(new HTML("Built by Daniel Kurka, @dankurka on Twitter"));

		round.add(new HTML("Using GWT to build mobile apps"));

		round.add(new HTML("<br/><br/><a target='_blank' href='http://www.m-gwt.com'>www.m-gwt.com</a><br/><br/>"));

		scrollPanel.setWidget(round);
		scrollPanel.setScrollingEnabledX(false);
		main.add(scrollPanel);

		backbutton = new Button();

		tabletButton = new HeaderRoundButton();
		tabletButton.setText("nav");
		if (!MGWTUtil.getFeatureDetection().isTablet()) {
			main.add(backbutton);
		} else {
			headerPanel.setLeftWidget(tabletButton);
			tabletButton.addStyleName(MGWTStyle.getDefaultClientBundle().getUtilCss().portraitonly());
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

	@Override
	public HasSimpleTouchHandler getNavButton() {
		return tabletButton;
	}

}
