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
package de.kurka.gwt.mobile.ui.client.panel;

import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasWidgets;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.ButtonBase;

/**
 * @author Daniel Kurka
 *
 */
public class DialogPanel extends Composite {

	private FlowPanel main;
	private FlowPanel container;

	private FlowPanel wrapper;
	private FlowPanel cell;

	private FlowPanel content;
	private OkButton okButton;
	private CancelButton cancelButton;
	private FlowPanel buttonContainer;
	private HTML title;

	public DialogPanel() {

		wrapper = new FlowPanel();
		//TODO css?
		wrapper.getElement().setAttribute("style", "display: table; width: 100%; height:100%");
		initWidget(wrapper);

		cell = new FlowPanel();
		//TODO css?
		cell.getElement().setAttribute("style", "display: table-cell;");
		cell.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);

		wrapper.add(cell);

		main = new FlowPanel();
		cell.add(main);

		main.setStylePrimaryName("mgwt-DialogPanel");

		container = new FlowPanel();
		container.addStyleName("mgwt-DialogPanel-container");

		main.add(container);

		title = new HTML();
		title.addStyleName("mgwt-DialogPanel-title");
		container.add(title);

		content = new FlowPanel();
		content.addStyleName("mgwt-DialogPanel-content");
		container.add(content);

		buttonContainer = new FlowPanel();
		buttonContainer.addStyleName("mgwt-DialogPanel-footer");
		container.add(buttonContainer);

		okButton = new OkButton("Ok");

		buttonContainer.add(okButton);

		cancelButton = new CancelButton("Cancel");

		buttonContainer.add(cancelButton);

	}

	private class CancelButton extends ButtonBase {

		public CancelButton(String text) {

			setText(text);
			addStyleName("mgwt-DialogPanel-button");
			addStyleName("mgwt-DialogPanel-cancelbutton");
		}

	}

	private class OkButton extends ButtonBase {

		public OkButton(String text) {

			setText(text);
			addStyleName("mgwt-DialogPanel-button");
			addStyleName("mgwt-DialogPanel-okbutton");
		}

	}

	public HasWidgets getContent() {
		return content;
	}

	public HasSimpleTouchHandler getCancelButton() {
		return cancelButton;
	}

	public HasSimpleTouchHandler getOkButton() {
		return okButton;
	}

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

	public void showOkButton(boolean show) {
		if (show) {
			buttonContainer.insert(okButton, 0);
		} else {
			buttonContainer.remove(okButton);
		}
	}

	public HasHTML getDialogTitle() {
		return title;
	}
}
