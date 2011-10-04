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

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.panel.DialogPanel;
import com.googlecode.mgwt.ui.client.panel.PopinDialog;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;


public class ConfirmDialog implements HasText, HasTitle {
	private PopinDialog popinDialog;
	private DialogPanel dialogPanel1;
	private Label textLabel;
	private ConfirmCallback callback;

	public interface ConfirmCallback {
		public void onOk();

		public void onCancel();
	}

	public ConfirmDialog(String title, String text, ConfirmCallback callback) {
		this(MGWTStyle.getDefaultClientBundle().getDialogCss(), title, text, callback);
	}

	public ConfirmDialog(DialogCss css, String title, String text, ConfirmCallback callback) {
		this.callback = callback;
		popinDialog = new PopinDialog(css);
		dialogPanel1 = new DialogPanel();
		dialogPanel1.showCancelButton(true);
		dialogPanel1.showOkButton(true);

		textLabel = new Label();
		dialogPanel1.getContent().add(textLabel);
		popinDialog.add(dialogPanel1);

		dialogPanel1.getOkButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				popinDialog.hide();
				if (ConfirmDialog.this.callback != null)
					ConfirmDialog.this.callback.onOk();
			}
		});

		dialogPanel1.getCancelButton().addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				popinDialog.hide();
				if (ConfirmDialog.this.callback != null)
					ConfirmDialog.this.callback.onCancel();
			}
		});

		setText(text);
		setTitleText(title);

	}

	@Override
	public void setTitleText(String title) {
		dialogPanel1.getDialogTitle().setHTML(title);

	}

	@Override
	public void setText(String text) {
		textLabel.setText(text);

	}

	@Override
	public String getTitleText() {
		return dialogPanel1.getDialogTitle().getHTML();
	}

	@Override
	public String getText() {
		return textLabel.getText();
	}

	public void show() {
		popinDialog.center();
	}

}
