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
package com.googlecode.mgwt.examples.showcase.client.activities.buttonbar;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.widget.BarButton;
import com.googlecode.mgwt.ui.client.widget.ButtonBar;
import com.googlecode.mgwt.ui.client.widget.HeaderBackButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.BarButton.TYPE;


/**
 * @author Daniel Kurka
 * 
 */
public class ButtonBarViewGwtImpl implements ButtonBarView {

	private LayoutPanel main;
	private ButtonBar footerPanel;
	private HeaderPanel headerPanel;
	private HeaderBackButton backButton;

	/**
	 * 
	 */
	public ButtonBarViewGwtImpl() {
		main = new LayoutPanel();

		headerPanel = new HeaderPanel();

		headerPanel.setCenter("ButtonBar");
		backButton = new HeaderBackButton();

		backButton.setText("UI");
		if (MGWTUtil.getFeatureDetection().isPhone()) {
			headerPanel.setLeftWidget(backButton);
		}
		main.add(headerPanel);

		main.add(new ScrollPanel());

		footerPanel = new ButtonBar();

		footerPanel.add(new BarButton(TYPE.refresh));
		footerPanel.add(new BarButton(TYPE.action));
		footerPanel.add(new BarButton(TYPE.arrow_down));
		footerPanel.add(new BarButton(TYPE.arrow_up));
		footerPanel.add(new BarButton(TYPE.arrow_left));
		footerPanel.add(new BarButton(TYPE.arrow_right));
		footerPanel.add(new BarButton(TYPE.bookmark));
		footerPanel.add(new BarButton(TYPE.camera));
		footerPanel.add(new BarButton(TYPE.compose));
		footerPanel.add(new BarButton(TYPE.contact_add));
		footerPanel.add(new BarButton(TYPE.fast_forward));
		footerPanel.add(new BarButton(TYPE.delete));
		footerPanel.add(new BarButton(TYPE.info));
		footerPanel.add(new BarButton(TYPE.locate));
		footerPanel.add(new BarButton(TYPE.new_icon));
		footerPanel.add(new BarButton(TYPE.next_slide));
		footerPanel.add(new BarButton(TYPE.organize));
		footerPanel.add(new BarButton(TYPE.pause));
		footerPanel.add(new BarButton(TYPE.play));
		footerPanel.add(new BarButton(TYPE.plus));
		footerPanel.add(new BarButton(TYPE.previous_slide));
		footerPanel.add(new BarButton(TYPE.reply));
		footerPanel.add(new BarButton(TYPE.rewind));
		footerPanel.add(new BarButton(TYPE.search));
		footerPanel.add(new BarButton(TYPE.stop));
		footerPanel.add(new BarButton(TYPE.trash));

		main.add(footerPanel);
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
