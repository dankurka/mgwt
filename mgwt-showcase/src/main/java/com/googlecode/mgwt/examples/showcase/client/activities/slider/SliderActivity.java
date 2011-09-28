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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.activities.UIPlace;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;


/**
 * @author Daniel Kurka
 * 
 */
public class SliderActivity extends MGWTAbstractActivity {

	private final ClientFactory clientFactory;

	public SliderActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final SliderView view = clientFactory.getSliderView();

		addHandlerRegistration(view.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				clientFactory.getPlaceController().goTo(new UIPlace());

			}
		}));

		addHandlerRegistration(view.getSliderValue().addValueChangeHandler(new ValueChangeHandler<Integer>() {

			@Override
			public void onValueChange(ValueChangeEvent<Integer> event) {
				view.getTextField().setText("" + event.getValue());
			}
		}));

		panel.setWidget(view);
	}

}
