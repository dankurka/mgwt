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
package de.kurka.mobile.showcase.client.activities.slider;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.MGWTUtil;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.base.MSlider;

/**
 * @author Daniel Kurka
 *
 */
public class SliderViewGwtImpl implements SliderView {

	private FlowPanel main;
	private HeaderBackButton headerBackButton;
	private MSlider mSlider;
	private HTML valueField;

	public SliderViewGwtImpl() {
		main = new FlowPanel();

		HeaderPanel headerPanel = new HeaderPanel();
		main.add(headerPanel);
		headerPanel.getTitleWidget().setText("Slider");

		headerBackButton = new HeaderBackButton();
		headerBackButton.setText("UI");
		if (MGWTUtil.getFeatureDetection().isPhone()) {
			headerPanel.setLeftWidget(headerBackButton);
		}

		mSlider = new MSlider();
		main.add(mSlider);
		mSlider.setMax(250);

		valueField = new HTML("0");
		main.add(valueField);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return headerBackButton;
	}

	@Override
	public HasValue<Integer> getSliderValue() {
		return mSlider;
	}

	/* (non-Javadoc)
	 * @see de.kurka.mobile.showcase.client.activities.SliderView#getTextField()
	 */
	@Override
	public HasHTML getTextField() {
		return valueField;
	}

}
