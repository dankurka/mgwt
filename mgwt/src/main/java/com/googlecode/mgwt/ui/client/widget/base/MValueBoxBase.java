/*
 * Copyright 2011 Daniel Kurka
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

import java.text.ParseException;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.AutoDirectionHandler;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;

/**
 * Base class for all input boxes
 * 
 * This is a clone of {@link com.google.gwt.user.client.ui.ValueBoxBase}
 * 
 * @author Daniel Kurka
 * 
 * @param <T> the type of the input
 */
public class MValueBoxBase<T> extends Composite implements HasTouchHandlers, HasPlaceHolder, HasAutoCapitalize, HasAutoCorrect, HasChangeHandlers, HasName, HasDirectionEstimator, HasValue<T>,
		AutoDirectionHandler.Target, IsEditor<ValueBoxEditor<T>>, HasAllKeyHandlers {

	private TouchPanel main;
	protected final ValueBoxBase<T> box;
	private FlowPanel cover;

	public MValueBoxBase(InputCss css, final ValueBoxBase<T> box) {
		this.box = box;

		box.addStyleName(css.box());
		main = new TouchPanel();
		initWidget(main);

		css.ensureInjected();

		cover = new FlowPanel();
		cover.addStyleName(css.cover());

		// if (MGWTUtil.getFeatureDetection().isIOs() && false) {
		// main.add(cover);
		// }

		main.add(box);

		box.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				MGWTUtil.fixIOSScrollIssueBlur();

			}
		});

		box.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				MGWTUtil.fixIOSScrollIssueFocus();

			}
		});

		// if (MGWTUtil.getFeatureDetection().isIOs() && false) {
		// cover.addDomHandler(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// cover.getElement().getStyle().setDisplay(Display.NONE);
		// box.setFocus(true);
		//
		// }
		// }, ClickEvent.getType());
		//
		// box.addBlurHandler(new BlurHandler() {
		//
		// @Override
		// public void onBlur(BlurEvent event) {
		// cover.getElement().getStyle().setDisplay(Display.BLOCK);
		//
		// }
		// });
		//
		// box.addFocusHandler(new FocusHandler() {
		//
		// @Override
		// public void onFocus(FocusEvent event) {
		// cover.getElement().getStyle().setDisplay(Display.NONE);
		//
		// }
		// });
		// }
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasPlaceHolder#setPlaceHolder(java.lang.String)
	 */
	public void setPlaceHolder(String value) {
		box.getElement().setAttribute("placeholder", value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasPlaceHolder#getPlaceHolder()
	 */
	public String getPlaceHolder() {
		return box.getElement().getAttribute("placeholder");
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasChangeHandlers#addChangeHandler(com.google.gwt.event.dom.client.ChangeHandler)
	 */
	public com.google.gwt.event.shared.HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return box.addChangeHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
	 */
	public com.google.gwt.event.shared.HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		return box.addValueChangeHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.editor.client.IsEditor#asEditor()
	 */
	public ValueBoxEditor<T> asEditor() {
		return box.asEditor();
	}

	public void cancelKey() {
		box.cancelKey();
	}

	public int getCursorPos() {
		return box.getCursorPos();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.client.HasDirection#getDirection()
	 */
	public Direction getDirection() {
		return box.getDirection();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.shared.HasDirectionEstimator#getDirectionEstimator()
	 */
	public DirectionEstimator getDirectionEstimator() {
		return box.getDirectionEstimator();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasName#getName()
	 */
	public String getName() {
		return box.getName();
	}

	public String getSelectedText() {
		return box.getSelectedText();
	}

	public int getSelectionLength() {
		return box.getSelectionLength();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#getText()
	 */
	public String getText() {
		return box.getText();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#getValue()
	 */
	public T getValue() {
		return box.getValue();
	}

	public T getValueOrThrow() throws ParseException {
		return box.getValueOrThrow();
	}

	public boolean isReadOnly() {
		return box.isReadOnly();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#onBrowserEvent(com.google.gwt.user.client.Event)
	 */
	@Override
	public void onBrowserEvent(Event event) {
		box.onBrowserEvent(event);
	}

	public void selectAll() {
		box.selectAll();
	}

	public void setAlignment(TextAlignment align) {
		box.setAlignment(align);
	}

	public void setCursorPos(int pos) {
		box.setCursorPos(pos);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.client.HasDirection#setDirection(com.google.gwt.i18n.client.HasDirection.Direction)
	 */
	public void setDirection(Direction direction) {
		box.setDirection(direction);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.shared.HasDirectionEstimator#setDirectionEstimator(boolean)
	 */
	public void setDirectionEstimator(boolean enabled) {
		box.setDirectionEstimator(enabled);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.shared.HasDirectionEstimator#setDirectionEstimator(com.google.gwt.i18n.shared.DirectionEstimator)
	 */
	public void setDirectionEstimator(DirectionEstimator directionEstimator) {
		box.setDirectionEstimator(directionEstimator);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasName#setName(java.lang.String)
	 */
	public void setName(String name) {
		box.setName(name);
	}

	/**
	 * set readonly
	 * 
	 * @param readOnly
	 */
	public void setReadOnly(boolean readOnly) {
		box.setReadOnly(readOnly);
	}

	/**
	 * {@link com.google.gwt.user.client.ui.ValueBoxBase#setSelectionRange(int,int)}
	 */
	public void setSelectionRange(int pos, int length) {
		box.setSelectionRange(pos, length);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
	 */
	public void setText(String text) {
		box.setText(text);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object)
	 */
	public void setValue(T value) {
		box.setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object, boolean)
	 */
	public void setValue(T value, boolean fireEvents) {
		box.setValue(value, fireEvents);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyUpHandlers#addKeyUpHandler(com.google.gwt.event.dom.client.KeyUpHandler)
	 */
	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return box.addKeyUpHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchStartHandler(com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler)
	 */
	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return main.addTouchStartHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchMoveHandler(com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler)
	 */
	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return main.addTouchMoveHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchCancelHandler(com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler)
	 */
	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return main.addTouchCancelHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchEndHandler(com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler)
	 */
	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return main.addTouchEndHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchHandler(com.googlecode.mgwt.dom.client.event.touch.TouchHandler)
	 */
	@Override
	public HandlerRegistration addTouchHandler(TouchHandler handler) {
		return main.addTouchHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasAutoCorrect#setAutoCorrectEnabled(boolean)
	 */
	@Override
	public void setAutoCorrectEnabled(boolean enabled) {
		if (enabled) {
			box.getElement().setPropertyString("autocorrect", "on");
		} else {
			box.getElement().setPropertyString("autocorrect", "off");
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasAutoCorrect#isAutoCorrectEnabled()
	 */
	@Override
	public boolean isAutoCorrectEnabled() {
		String autoCorrent = box.getElement().getPropertyString("autocorrect");
		return "on".equals(autoCorrent);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasAutoCapitalize#setAutoCapitalize(boolean)
	 */
	@Override
	public void setAutoCapitalize(boolean capitalize) {
		if (capitalize) {
			box.getElement().setPropertyString("autocapitalize", "on");
		} else {
			box.getElement().setPropertyString("autocapitalize", "off");
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasAutoCapitalize#isAutoCapitalize()
	 */
	@Override
	public boolean isAutoCapitalize() {
		String auto = box.getElement().getPropertyString("autocapitalize");
		return "on".equals(auto);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyDownHandlers#addKeyDownHandler(com.google.gwt.event.dom.client.KeyDownHandler)
	 */
	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return box.addKeyDownHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyPressHandlers#addKeyPressHandler(com.google.gwt.event.dom.client.KeyPressHandler)
	 */
	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return box.addKeyPressHandler(handler);
	}

}
