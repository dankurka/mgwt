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
import de.kurka.gwt.mobile.ui.client.MGWT;
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
		if (!MGWT.getFeatureDetection().isTablet()) {
			headerPanel.setLeftWidget(headerBackButton);
		}

		main.add(headerPanel);

		ScrollPanel scrollPanel = new ScrollPanel();

		scrollPanel.setSize("320px", "320px");

		RoundPanel roundPanel = new RoundPanel();
		roundPanel.setWidth("1000px");
		for (int i = 0; i < 100; i++) {
			roundPanel
					.add(new HTML(
							"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur tincidunt, arcu eget accumsan ullamcorper, ante nisl viverra enim, id consequat ante metus sed nibh. Sed orci nisl, dictum sit amet bibendum id, mollis ac arcu. Mauris venenatis orci sed dui lacinia vulputate. Proin in commodo nisl. Curabitur libero sem, tincidunt et eleifend et, euismod ac arcu. Etiam sit amet nulla mauris, id pulvinar enim. Quisque tincidunt accumsan tempor. Donec et euismod augue. Quisque ultricies mollis metus cursus consectetur. Ut sollicitudin magna in velit vulputate tempus. Sed metus metus, tincidunt nec consectetur vitae, sagittis ac velit Vestibulum consectetur, velit sed consectetur tempor, sapien odio tempor nulla, vel interdum mauris mi ac sem. Proin fermentum dictum mattis. Praesent eleifend posuere orci, vel rhoncus ante consequat eu. Vivamus eu nisl ornare nibh pellentesque fringilla. Sed tincidunt felis gravida mauris gravida sed venenatis mauris tincidunt. Pellentesque varius neque non arcu dictum consequat. Nulla vitae orci felis, ac egestas nisl. Vivamus semper sollicitudin mollis. Donec ac diam ut magna tempor consectetur. Donec at metus ligula, sed hendrerit sapien. Proin quis urna dui, id tincidunt tellus. Morbi enim ligula, mollis ut congue non, commodo nec magna. Quisque sagittis vehicula dui, ac aliquam tortor scelerisque a. Morbi urna ipsum, feugiat vitae fringilla in, blandit adipiscing tellus. Donec in est id tortor viverra viverra. Proin eu arcu sem, eget tincidunt est. Nunc in erat risus. Praesent pharetra pulvinar volutpat. Donec semper diam in enim luctus in viverra nisi tincidunt. Aliquam cursus interdum posuere."));
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
