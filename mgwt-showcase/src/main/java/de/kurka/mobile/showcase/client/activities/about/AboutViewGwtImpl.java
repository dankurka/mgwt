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
package de.kurka.mobile.showcase.client.activities.about;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.Button;
import de.kurka.gwt.mobile.ui.client.panel.RoundPanel;

/**
 * @author Daniel Kurka
 *
 */
public class AboutViewGwtImpl implements AboutView {

	private RoundPanel main;
	private Button backbutton;

	public AboutViewGwtImpl() {
		main = new RoundPanel();

		main.add(new HTML("mgwt"));
		main.add(new HTML("Version 0.5a"));
		main.add(new HTML("Built by Daniel Kurka, @dankurka on Twitter"));

		main.add(new HTML("Using GWT to build mobile apps"));

		main.add(new HTML("<br/><br/><a href='http://www.m-gwt.com'>www.m-gwt.com</a><br/><br/>"));

		backbutton = new Button();

		main.add(backbutton);

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
