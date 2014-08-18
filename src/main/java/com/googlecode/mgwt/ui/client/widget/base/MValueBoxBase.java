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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
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
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.AutoDirectionHandler;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.input.InputAppearance;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;

import java.text.ParseException;

/**
 * Base class for all input boxes
 *
 * This is a clone of {@link com.google.gwt.user.client.ui.ValueBoxBase}
 *
 * @author Daniel Kurka
 */
public class MValueBoxBase<T> extends Composite implements AutoDirectionHandler.Target,
    HasAllKeyHandlers, HasAutoCapitalize, HasAutoCorrect, HasBlurHandlers, HasChangeHandlers,
    HasDirectionEstimator, HasEnabled, HasFocusHandlers, HasName, HasPlaceHolder, HasTouchHandlers,
    HasValue<T>, IsEditor<ValueBoxEditor<T>> {

  public interface HasSource {
    public void setSource(Object source);
  }

  public interface MValueBoxBaseImpl {
    public void setType(Element element, String type);
  }

  public static class MValueBoxBaseDefaultImpl implements MValueBoxBaseImpl {

    @Override
    public void setType(Element element, String type) {
      element.setPropertyString("type", type);
    }
  }

  public static class MValueBoxBaseNoOpImpl implements MValueBoxBaseImpl {

    @Override
    public void setType(Element element, String type) {
      // no op ...
    }
  }

  protected static final MValueBoxBaseImpl impl = GWT.create(MValueBoxBaseImpl.class);

  private TouchPanel main;
  protected final ValueBoxBase<T> box;
  private boolean enabled;
  private boolean invalid;
  private InputAppearance appearance;

  public MValueBoxBase(InputAppearance appearance, final ValueBoxBase<T> box) {
    this.appearance = appearance;
    if (!(box instanceof HasSource)) {
      throw new IllegalStateException("box must implement HasSource..");
    }
    this.box = box;
    box.addStyleName(appearance.css().box());
    main = new TouchPanel();
    initWidget(main);

    setEnabled(true);
    setInvalid(false);

    main.add(box);

    ((HasSource) box).setSource(this);
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

  @Override
  public void setPlaceHolder(String value) {
    box.getElement().setAttribute("placeholder", value);
  }

  @Override
  public String getPlaceHolder() {
    return box.getElement().getAttribute("placeholder");
  }

  @Override
  public com.google.gwt.event.shared.HandlerRegistration addChangeHandler(ChangeHandler handler) {
    return box.addChangeHandler(handler);
  }

  @Override
  public com.google.gwt.event.shared.HandlerRegistration addValueChangeHandler(
      ValueChangeHandler<T> handler) {
    return box.addValueChangeHandler(handler);
  }

  @Override
  public ValueBoxEditor<T> asEditor() {
    return box.asEditor();
  }

  public void cancelKey() {
    box.cancelKey();
  }

  public int getCursorPos() {
    return box.getCursorPos();
  }

  @Override
  public Direction getDirection() {
    return box.getDirection();
  }

  @Override
  public DirectionEstimator getDirectionEstimator() {
    return box.getDirectionEstimator();
  }

  @Override
  public String getName() {
    return box.getName();
  }

  public String getSelectedText() {
    return box.getSelectedText();
  }

  public int getSelectionLength() {
    return box.getSelectionLength();
  }

  @Override
  public String getText() {
    return box.getText();
  }

  @Override
  public T getValue() {
    return box.getValue();
  }

  public T getValueOrThrow() throws ParseException {
    return box.getValueOrThrow();
  }

  public boolean isReadOnly() {
    return box.isReadOnly();
  }

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

  @Override
  public void setDirection(Direction direction) {
    box.setDirection(direction);
  }

  @Override
  public void setDirectionEstimator(boolean enabled) {
    box.setDirectionEstimator(enabled);
  }

  @Override
  public void setDirectionEstimator(DirectionEstimator directionEstimator) {
    box.setDirectionEstimator(directionEstimator);
  }

  @Override
  public void setName(String name) {
    box.setName(name);
  }

  public void setReadOnly(boolean readOnly) {
    box.setReadOnly(readOnly);
  }

  public void setSelectionRange(int pos, int length) {
    box.setSelectionRange(pos, length);
  }

  @Override
  public void setText(String text) {
    box.setText(text);
  }

  @Override
  public void setValue(T value) {
    box.setValue(value);
  }

  @Override
  public void setValue(T value, boolean fireEvents) {
    box.setValue(value, fireEvents);
  }

  @Override
  public com.google.gwt.event.shared.HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
    return box.addKeyUpHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
    return main.addTouchStartHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
    return main.addTouchMoveHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
    return main.addTouchCancelHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
    return main.addTouchEndHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchHandler(TouchHandler handler) {
    return main.addTouchHandler(handler);
  }

  @Override
  public void setAutoCorrectEnabled(boolean enabled) {
    if (enabled) {
      box.getElement().setPropertyString("autocorrect", "on");
    } else {
      box.getElement().setPropertyString("autocorrect", "off");
    }
  }

  @Override
  public boolean isAutoCorrectEnabled() {
    String autoCorrent = box.getElement().getPropertyString("autocorrect");
    return "on".equals(autoCorrent);
  }

  @Override
  public void setAutoCapitalize(boolean capitalize) {
    if (capitalize) {
      box.getElement().setPropertyString("autocapitalize", "on");
    } else {
      box.getElement().setPropertyString("autocapitalize", "off");
    }
  }

  @Override
  public boolean isAutoCapitalize() {
    String auto = box.getElement().getPropertyString("autocapitalize");
    return "on".equals(auto);
  }

  @Override
  public com.google.gwt.event.shared.HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
    return box.addKeyDownHandler(handler);
  }

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

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
    box.setReadOnly(!enabled);
    if (enabled) {
      box.removeStyleName(appearance.css().disabled());
    } else {
      box.addStyleName(appearance.css().disabled());
    }
  }

  public boolean isInvalid() {
    return invalid;
  }

  public void setInvalid(boolean invalid) {
    this.invalid = invalid;
    if (invalid) {
      box.addStyleName(appearance.css().invalid());
    } else {
      box.removeStyleName(appearance.css().invalid());
    }
  }
}
