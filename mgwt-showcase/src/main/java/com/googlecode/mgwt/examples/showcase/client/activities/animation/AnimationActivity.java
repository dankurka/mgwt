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
package com.googlecode.mgwt.examples.showcase.client.activities.animation;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.examples.showcase.client.ClientFactory;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationDissolvePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationFadePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationFlipPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationPopPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSlidePlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSlideUpPlace;
import com.googlecode.mgwt.examples.showcase.client.activities.animationdone.AnimationSwapPlace;
import com.googlecode.mgwt.examples.showcase.client.places.HomePlace;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;


/**
 * @author Daniel Kurka
 * 
 */
public class AnimationActivity extends MGWTAbstractActivity {

	private final ClientFactory clientFactory;

	/**
	 * 
	 */
	public AnimationActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		AnimationView view = clientFactory.getAnimationView();

		view.setLeftButtonText("Home");
		view.setTitle("Animation");

		view.setAnimations(createAnimations());

		addHandlerRegistration(view.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace());

			}
		}));

		addHandlerRegistration(view.getCellSelectedHandler().addCellSelectedHandler(new CellSelectedHandler() {

			@Override
			public void onCellSelected(CellSelectedEvent event) {
				int index = event.getIndex();

				switch (index) {
				case 0:
					clientFactory.getPlaceController().goTo(new AnimationSlidePlace());

					break;
				case 1:
					clientFactory.getPlaceController().goTo(new AnimationSlideUpPlace());

					break;
				case 2:
					clientFactory.getPlaceController().goTo(new AnimationDissolvePlace());

					break;
				case 3:
					clientFactory.getPlaceController().goTo(new AnimationFadePlace());

					break;
				case 4:
					clientFactory.getPlaceController().goTo(new AnimationFlipPlace());

					break;
				case 5:
					clientFactory.getPlaceController().goTo(new AnimationPopPlace());

					break;
				case 6:
					clientFactory.getPlaceController().goTo(new AnimationSwapPlace());

					break;

				default:
					break;
				}

			}
		}));

		panel.setWidget(view);

	}

	/**
	 * @return
	 */
	private List<Animation> createAnimations() {
		ArrayList<Animation> list = new ArrayList<Animation>();

		list.add(new Animation("Slide"));
		list.add(new Animation("Slide up"));
		list.add(new Animation("Dissolve"));
		list.add(new Animation("Fade"));
		list.add(new Animation("Flip"));
		list.add(new Animation("Pop"));
		list.add(new Animation("Swap"));

		return list;
	}

}
