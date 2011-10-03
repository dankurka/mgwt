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
package com.googlecode.mgwt.examples.showcase.client.activities.slider;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasValue;
import com.googlecode.mgwt.examples.showcase.client.DetailViewGwtImpl;
import com.googlecode.mgwt.ui.client.widget.MSlider;

/**
 * @author Daniel Kurka
 * 
 */
public class SliderViewGwtImpl extends DetailViewGwtImpl implements SliderView {

	private MSlider mSlider;
	private HTML valueField;

	public SliderViewGwtImpl() {

		FlowPanel content = new FlowPanel();

		mSlider = new MSlider();
		content.add(mSlider);
		mSlider.setMax(250);

		mSlider.getElement().getStyle().setMargin(20, Unit.PX);

		valueField = new HTML("0");
		content.add(valueField);

		scrollPanel.setWidget(content);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setScrollingEnabledY(false);

	}

	@Override
	public HasValue<Integer> getSliderValue() {
		return mSlider;
	}

	/* (non-Javadoc)
	 * @see com.googlecode.mgwt.examples.showcase.client.activities.SliderView#getTextField()
	 */
	@Override
	public HasHTML getTextField() {
		return valueField;
	}

}
