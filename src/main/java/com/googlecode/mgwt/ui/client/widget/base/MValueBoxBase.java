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

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.AutoDirectionHandler;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
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
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;

/**
 * Base class for all input boxes
 * 
 * This is a clone of {@link com.google.gwt.user.client.ui.ValueBoxBase}
 * 
 * <h2>Styling</h2>
 * 
 * The DOM looks like (depending on the actual class)
 * 
 * <pre>
 * &lt;div class="mgwt-TextBox">
 * 	&lt;input class="mgwt-InputBox-box"/>
 * &lt;/div>
 * </pre>
 * 
 * 
 * 
 * 
 * 
 * @author Daniel Kurka
 */
public class MValueBoxBase<T> extends Composite implements HasBlurHandlers, HasTouchHandlers, HasPlaceHolder, HasAutoCapitalize, HasAutoCorrect, HasChangeHandlers, HasName, HasDirectionEstimator,
		HasValue<T>, AutoDirectionHandler.Target, IsEditor<ValueBoxEditor<T>>, HasAllKeyHandlers, HasFocusHandlers {

	public interface HasSource {
		public void setSource(Object source);
	}

	public interface MValueBoxBaseImpl {
		public void setType(com.google.gwt.user.client.Element element, String type);
	}

	public static class MValueBoxBaseDefaultImpl implements MValueBoxBaseImpl {

		@Override
		public void setType(Element element, String type) {
			element.setPropertyString("type", type);

		}
	}

	public static class MValueBoxBaseIE9Impl implements MValueBoxBaseImpl {

		@Override
		public void setType(Element element, String type) {
			//no op ...

		}
	}

	protected static final MValueBoxBaseImpl impl = GWT.create(MValueBoxBaseImpl.class);

	private TouchPanel main;
	protected final ValueBoxBase<T> box;

	/**
	 * <p>
	 * Constructor for MValueBoxBase.
	 * </p>
	 * 
	 * @param css a {@link com.googlecode.mgwt.ui.client.theme.base.InputCss}
	 *            object.
	 * @param box a {@link com.google.gwt.user.client.ui.ValueBoxBase} object.
	 */
	public MValueBoxBase(InputCss css, final ValueBoxBase<T> box) {
		if (!(box instanceof HasSource)) {
			throw new IllegalStateException("box must implement HasSource..");
		}
		this.box = box;

		box.addStyleName(css.box());
		main = new TouchPanel();
		initWidget(main);

		css.ensureInjected();

		main.add(box);

	}

	protected void setup(Object source) {
		((HasSource) box).setSource(source);
		box.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				MGWT.fixIOSScrollIssueBlur();

			}
		});

		box.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				MGWT.fixIOSScrollIssueFocus();

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasPlaceHolder#setPlaceHolder(java.lang.String)
	 */
	/** {@inheritDoc} */
	public void setPlaceHolder(String value) {
		box.getElement().setAttribute("placeholder", value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasPlaceHolder#getPlaceHolder()
	 */
	/**
	 * <p>
	 * getPlaceHolder
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getPlaceHolder() {
		return box.getElement().getAttribute("placeholder");
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasChangeHandlers#addChangeHandler(com.google.gwt.event.dom.client.ChangeHandler)
	 */
	/** {@inheritDoc} */
	public com.google.gwt.event.shared.HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return box.addChangeHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.logical.shared.HasValueChangeHandlers#addValueChangeHandler(com.google.gwt.event.logical.shared.ValueChangeHandler)
	 */
	/** {@inheritDoc} */
	public com.google.gwt.event.shared.HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
		return box.addValueChangeHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.editor.client.IsEditor#asEditor()
	 */
	/**
	 * <p>
	 * asEditor
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.editor.ui.client.adapters.ValueBoxEditor}
	 *         object.
	 */
	public ValueBoxEditor<T> asEditor() {
		return box.asEditor();
	}

	/**
	 * <p>
	 * cancelKey
	 * </p>
	 */
	public void cancelKey() {
		box.cancelKey();
	}

	/**
	 * <p>
	 * getCursorPos
	 * </p>
	 * 
	 * @return a int.
	 */
	public int getCursorPos() {
		return box.getCursorPos();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.client.HasDirection#getDirection()
	 */
	/**
	 * <p>
	 * getDirection
	 * </p>
	 * 
	 * @return a Direction object.
	 */
	public Direction getDirection() {
		return box.getDirection();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.shared.HasDirectionEstimator#getDirectionEstimator()
	 */
	/**
	 * <p>
	 * getDirectionEstimator
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.i18n.shared.DirectionEstimator} object.
	 */
	public DirectionEstimator getDirectionEstimator() {
		return box.getDirectionEstimator();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasName#getName()
	 */
	/**
	 * <p>
	 * getName
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getName() {
		return box.getName();
	}

	/**
	 * <p>
	 * getSelectedText
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getSelectedText() {
		return box.getSelectedText();
	}

	/**
	 * <p>
	 * getSelectionLength
	 * </p>
	 * 
	 * @return a int.
	 */
	public int getSelectionLength() {
		return box.getSelectionLength();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#getText()
	 */
	/**
	 * <p>
	 * getText
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getText() {
		return box.getText();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#getValue()
	 */
	/**
	 * <p>
	 * getValue
	 * </p>
	 * 
	 * @return a T object.
	 */
	public T getValue() {
		return box.getValue();
	}

	/**
	 * <p>
	 * getValueOrThrow
	 * </p>
	 * 
	 * @return a T object.
	 * @throws java.text.ParseException if any.
	 */
	public T getValueOrThrow() throws ParseException {
		return box.getValueOrThrow();
	}

	/**
	 * <p>
	 * isReadOnly
	 * </p>
	 * 
	 * @return a boolean.
	 */
	public boolean isReadOnly() {
		return box.isReadOnly();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#onBrowserEvent(com.google.gwt.user.client.Event)
	 */
	/** {@inheritDoc} */
	@Override
	public void onBrowserEvent(Event event) {
		box.onBrowserEvent(event);
	}

	/**
	 * <p>
	 * selectAll
	 * </p>
	 */
	public void selectAll() {
		box.selectAll();
	}

	/**
	 * <p>
	 * setAlignment
	 * </p>
	 * 
	 * @param align a
	 *            {@link com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment}
	 *            object.
	 */
	public void setAlignment(TextAlignment align) {
		box.setAlignment(align);
	}

	/**
	 * <p>
	 * setCursorPos
	 * </p>
	 * 
	 * @param pos a int.
	 */
	public void setCursorPos(int pos) {
		box.setCursorPos(pos);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.client.HasDirection#setDirection(com.google.gwt.i18n.client.HasDirection.Direction)
	 */
	/**
	 * <p>
	 * setDirection
	 * </p>
	 * 
	 * @param direction a Direction object.
	 */
	public void setDirection(Direction direction) {
		box.setDirection(direction);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.shared.HasDirectionEstimator#setDirectionEstimator(boolean)
	 */
	/** {@inheritDoc} */
	public void setDirectionEstimator(boolean enabled) {
		box.setDirectionEstimator(enabled);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.i18n.shared.HasDirectionEstimator#setDirectionEstimator(com.google.gwt.i18n.shared.DirectionEstimator)
	 */
	/**
	 * <p>
	 * setDirectionEstimator
	 * </p>
	 * 
	 * @param directionEstimator a
	 *            {@link com.google.gwt.i18n.shared.DirectionEstimator} object.
	 */
	public void setDirectionEstimator(DirectionEstimator directionEstimator) {
		box.setDirectionEstimator(directionEstimator);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasName#setName(java.lang.String)
	 */
	/** {@inheritDoc} */
	public void setName(String name) {
		box.setName(name);
	}

	/**
	 * set readonly
	 * 
	 * @param readOnly a boolean.
	 */
	public void setReadOnly(boolean readOnly) {
		box.setReadOnly(readOnly);
	}

	/**
	 * {@link com.google.gwt.user.client.ui.ValueBoxBase#setSelectionRange(int,int)}
	 * 
	 * @param pos a int.
	 * @param length a int.
	 */
	public void setSelectionRange(int pos, int length) {
		box.setSelectionRange(pos, length);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
	 */
	/** {@inheritDoc} */
	public void setText(String text) {
		box.setText(text);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object)
	 */
	/**
	 * <p>
	 * setValue
	 * </p>
	 * 
	 * @param value a T object.
	 */
	public void setValue(T value) {
		box.setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasValue#setValue(java.lang.Object, boolean)
	 */
	/** {@inheritDoc} */
	public void setValue(T value, boolean fireEvents) {
		box.setValue(value, fireEvents);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyUpHandlers#addKeyUpHandler(com.google.gwt.event.dom.client.KeyUpHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return box.addKeyUpHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchStartHandler(com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return main.addTouchStartHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchMoveHandler(com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return main.addTouchMoveHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchCancelHandler(com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return main.addTouchCancelHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchEndHandler(com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return main.addTouchEndHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchHandler(com.googlecode.mgwt.dom.client.event.touch.TouchHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchHandler(TouchHandler handler) {
		return main.addTouchHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasAutoCorrect#setAutoCorrectEnabled(boolean)
	 */
	/** {@inheritDoc} */
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
	/** {@inheritDoc} */
	@Override
	public boolean isAutoCorrectEnabled() {
		String autoCorrent = box.getElement().getPropertyString("autocorrect");
		return "on".equals(autoCorrent);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.HasAutoCapitalize#setAutoCapitalize(boolean)
	 */
	/** {@inheritDoc} */
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
	/** {@inheritDoc} */
	@Override
	public boolean isAutoCapitalize() {
		String auto = box.getElement().getPropertyString("autocapitalize");
		return "on".equals(auto);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyDownHandlers#addKeyDownHandler(com.google.gwt.event.dom.client.KeyDownHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return box.addKeyDownHandler(handler);
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.HasKeyPressHandlers#addKeyPressHandler(com.google.gwt.event.dom.client.KeyPressHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return box.addKeyPressHandler(handler);
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return box.addFocusHandler(handler);
	}

	public void setFocus(boolean b) {
		box.setFocus(b);
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return box.addBlurHandler(handler);
	}

}
