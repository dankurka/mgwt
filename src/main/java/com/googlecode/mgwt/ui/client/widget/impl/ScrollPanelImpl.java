/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.impl;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.collection.shared.LightArrayInt;
import com.googlecode.mgwt.ui.client.widget.event.scroll.BeforeScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.BeforeScrollMoveEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.BeforeScrollStartEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollAnimationEndEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollAnimationMoveEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollAnimationStartEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollAnimationStartEvent.Handler;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollMoveEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollRefreshEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollStartEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollTouchEndEvent;

/**
 * ScrollPanelImpl abstracts different implementations for scrolling behaviour
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public abstract class ScrollPanelImpl extends Composite implements HasWidgets {

  /**
   * instruct the panel to use position absolute instead of translate3d
   * 
   * on android devices input fields behave strange when used with translate3d
   * 
   * take a look into the mgwt docs
   * 
   * @param pos true to use absolute position default: translate3d
   */
  public abstract void setUsePos(boolean pos);

  /**
   * Scroll to a given position in the specified time
   * 
   * @param destX the new position x
   * @param destY the new position y
   * @param newDuration the duration
   */
  public abstract void scrollTo(int destX, int destY, int newDuration);

  /**
   * Is scrolling enabled in x-axis
   * 
   * @return true if scrolling is enabled
   */
  public abstract boolean isScrollingEnabledX();

  /**
   * enable scrolling in x-axis
   * 
   * @param scrollingEnabledX true to enable scrolling
   */
  public abstract void setScrollingEnabledX(boolean scrollingEnabledX);

  /**
   * Is scrolling enabled in y-axis
   * 
   * @return true if scrolling is enabled
   */
  public abstract boolean isScrollingEnabledY();

  /**
   * enable scrolling in y-axis
   * 
   * @param scrollingEnabledY a boolean.
   */
  public abstract void setScrollingEnabledY(boolean scrollingEnabledY);

  /**
   * set the content of the scrollable area
   * 
   * @param child the content of the scrollable area
   */
  public abstract void setWidget(IsWidget child);

  /**
   * Recalculate dimensions for scrolling
   * 
   * (needs to be called when the content of the childarea changes without setting a new child)
   */
  public abstract void refresh();

  public abstract void setOffSetY(int y);

  public abstract void setMaxScrollY(int y);

  public abstract int getMaxScrollY();

  public abstract void setMinScrollY(int y);

  public abstract int getMinScrollY();

  public abstract void scrollTo(int x, int y, int time, boolean relative);

  public abstract void scrollToPage(int pageX, int pageY, int time);

  public abstract void setBounce(boolean bounce);

  public abstract void setMomentum(boolean momentum);

  public abstract void setSnap(boolean snap);

  public abstract void setSnapThreshold(int threshold);

  public HandlerRegistration addBeforeScrollStartHandler(BeforeScrollStartEvent.Handler handler) {
    return addHandler(handler, BeforeScrollStartEvent.getTYPE());
  }

  public HandlerRegistration addBeforeScrollMoveHandler(BeforeScrollMoveEvent.Handler handler) {
    return addHandler(handler, BeforeScrollMoveEvent.getTYPE());
  }

  public HandlerRegistration addBeforeScrollEndHandler(BeforeScrollEndEvent.Handler handler) {
    return addHandler(handler, BeforeScrollEndEvent.getTYPE());
  }

  public HandlerRegistration addScrollEndHandler(ScrollEndEvent.Handler handler) {
    return addHandler(handler, ScrollEndEvent.getTYPE());
  }

  public HandlerRegistration addScrollStartHandler(ScrollStartEvent.Handler handler) {
    return addHandler(handler, ScrollStartEvent.getTYPE());
  }

  public HandlerRegistration addScrollMoveHandler(ScrollMoveEvent.Handler handler) {
    return addHandler(handler, ScrollMoveEvent.getTYPE());
  }

  public HandlerRegistration addScrollRefreshHandler(ScrollRefreshEvent.Handler handler) {
    return addHandler(handler, ScrollRefreshEvent.getTYPE());
  }

  public HandlerRegistration addScrollTouchEndHandler(ScrollTouchEndEvent.Handler handler) {
    return addHandler(handler, ScrollTouchEndEvent.getTYPE());
  }

  public HandlerRegistration addScrollAnimationStartHandler(Handler handler) {
    return addHandler(handler, ScrollAnimationStartEvent.getTYPE());
  }

  public HandlerRegistration addScrollAnimationMoveHandler(ScrollAnimationMoveEvent.Handler handler) {
    return addHandler(handler, ScrollAnimationMoveEvent.getTYPE());
  }

  public HandlerRegistration addScrollAnimationEndHandler(ScrollAnimationEndEvent.Handler handler) {
    return addHandler(handler, ScrollAnimationEndEvent.getTYPE());
  }

  public abstract int getY();

  public abstract int getX();

  public abstract void setBounceFactor(double factor);

  public abstract void setShowScrollBarX(boolean b);

  public abstract void setShowScrollBarY(boolean b);

  public abstract int getCurrentPageX();

  public abstract int getCurrentPageY();

  public abstract void setAutoHandleResize(boolean handle);

  public abstract void setOffSetMaxY(int height);

  public abstract void setSnapSelector(String selector);

  public abstract LightArrayInt getPagesY();

  public abstract LightArrayInt getPagesX();

  public abstract void setHideScrollBar(boolean hideScrollBar);

}
