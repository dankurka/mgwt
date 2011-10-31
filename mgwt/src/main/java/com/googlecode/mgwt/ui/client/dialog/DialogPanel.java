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

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.googlecode.mgwt.dom.client.event.tap.HasTapEvent;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

/**
 * A dialog panel with title, text, ok and cancel button
 * 
 * @author Daniel Kurka
 * 
 */
public class DialogPanel extends Composite {
	private class CancelButton extends ButtonBase {

		public CancelButton(DialogCss css, String text) {
			super(css);
			setText(text);
			addStyleName(css.cancelbutton());

		}

	}

	private class OkButton extends ButtonBase {

		public OkButton(DialogCss css, String text) {

			super(css);
			setText(text);
			addStyleName(css.okbutton());

		}

	}

	private FlowPanel main;
	private FlowPanel container;
	private HTML title;
	private FlowPanel content;
	private FlowPanel buttonContainer;
	private OkButton okButton;
	private CancelButton cancelButton;
	private final DialogCss css;

	/**
	 * Construct the panel
	 */
	public DialogPanel() {
		this(MGWTStyle.getDefaultClientBundle().getDialogCss());
	}

	/**
	 * Construct panel with a special css
	 * 
	 * @param css the css to use
	 */
	public DialogPanel(DialogCss css) {
		this.css = css;
		this.css.ensureInjected();
		main = new FlowPanel();
		initWidget(main);

		main.addStyleName(css.getDialogPanel());

		container = new FlowPanel();
		container.addStyleName(css.container());

		main.add(container);

		title = new HTML();
		title.addStyleName(css.title());
		container.add(title);

		content = new FlowPanel();
		content.addStyleName(css.content());
		container.add(content);

		buttonContainer = new FlowPanel();
		buttonContainer.addStyleName(css.footer());
		container.add(buttonContainer);

		okButton = new OkButton(css, "Ok");

		buttonContainer.add(okButton);

		cancelButton = new CancelButton(css, "Cancel");

		buttonContainer.add(cancelButton);

	}

	/**
	 * get the container of the panel
	 * 
	 * @return the container of the dialog panel
	 */
	public HasWidgets getContent() {
		return content;
	}

	/**
	 * get {@link HasTapEvent} for the cancel button
	 * 
	 * @return the {@link HasTapEvent} for cancel button
	 */
	public HasTapEvent getCancelButton() {
		return cancelButton;
	}

	/**
	 * get {@link HasTapEvent} for the ok button
	 * 
	 * @return the {@link HasTapEvent} for ok button
	 */
	public HasTapEvent getOkButton() {
		return okButton;
	}

	/**
	 * show the cancel button
	 * 
	 * @param show true to show, otherwise hidden
	 */
	public void showCancelButton(boolean show) {
		if (show) {
			int widgetCount = buttonContainer.getWidgetCount();
			if (widgetCount == 0) {
				buttonContainer.add(cancelButton);
			}
		} else {
			buttonContainer.remove(cancelButton);
		}
	}

	/**
	 * show the ok button
	 * 
	 * @param show true to show, otherwise hidden
	 */
	public void showOkButton(boolean show) {
		if (show) {
			buttonContainer.insert(okButton, 0);
		} else {
			buttonContainer.remove(okButton);
		}
	}

	/**
	 * Get the title of the dialog
	 * 
	 * @return the title of the dialog
	 */
	public HasHTML getDialogTitle() {
		return title;
	}
}
