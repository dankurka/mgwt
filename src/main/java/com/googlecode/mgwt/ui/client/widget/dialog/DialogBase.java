/*
 * Copyright 2010 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.dialog;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.ui.client.util.MGWTUtil;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationEndCallback;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;
import com.googlecode.mgwt.ui.client.widget.touch.TouchDelegate;

import java.util.Iterator;

/**
 * Baseclass for creating dialogs that are animated
 *
 * @author Daniel Kurka
 */
public abstract class DialogBase implements HasWidgets, HasTouchHandlers, HasTapHandlers,
    Dialog {

  private final class InternalTouchHandler implements TouchHandler {
    private final Element shadow;
    private Element startTarget;

    private InternalTouchHandler(Element shadow) {
      this.shadow = shadow;
    }

    @Override
    public void onTouchCancel(TouchCancelEvent event) {
      startTarget = null;
    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
      EventTarget eventTarget = event.getNativeEvent().getEventTarget();
      if (eventTarget != null) {
        // no textnode or element node
        if (Node.is(eventTarget)) {
          if (Element.is(eventTarget)) {
            Element endTarget = eventTarget.cast();

            if (endTarget == shadow && startTarget == shadow) {
              maybeHide();
            }

          }
        }
      }
      startTarget = null;
    }

    @Override
    public void onTouchMove(TouchMoveEvent event) {
    }

    @Override
    public void onTouchStart(TouchStartEvent event) {
      EventTarget eventTarget = event.getNativeEvent().getEventTarget();
      if (eventTarget != null) {
        // no textnode or element node
        if (Node.is(eventTarget)) {
          if (Element.is(eventTarget)) {
            startTarget = eventTarget.cast();
          }
        }
      }
    }
  }

  protected FlowPanel container;
  protected HasWidgets panelToOverlay;

  private AnimationWidget display;

  private boolean centerContent;
  private boolean hideOnBackgroundClick;
  private boolean isVisible;
  private TouchDelegate touchDelegate;
  protected DialogAppearance appearance;

  /**
   * Create an instance of an animated dialog
   *
   * @param css a {@link com.googlecode.mgwt.ui.client.theme.base.DialogCss} object.
   */
  public DialogBase(DialogAppearance appearance) {
    this.appearance = appearance;
    display = new AnimationWidget();

    display.addStyleName(appearance.dialogCss().animationContainerShadow());
    display.addStyleName(appearance.dialogCss().z_index());

    touchDelegate = new TouchDelegate(display);

    container = new FlowPanel();

    container.addStyleName(appearance.dialogCss().animationContainer());

    Element shadow = container.getElement();

    addTouchHandler(new InternalTouchHandler(shadow));

  }

  @Override
  public void add(Widget w) {
    container.add(w);
  }

  @Override
  public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
    return touchDelegate.addTouchStartHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
    return touchDelegate.addTouchMoveHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
    return touchDelegate.addTouchCancelHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
    return touchDelegate.addTouchEndHandler(handler);
  }

  @Override
  public HandlerRegistration addTouchHandler(TouchHandler handler) {
    HandlerRegistrationCollection handlerRegistrationCollection =
        new HandlerRegistrationCollection();

    handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(handler));
    handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(handler));
    handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(handler));
    handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(handler));
    return handlerRegistrationCollection;
  }

  public HandlerRegistration addTapHandler(TapHandler handler) {
    return touchDelegate.addTapHandler(handler);
  }

  /**
   * Show the dialog centered
   */
  public void center() {
    centerContent = true;
    show();
  }

  @Override
  public void clear() {
    container.clear();
  }

  /**
   * get the panel that the dialog overlays
   *
   * @return the panel that is overlayed by this dialog
   */
  public HasWidgets getPanelToOverlay() {
    if (panelToOverlay == null) {
      panelToOverlay = RootPanel.get();
    }
    return panelToOverlay;
  }

  /**
   * hide the dialog if it is visible
   */
  public void hide() {
    if (!isVisible)
      return;
    isVisible = false;
    Animation animation = getHideAnimation();

    display.animate(animation, false, new AnimationEndCallback() {

      @Override
      public void onAnimationEnd() {
        HasWidgets panel = getPanelToOverlay();
        panel.remove(display.asWidget());
        // see issue 247 => http://code.google.com/p/mgwt/issues/detail?id=247
        MGWTUtil.forceFullRepaint();

      }
    });

  }

  /**
   * Should the dialog hide itself if there is a tap outside the dialog
   *
   * @return true if the dialog automatically hides, otherwise false
   */
  public boolean isHideOnBackgroundClick() {
    return hideOnBackgroundClick;
  }

  @Override
  public Iterator<Widget> iterator() {
    return container.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return container.remove(w);
  }

  /**
   * Should the content of the dialog be centered
   *
   * @param centerContent true to center content
   */
  public void setCenterContent(boolean centerContent) {
    this.centerContent = centerContent;
  }

  /**
   * Should the dialog hide itself if there is a tap outside the dialog
   *
   * @param hideOnBackgroundClick true if the dialog automatically hides, otherwise false
   */
  public void setHideOnBackgroundClick(boolean hideOnBackgroundClick) {
    this.hideOnBackgroundClick = hideOnBackgroundClick;
  }

  /**
   * set the panel that should be overlayed by the dialog
   *
   * @param panel the area to be overlayed
   */
  public void setPanelToOverlay(HasWidgets panel) {
    this.panelToOverlay = panel;
  }

  /**
   * should the dialog add a shadow over the area that it covers
   *
   * @param shadow true to add a shadow
   */
  public void setShadow(boolean shadow) {
    if (shadow) {
      display.asWidget().addStyleName(appearance.dialogCss().animationContainerShadow());
    } else {
      display.asWidget().removeStyleName(appearance.dialogCss().animationContainerShadow());

    }
  }

  public void show() {
    if (isVisible) {
      return;
    }
    isVisible = true;

    // add overlay to DOM
    HasWidgets panel = getPanelToOverlay();
    panel.add(display.asWidget());

    if (centerContent) {
      container.addStyleName(appearance.dialogCss().animationContainerCenter());

    }

    display.setFirstWidget(container);

    // and animiate
    Animation animation = getShowAnimation();

    display.animate(animation, true, new AnimationEndCallback() {

      @Override
      public void onAnimationEnd() {

      }
    });
  }

  protected abstract Animation getShowAnimation();

  protected abstract Animation getHideAnimation();

  protected void maybeHide() {
    if (hideOnBackgroundClick) {
      hide();
    }
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    display.fireEvent(event);
  }
}
