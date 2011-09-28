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
package com.googlecode.mgwt.ui.client.dialog;

import java.util.List;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.dialog.OptionsDialog.OptionCallback;
import com.googlecode.mgwt.ui.client.dialog.OptionsDialog.OptionsDialogOption;
import com.googlecode.mgwt.ui.client.widget.Button;


public class Dialogs {

	public interface AlertCallback {
		public void onButtonPressed();
	}

	public static void alert(String title, String text, final AlertCallback callback) {
		AlertDialog alertDialog = new AlertDialog(MGWTStyle.getDefaultClientBundle().getDialogCss(), title, text);

		alertDialog.addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				if (callback != null) {
					callback.onButtonPressed();
				}

			}
		});

		alertDialog.show();
	}

	public static void confirm(String title, String text, final ConfirmCallback callback) {
		ConfirmDialog confirmDialog = new ConfirmDialog(title, text, callback);

		confirmDialog.show();
	}

	private static class InternalTouchHandler implements SimpleTouchHandler {
		private final int buttonCount;
		private final OptionCallback callback;
		private final OptionsDialog panel;

		public InternalTouchHandler(int buttonCount, OptionsDialog panel, OptionCallback callback) {
			this.buttonCount = buttonCount;
			this.panel = panel;

			this.callback = callback;
		}

		@Override
		public void onTouch(SimpleTouchEvent event) {
			panel.hide();
			if (callback != null) {
				callback.onOptionSelected(buttonCount);
			}

		}

	}

	public static void options(List<OptionsDialogOption> optionText, OptionCallback callback) {

		options(optionText, callback, RootPanel.get());
	}

	public static void options(List<OptionsDialogOption> optionText, OptionCallback callback, HasWidgets widgetToCover) {

		OptionsDialog optionsDialog = new OptionsDialog(MGWTStyle.getDefaultClientBundle().getDialogCss());

		int count = 0;
		for (OptionsDialogOption optionsDialogOption : optionText) {
			count++;
			Button button = new Button(optionsDialogOption.getText());
			switch (optionsDialogOption.getType()) {
			case NORMAL:
				break;
			case IMPORTANT:
				button.setImportant(true);
				break;
			case CONFIRM:
				button.setConfirm(true);
				break;
			}
			button.addSimpleTouchHandler(new InternalTouchHandler(count, optionsDialog, callback));
			optionsDialog.add(button);
		}
		optionsDialog.setPanelToOverlay(widgetToCover);
		optionsDialog.show();
	}
}
