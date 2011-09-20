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
package de.kurka.mobile.showcase.client.activities.popup;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchEvent;
import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.gwt.mobile.mvp.client.MGWTAbstractActivity;
import de.kurka.gwt.mobile.ui.client.dialog.ConfirmDialog.ConfirmCallback;
import de.kurka.gwt.mobile.ui.client.dialog.OptionsDialog.ButtonType;
import de.kurka.gwt.mobile.ui.client.dialog.OptionsDialog.OptionCallback;
import de.kurka.gwt.mobile.ui.client.dialog.OptionsDialog.OptionsDialogOption;
import de.kurka.mobile.showcase.client.ClientFactory;
import de.kurka.mobile.showcase.client.activities.UIPlace;

/**
 * @author Daniel Kurka
 * 
 */
public class PopupActivity extends MGWTAbstractActivity {

	private final ClientFactory clientFactory;

	public PopupActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;

	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final PopupView view = clientFactory.getPopupView();

		addHandlerRegistration(view.getBackButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				clientFactory.getPlaceController().goTo(new UIPlace());

			}
		}));

		addHandlerRegistration(view.getConfirmButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				view.confirmSomeStuff("Confirm this", "Confirm some more stuff", new ConfirmCallback() {

					@Override
					public void onOk() {

					}

					@Override
					public void onCancel() {

					}
				});

			}
		}));

		addHandlerRegistration(view.getSlideUpButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				List<OptionsDialogOption> list = new ArrayList<OptionsDialogOption>();
				list.add(new OptionsDialogOption("Confirm", ButtonType.CONFIRM));
				list.add(new OptionsDialogOption("Cancel", ButtonType.NORMAL));
				list.add(new OptionsDialogOption("Delete", ButtonType.IMPORTANT));

				view.showSomeOptions(list, new OptionCallback() {

					@Override
					public void onOptionSelected(int index) {

					}
				});
			}
		}));

		addHandlerRegistration(view.getAlertButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				view.alertSomeStuff("Hi", "How are you doing?");
			}
		}));

		panel.setWidget(view);
	}
}
