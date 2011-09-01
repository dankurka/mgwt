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
package de.kurka.gwt.mobile.ui.client.widget.base;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

import de.kurka.gwt.mobile.ui.client.util.FeatureDetection;
import de.kurka.gwt.mobile.ui.client.widget.TouchPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class MTextBox extends TextBox implements HasValue<String> {

	private TouchPanel main;
	private TextBox textBox;
	private FlowPanel cover;

	public MTextBox() {
//		main = new TouchPanel();
//		initWidget(main);
//
//		cover = new FlowPanel();
//		cover.addStyleName("cover");
//
//		if (FeatureDetection.isiOs()) {
//			main.add(cover);
//		}
//
//		textBox = new TextBox();
//		main.add(textBox);
//
//		if (FeatureDetection.isiOs()) {
//			cover.addDomHandler(new ClickHandler() {
//
//				@Override
//				public void onClick(ClickEvent event) {
//					cover.getElement().getStyle().setDisplay(Display.NONE);
//					textBox.setFocus(true);
//
//				}
//			}, ClickEvent.getType());
//
//			textBox.addBlurHandler(new BlurHandler() {
//
//				@Override
//				public void onBlur(BlurEvent event) {
//					cover.getElement().getStyle().setDisplay(Display.BLOCK);
//
//				}
//			});
//
//			textBox.addFocusHandler(new FocusHandler() {
//
//				@Override
//				public void onFocus(FocusEvent event) {
//					cover.getElement().getStyle().setDisplay(Display.NONE);
//					// textBox.setFocus(true);
//
//				}
//			});
//		}
		setStylePrimaryName("mgwt-TextBox");
	}

	public void setPlaceHolder(String value) {
		getElement().setAttribute("placeholder", value);
	}

	public String getPlaceHolder() {
		return getElement().getAttribute("placeholder");
	}

//	@Override
//	public HandlerRegistration addValueChangeHandler(
//			ValueChangeHandler<String> handler) {
//		return textBox.addValueChangeHandler(handler);
//	}
//
//	@Override
//	public String getValue() {
//		return textBox.getValue();
//	}
//
//	@Override
//	public void setValue(String value) {
//		textBox.setValue(value);
//
//	}
//
//	@Override
//	public void setValue(String value, boolean fireEvents) {
//		textBox.setValue(value, fireEvents);
//
//	}

}
