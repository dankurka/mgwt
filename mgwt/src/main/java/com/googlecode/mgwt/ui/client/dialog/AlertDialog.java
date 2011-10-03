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

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.ui.client.panel.DialogPanel1;
import com.googlecode.mgwt.ui.client.panel.PopinDialog;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;

public class AlertDialog implements HasText, HasTitle, HasSimpleTouchHandler {

	private Label textLabel;
	private PopinDialog popinDialog;
	private DialogPanel1 dialogPanel1;

	public AlertDialog(DialogCss css, String title, String text) {
		css.ensureInjected();
		popinDialog = new PopinDialog(css);
		dialogPanel1 = new DialogPanel1();
		dialogPanel1.showCancelButton(false);
		dialogPanel1.showOkButton(true);

		textLabel = new Label();
		dialogPanel1.getContent().add(textLabel);
		popinDialog.add(dialogPanel1);

		dialogPanel1.getOkButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				popinDialog.hide();
			}

		});

		setText(text);
		setTitleText(title);

	}

	@Override
	public String getText() {
		return textLabel.getText();
	}

	@Override
	public void setText(String text) {
		textLabel.setText(text);

	}

	@Override
	public void setTitleText(String text) {
		dialogPanel1.getDialogTitle().setText(text);

	}

	@Override
	public String getTitleText() {
		return dialogPanel1.getDialogTitle().getText();
	}

	public void show() {
		popinDialog.center();
	}

	@Override
	public HandlerRegistration addSimpleTouchHandler(SimpleTouchHandler handler) {
		return dialogPanel1.getOkButton().addSimpleTouchHandler(handler);
	}

}
