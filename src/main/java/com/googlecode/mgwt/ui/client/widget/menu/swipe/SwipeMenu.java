/*
 * Copyright 2013 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.menu.swipe;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.logical.shared.HasOpenHandlers;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEndHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeEvent.DIRECTION;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeMoveEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeMoveHandler;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeStartEvent;
import com.googlecode.mgwt.dom.client.recognizer.swipe.SwipeStartHandler;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.touch.TouchDelegate;

/**
 * Considered experimental and might change, use at your own risk
 */
public class SwipeMenu extends Composite implements HasOpenHandlers<SwipeMenu>,
    HasCloseHandlers<SwipeMenu> {

  private static final SwipeMenuAppearance APPEARANCE = GWT.create(SwipeMenuAppearance.class);

  protected static final int THRESHOLD = 40;

  @UiField
  protected FlowPanel main;
  @UiField
  protected SimplePanel menu;
  @UiField
  protected SimplePanel content;
  @UiField
  protected FlowPanel wrap;

  @UiField(provided = true)
  protected final SwipeMenuAppearance appearance;

  private enum STATE {
    OPEN, CLOSED, ANIMATING_TO_CLOSE, SWIPING_TO_OPEN, SWIPING_TO_CLOSE, ANIMATING_TO_OPEN
  }

  private STATE state;

  private DIRECTION currentDirection;
  private int maxDistance;

  private int startX;

  private TouchDelegate touchContainer;

  public SwipeMenu() {
    this(APPEARANCE, /*allowSwipe*/ true);
  }
  
  public SwipeMenu(boolean allowSwipe) {
    this(APPEARANCE, allowSwipe);
  }

  public SwipeMenu(SwipeMenuAppearance appearance) {
    this(appearance, /*allowSwipe*/ true);
  }
  
  public SwipeMenu(SwipeMenuAppearance appearance, boolean allowSwipe) {

    this.appearance = appearance;
    state = STATE.CLOSED;

    initWidget(appearance.uiBinder().createAndBindUi(this));

    touchContainer = new TouchDelegate(main);

    initHandlers(allowSwipe);

  }

  @Override
  public HandlerRegistration addOpenHandler(OpenHandler<SwipeMenu> handler) {
    return addHandler(handler, OpenEvent.getType());
  }

  @Override
  public HandlerRegistration addCloseHandler(CloseHandler<SwipeMenu> handler) {
    return addHandler(handler, CloseEvent.getType());
  }

  public void setMenuDisplay(Widget w) {
    menu.setWidget(w);
  }

  public void setContentDisplay(Widget w) {
    content.setWidget(w);
  }

  public void open() {
    open(true);
  }

  public boolean isOpen() {
    return state == STATE.OPEN;
  }

  public void open(boolean animate) {
    // TODO deal with animating
    if (animate) {
      openMenuWithAnimation(200);
    } else {
      state = STATE.OPEN;
      openMenu();
    }
  }

  public void close() {
    close(true);
  }

  public void close(boolean animate) {
    // TODO deal with animating
    if (animate) {
      closeMenuWithAnimation(200);
    } else {
      state = STATE.CLOSED;
      closeMenu();
    }
  }

  private void initHandlers(boolean allowSwipe) {
    if (allowSwipe) {
      touchContainer.addSwipeStartHandler(new SwipeStartHandler() {

        @Override
        public void onSwipeStart(SwipeStartEvent event) {

          handleSwipeStart(event);

        }
      });

      touchContainer.addSwipeMoveHandler(new SwipeMoveHandler() {

        @Override
        public void onSwipeMove(SwipeMoveEvent event) {

          handleSwipeMove(event);

        }
      });

      touchContainer.addSwipeEndHandler(new SwipeEndHandler() {

        @Override
        public void onSwipeEnd(SwipeEndEvent event) {
          handleSwipeEnd(event);

        }
      });
    }

    touchContainer.addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {

        handleTap(event);

      }
    });

    wrap.addDomHandler(new TransitionEndHandler() {

      @Override
      public void onTransitionEnd(TransitionEndEvent event) {

        handleTransitionEnd();

      }
    }, TransitionEndEvent.getType());
  }

  private void updatePosition(int position) {
    if (position > 0) {
      position = 0;
    }

    if (position < -menu.getOffsetWidth()) {
      position = -menu.getOffsetWidth();
    }

    CssUtil.translate(wrap.getElement(), position, 0);

  }

  @Override
  protected void onLoad() {
    super.onLoad();
    closeMenu();
    // work around for CSS Resource style injection
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {

      @Override
      public void execute() {
        closeMenu();

      }
    });

  }

  private void closeMenu() {
    CloseEvent.fire(this, this);
    wrap.addStyleName(appearance.css().closed());
    wrap.removeStyleName(appearance.css().opened());
    CssUtil.resetTransForm(wrap.getElement());
  }

  private void openMenu() {
    OpenEvent.fire(this, this);
    wrap.removeStyleName(appearance.css().closed());
    wrap.addStyleName(appearance.css().opened());
    CssUtil.resetTransForm(wrap.getElement());
  }

  private void openMenuWithAnimation(int time) {
    state = STATE.ANIMATING_TO_OPEN;
    CssUtil.setTransitionDuration(wrap.getElement(), time);
    openMenu();
  }

  private void closeMenuWithAnimation(int time) {
    state = STATE.ANIMATING_TO_CLOSE;
    CssUtil.setTransitionDuration(wrap.getElement(), time);
    closeMenu();

  }

  private void handleSwipeStart(SwipeStartEvent event) {
    if (state == STATE.CLOSED && event.getDirection() == DIRECTION.LEFT_TO_RIGHT) {

      // ignore?
      if (event.getTouch().getPageX() > 40) {
        return;
      }
      currentDirection = DIRECTION.LEFT_TO_RIGHT;
      maxDistance = event.getDistance();
      state = STATE.SWIPING_TO_OPEN;
      updatePosition(-menu.getOffsetWidth() + event.getDistance());

    }

    if (state == STATE.OPEN && event.getDirection() == DIRECTION.RIGHT_TO_LEFT) {
      state = STATE.SWIPING_TO_CLOSE;
      currentDirection = DIRECTION.RIGHT_TO_LEFT;

      startX = event.getTouch().getPageX();

      maxDistance = event.getDistance();
      updatePosition(-event.getDistance());
    }
  }

  private void handleSwipeMove(SwipeMoveEvent event) {
    if (state == STATE.SWIPING_TO_OPEN) {
      if (event.getDistance() > maxDistance) {
        maxDistance = event.getDistance();
      }
      // not ideal but covers most use cases
      if (maxDistance - event.getDistance() > 40) {
        // started moving in the wrong dir
        currentDirection = DIRECTION.RIGHT_TO_LEFT;
      } else if (maxDistance - event.getDistance() > 0) {
        currentDirection = DIRECTION.LEFT_TO_RIGHT;
      }

      updatePosition(-menu.getOffsetWidth() + event.getDistance());
    }

    if (state == STATE.SWIPING_TO_CLOSE) {
      if (event.getTouch().getPageX() - startX > 0) {
        currentDirection = DIRECTION.LEFT_TO_RIGHT;
        updatePosition(0);
        return;
      }
      if (event.getDistance() > maxDistance) {
        maxDistance = event.getDistance();
      }
      // not ideal but covers most use cases
      if (maxDistance - event.getDistance() > 40) {
        // started moving in the wrong dir
        currentDirection = DIRECTION.LEFT_TO_RIGHT;
      } else if (maxDistance - event.getDistance() > 0) {
        currentDirection = DIRECTION.RIGHT_TO_LEFT;
      }

      updatePosition(-event.getDistance());
    }
  }

  private void handleSwipeEnd(SwipeEndEvent event) {
    if (state == STATE.SWIPING_TO_OPEN) {
      if (event.getDistance() > THRESHOLD && currentDirection == DIRECTION.LEFT_TO_RIGHT) {

        openMenuWithAnimation(150);
      } else {

        closeMenuWithAnimation(150);

      }

    }

    if (state == STATE.SWIPING_TO_CLOSE) {
      if (event.getDistance() > THRESHOLD && currentDirection == DIRECTION.RIGHT_TO_LEFT) {
        closeMenuWithAnimation(150);
      } else {
        openMenuWithAnimation(150);
      }

    }
  }

  private void handleTap(TapEvent event) {
    if (state == STATE.OPEN) {
      if (event.getStartX() > menu.getOffsetWidth()) {
        closeMenuWithAnimation(200);
      }

    }
  }

  private void handleTransitionEnd() {
    switch (state) {
      case ANIMATING_TO_CLOSE:
        state = STATE.CLOSED;
        CssUtil.setTransitionDuration(wrap.getElement(), 0);
        break;
      case ANIMATING_TO_OPEN:
        state = STATE.OPEN;
        CssUtil.setTransitionDuration(wrap.getElement(), 0);
        break;

      default:
        break;
    }
  }

}
