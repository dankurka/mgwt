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
package com.googlecode.mgwt.ui.client.widget.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.theme.base.ButtonBaseCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

/**
 * @author Daniel Kurka
 * 
 */
public abstract class ButtonBase extends TouchWidget implements HasText, HasSimpleTouchHandler {

	public ButtonBase(ButtonBaseCss css) {
		this(DOM.createDiv(), css);
	}

	private final String active;

	public ButtonBase(Element element, ButtonBaseCss css) {
		setElement(element);
		css.ensureInjected();
		this.active = css.active();

		if (MGWTUtil.getFeatureDetection().isBlackBerry() || MGWTUtil.getFeatureDetection().isDesktop()) {
			addDomHandler(new MouseOverHandler() {

				@Override
				public void onMouseOver(MouseOverEvent event) {
					addStyleName(active);

				}
			}, MouseOverEvent.getType());

			addDomHandler(new MouseOutHandler() {

				@Override
				public void onMouseOut(MouseOutEvent event) {
					removeStyleName(active);

				}
			}, MouseOutEvent.getType());
		} else {
			addTouchHandler(new TouchHandler() {

				@Override
				public void onTouchCanceled(TouchCancelEvent event) {
					removeStyleName(active);

				}

				@Override
				public void onTouchEnd(TouchEndEvent event) {
					removeStyleName(active);

				}

				@Override
				public void onTouchMove(TouchMoveEvent event) {

				}

				@Override
				public void onTouchStart(TouchStartEvent event) {
					addStyleName(active);

				}
			});
		}

		addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch(SimpleTouchEvent event) {
				removeStyleName(active);

			}
		});

	}

	@Override
	public String getText() {
		return getElement().getInnerText();
	}

	@Override
	public void setText(String text) {
		getElement().setInnerText(text);

	}

	@Override
	public HandlerRegistration addSimpleTouchHandler(SimpleTouchHandler handler) {
		return super.addSimpleTouchHandler(handler);
	}

}
