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
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.widget.Button;

/**
 * Utility class to use the most common dialog classes
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class Dialogs {

	/**
	 * Callback interface for the Alert Dialog
	 * 
	 * @author Daniel Kurka
	 * 
	 */
	public interface AlertCallback {
		/**
		 * called when the ok button is pressed
		 */
		public void onButtonPressed();
	}

	/**
	 * Show an alert to the user
	 * 
	 * @param title - the title of the alert
	 * @param text - the text of the alert
	 * @param callback - the callback that is called when the user clicks the ok
	 *            button (can be null)
	 */
	public static void alert(String title, String text, final AlertCallback callback) {
		AlertDialog alertDialog = new AlertDialog(MGWTStyle.getTheme().getMGWTClientBundle().getDialogCss(), title, text);

		alertDialog.addTapHandler(new TapHandler() {

			@Override
			public void onTap(TapEvent event) {
				if (callback != null) {
					callback.onButtonPressed();
				}

			}
		});

		alertDialog.show();
	}

	/**
	 * Show a confirm dialog to the user
	 * 
	 * @param title - The title of the Dialog
	 * @param text - the text to confirm
	 * @param callback - the callback that is called when a button is taped on
	 *            the dialog
	 */
	public static void confirm(String title, String text, final ConfirmCallback callback) {
		ConfirmDialog confirmDialog = new ConfirmDialog(title, text, callback);

		confirmDialog.show();
	}

	private static class InternalTouchHandler implements TapHandler {
		private final int buttonCount;
		private final OptionCallback callback;
		private final OptionsDialog panel;

		public InternalTouchHandler(int buttonCount, OptionsDialog panel, OptionCallback callback) {
			this.buttonCount = buttonCount;
			this.panel = panel;

			this.callback = callback;
		}

		@Override
		public void onTap(TapEvent event) {
			panel.hide();
			if (callback != null) {
				callback.onOptionSelected(buttonCount);
			}

		}

	}

	/**
	 * Show an options dialog to the user
	 * 
	 * @param options - text and type of the buttons to show
	 * @param callback - the callback of the button that was selected
	 */
	public static void options(List<OptionsDialogEntry> options, OptionCallback callback) {

		options(options, callback, RootPanel.get());
	}

	/**
	 * Show an options dialog to the user
	 * 
	 * @param options - text and type of the buttons to show
	 * @param callback - the callback of the button that was selected
	 * @param widgetToCover - the widget that should be covered by the dialog
	 */
	public static void options(List<OptionsDialogEntry> options, OptionCallback callback, HasWidgets widgetToCover) {

		OptionsDialog optionsDialog = new OptionsDialog(MGWTStyle.getTheme().getMGWTClientBundle().getDialogCss());

		int count = 0;
		for (OptionsDialogEntry optionsDialogEntry : options) {
			count++;
			Button button = new Button(optionsDialogEntry.getText());
			switch (optionsDialogEntry.getType()) {
			case NORMAL:
				break;
			case IMPORTANT:
				button.setImportant(true);
				break;
			case CONFIRM:
				button.setConfirm(true);
				break;
			default:
				throw new RuntimeException("how did we get here?");
			}
			button.addTapHandler(new InternalTouchHandler(count, optionsDialog, callback));
			optionsDialog.add(button);
		}
		optionsDialog.setPanelToOverlay(widgetToCover);
		optionsDialog.show();
	}

	/**
	 * The option Callback interface
	 * 
	 * @author Daniel Kurka
	 * 
	 */
	public interface OptionCallback {
		public void onOptionSelected(int index);
	}

	/**
	 * The type of buttons to add
	 * 
	 * @author Daniel Kurka
	 * 
	 */
	public enum ButtonType {
		NORMAL, IMPORTANT, CONFIRM
	};

	/**
	 * Options for Options Dialog
	 * 
	 * @author Daniel Kurka
	 * 
	 */
	public static class OptionsDialogEntry {
		private final String text;
		private final ButtonType type;

		public OptionsDialogEntry(String text, ButtonType type) {
			this.text = text;
			this.type = type;

		}

		public String getText() {
			return text;
		}

		public ButtonType getType() {
			return type;
		}
	}
}
