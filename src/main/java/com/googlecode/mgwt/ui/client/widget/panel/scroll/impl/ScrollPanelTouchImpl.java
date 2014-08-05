package com.googlecode.mgwt.ui.client.widget.panel.scroll.impl;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.animation.client.AnimationScheduler.AnimationHandle;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.collection.shared.CollectionFactory;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.collection.shared.LightArrayInt;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.BeforeScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.BeforeScrollMoveEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.BeforeScrollStartEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollAnimationEndEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollAnimationMoveEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollAnimationStartEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollMoveEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanelAppearance;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanelAppearance.ScrollPanelCss;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollRefreshEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollStartEvent;
import com.googlecode.mgwt.ui.client.widget.touch.TouchDelegate;

import java.util.Iterator;
import java.util.logging.Logger;

public class ScrollPanelTouchImpl extends ScrollPanelImpl {

  // TODO fix this by refactoring
  private static final ScrollPanelAppearance SPA = GWT.create(ScrollPanelAppearance.class);

  private static Logger logger = Logger.getLogger(ScrollPanelTouchImpl.class.getName());

  private static double ZOOM_MIN = 1;
  private static double ZOOM_MAX = 4;

  private class TouchListener implements TouchHandler {

    @Override
    public void onTouchStart(TouchStartEvent event) {
      if (!listenForStart)
        return;
      start(event);

    }

    @Override
    public void onTouchMove(TouchMoveEvent event) {
      if (!listenForMoveEvent)
        return;
      move(event);

    }

    @Override
    public void onTouchEnd(TouchEndEvent event) {
      if (!listenForEndEvent)
        return;
      end(event);

    }

    @Override
    public void onTouchCancel(TouchCancelEvent event) {
      if (!listenForCancelEvent) {
        return;
      }
      end(event);
    }

  }

  private static class Step {
    private final int x;

    private final int y;
    private int time;

    public Step(int x, int y, int time) {
      this.x = x;
      this.y = y;
      this.time = time;

    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public int getTime() {
      return time;
    }

    public void setTime(int i) {
      this.time = 0;

    }

    @Override
    public String toString() {
      return "Step [x=" + x + ", y=" + y + ", time=" + time + "]";
    }

  }

  private static class Momentum {

    public static final Momentum ZERO_MOMENTUM = new Momentum(0, 0);

    private final int time;
    private final int dist;

    /**
		 *
		 */
    public Momentum(int dist, int time) {
      this.dist = dist;
      this.time = time;

    }

    public int getTime() {
      return time;
    }

    public int getDist() {
      return dist;
    }

  }

  private static class Snap {
    private final int x;
    private final int y;
    private final int time;

    public Snap(int x, int y, int time) {
      this.x = x;
      this.y = y;
      this.time = time;

    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public int getTime() {
      return time;
    }
  }

  private boolean enabled;
  private int x;
  private int y;
  private LightArray<Step> steps;

  private LightArrayInt pagesX;
  private LightArrayInt pagesY;

  private SimplePanel wrapper;
  private Widget scroller;
  private double scale;

  private double zoomMin;
  private double zoomMax;
  private int wrapperHeight;
  private int wrapperWidth;

  // offset from top
  private int topOffset;
  private int minScrollY;
  private int scrollerWidth;
  private int scrollerHeight;
  private int maxScrollX;
  private int maxScrollY;
  private int dirX;
  private int dirY;

  // enable disable horizontal scroll
  private boolean hScroll;
  // enable disable vertical scroll
  private boolean vScroll;
  private boolean bounceLock;
  private boolean hScrollbar;
  private boolean vScrollbar;
  private int wrapperOffsetLeft;
  private int wrapperOffsetTop;
  private boolean moved;
  private boolean zoomed;
  private boolean animating;
  private boolean useTransform;
  private boolean zoom;
  private boolean useTransistion;
  private int distX;
  private int distY;
  private int absDistX;
  private int absDistY;
  private double touchesDistStart;
  private int originX;
  private int originY;
  private boolean momentum;
  private int absStartX;
  private int absStartY;
  private int startX;
  private int startY;
  private int pointX;
  private int pointY;
  private long startTime;
  private double touchesDist;
  private double lastScale;
  private boolean bounce;
  private double bounceFactor;
  private boolean lockDirection;
  private Timer doubleTapTimer;
  private boolean snap;
  private int snapThreshold;
  private boolean wheelActionZoom;
  private int wheelZoomCount;
  protected AnimationHandle aniTime;
  private int currPageX;
  private int currPageY;
  private String snapSelector;
  private TouchListener touchListener;
  private HandlerRegistration transistionEndRegistration;

  private boolean fixedScrollbar;

  private boolean hideScrollBar;

  private boolean fadeScrollBar;

  private ScrollPanelCss css;

  private boolean shouldHandleResize;

  public ScrollPanelTouchImpl() {

    wrapper = new SimplePanel();
    touchDelegate = new TouchDelegate(wrapper);

    touchListener = new TouchListener();
    setupEvents();

    css = SPA.css();
    css.ensureInjected();

    wrapper.addStyleName(css.scrollPanel());

    initWidget(wrapper);

    shouldHandleResize = true;

    enabled = true;
    steps = CollectionFactory.constructArray();
    scale = 1.0;
    currPageX = 0;
    currPageY = 0;
    pagesX = CollectionFactory.constructIntegerArray();
    pagesY = CollectionFactory.constructIntegerArray();
    wheelZoomCount = 0;

    // setup events!

    // setting standard options
    this.hScroll = true;
    this.vScroll = true;
    this.hScrollDesired = true;
    this.vScrollDesired = true;
    this.x = 0;
    this.y = 0;
    this.bounce = true;
    this.bounceFactor = 2.0;
    this.bounceLock = false;
    this.momentum = true;
    this.lockDirection = true;
    setUseTransform(true);
    setUseTransistion(false);
    this.topOffset = 0;

    // Zoom
    setZoom(false);
    this.zoomMin = ZOOM_MIN;
    this.zoomMax = ZOOM_MAX;

    // snap
    this.snap = false;
    this.snapSelector = null;
    this.snapThreshold = 1;

    this.fixedScrollbar = MGWT.getOsDetection().isAndroid() && !MGWT.getOsDetection().isAndroid4_4_OrHigher();
    this.hideScrollBar = true;
    this.fadeScrollBar = MGWT.getOsDetection().isIOs() && CssUtil.has3d();

    // array for scrollbars
    this.scrollBar = new boolean[2];
    this.scrollBarWrapper = new Element[2];
    this.scrollBarIndicator = new Element[2];
    this.scrollBarSize = new int[2];
    this.scrollbarIndicatorSize = new int[2];
    this.scrollbarMaxScroll = new int[2];
    this.scrollbarProp = new double[2];

    this.scrollBar[DIRECTION.HORIZONTAL.ordinal()] = this.hScroll;
    this.scrollBar[DIRECTION.VERTICAL.ordinal()] = this.vScroll;

  }

  public void setUseTransistion(boolean useTransistion) {
    this.useTransistion = CssUtil.hasTransistionEndEvent() && useTransistion;
  }

  public void setUseTransform(boolean useTransform) {
    this.useTransform = CssUtil.hasTransform() && useTransform;
  }

  public void setZoom(boolean zoom) {
    this.zoom = zoom && useTransform;
  }

  private void checkDOMChanges() {
    if (moved || zoomed || animating || (Math.abs(scrollerWidth - scroller.getOffsetWidth() * scale) < 0.01 && Math.abs(scrollerHeight - scroller.getOffsetHeight() * scale) < 0.01)) {
      return;
    }

    refresh();
  }

  private enum DIRECTION {
    HORIZONTAL, VERTICAL
  };

  private boolean[] scrollBar;
  private Element[] scrollBarWrapper;
  private Element[] scrollBarIndicator;

  private int[] scrollBarSize;
  private int[] scrollbarIndicatorSize;
  private int[] scrollbarMaxScroll;
  private double[] scrollbarProp;

  private boolean hScrollDesired;

  private boolean vScrollDesired;

  private LightArrayInt pagesActualX;

  private LightArrayInt pagesActualY;

  private void scrollBar(final DIRECTION direction) {
    final int dir = direction.ordinal();

    if (!scrollBar[dir]) {
      if (scrollBarWrapper[dir] != null) {
        if (CssUtil.hasTransform()) {
          CssUtil.resetTransForm(scrollBarIndicator[dir]);

        }

        if (scrollBarWrapper[dir].getParentNode() != null) {
          scrollBarWrapper[dir].getParentNode().removeChild(scrollBarWrapper[dir]);
        }

        scrollBarWrapper[dir] = null;
        scrollBarIndicator[dir] = null;

      }

      return;
    }

    Element bar;

    if (scrollBarWrapper[dir] == null) {
      // Create the scrollbar wrapper
      bar = DOM.createDiv();

      CssUtil.setTransitionDuration(bar, (this.fadeScrollBar ? 350 : 0));
      bar.getStyle().setOpacity(this.hideScrollBar ? 0 : 1);

      this.scrollBarWrapper[dir] = bar;
      this.scrollBarWrapper[dir].addClassName(css.scrollBar());
      if (direction == DIRECTION.HORIZONTAL) {
        this.scrollBarWrapper[dir].addClassName(css.scrollBarHorizontal());
        this.scrollBarWrapper[dir].getStyle().setRight(this.scrollBar[DIRECTION.VERTICAL.ordinal()] ? 7 : 2, Unit.PX);
      } else {
        this.scrollBarWrapper[dir].addClassName(css.scrollBarVertical());
        this.scrollBarWrapper[dir].getStyle().setBottom(this.scrollBar[DIRECTION.HORIZONTAL.ordinal()] ? 7 : 2, Unit.PX);
      }

      // Create the scrollbar indicator
      bar = DOM.createDiv();
      bar.addClassName(css.scrollBarBar());

      if (direction == DIRECTION.HORIZONTAL) {

        bar.getStyle().setHeight(100, Unit.PCT);

      } else {

        bar.getStyle().setWidth(100, Unit.PCT);
      }

      scrollBarWrapper[dir].appendChild(bar);
      scrollBarIndicator[dir] = bar;
      this.scrollBarIndicator[dir].addClassName(css.scrollBarBar());

    }

    // only append if size fits!
    if (direction == DIRECTION.HORIZONTAL) {
      if (this.wrapperWidth < this.scrollerWidth) {
        this.wrapper.getElement().appendChild(this.scrollBarWrapper[dir]);

      }
    } else {
      if (this.wrapperHeight < this.scrollerHeight) {
        this.wrapper.getElement().appendChild(this.scrollBarWrapper[dir]);

      }
    }

    int delay = MGWT.getOsDetection().isAndroid() ? 200 : 1;
    new Timer() {
      @Override
      public void run() {
        switch (direction) {
          case HORIZONTAL:
            ScrollPanelTouchImpl.this.scrollBarSize[dir] = ScrollPanelTouchImpl.this.scrollBarWrapper[dir].getClientWidth();
            ScrollPanelTouchImpl.this.scrollbarIndicatorSize[dir] =
                (int) Math.max(Math.round((double) (ScrollPanelTouchImpl.this.scrollBarSize[dir] * ScrollPanelTouchImpl.this.scrollBarSize[dir]) / ScrollPanelTouchImpl.this.scrollerWidth), 8);
            ScrollPanelTouchImpl.this.scrollBarIndicator[dir].getStyle().setWidth(ScrollPanelTouchImpl.this.scrollbarIndicatorSize[dir], Unit.PX);

            ScrollPanelTouchImpl.this.scrollbarMaxScroll[dir] = ScrollPanelTouchImpl.this.scrollBarSize[dir] - ScrollPanelTouchImpl.this.scrollbarIndicatorSize[dir];
            ScrollPanelTouchImpl.this.scrollbarProp[dir] = ((double) (ScrollPanelTouchImpl.this.scrollbarMaxScroll[dir])) / ScrollPanelTouchImpl.this.maxScrollX;
            break;
          case VERTICAL:
            ScrollPanelTouchImpl.this.scrollBarSize[dir] = ScrollPanelTouchImpl.this.scrollBarWrapper[dir].getClientHeight();

            ScrollPanelTouchImpl.this.scrollbarIndicatorSize[dir] =
                (int) Math.max(Math.round((double) (ScrollPanelTouchImpl.this.scrollBarSize[dir] * ScrollPanelTouchImpl.this.scrollBarSize[dir]) / ScrollPanelTouchImpl.this.scrollerHeight), 8);
            ScrollPanelTouchImpl.this.scrollBarIndicator[dir].getStyle().setHeight(ScrollPanelTouchImpl.this.scrollbarIndicatorSize[dir], Unit.PX);
            ScrollPanelTouchImpl.this.scrollbarMaxScroll[dir] = ScrollPanelTouchImpl.this.scrollBarSize[dir] - ScrollPanelTouchImpl.this.scrollbarIndicatorSize[dir];
            ScrollPanelTouchImpl.this.scrollbarProp[dir] = ((double) (ScrollPanelTouchImpl.this.scrollbarMaxScroll[dir])) / ScrollPanelTouchImpl.this.maxScrollY;

            break;

          default:
            break;
        }

        // Reset position
        scrollbarPos(direction, true);
      }
    }.schedule(delay);

  }

  private void resize() {
    int delay = MGWT.getOsDetection().isAndroid() ? 200 : 1;
    new Timer() {
      @Override
      public void run() {
        refresh();
      }
    }.schedule(delay);
  }

  private void pos(int x, int y) {

    x = this.hScroll ? x : 0;
    y = this.vScroll ? y : 0;

    if (useTransform) {
      CssUtil.translate(scroller.getElement(), x, y);
    } else {
      // TODO
      scroller.getElement().getStyle().setLeft(x, Unit.PX);
      scroller.getElement().getStyle().setTop(y, Unit.PX);

    }

    this.x = x;
    this.y = y;

    scrollbarPos(DIRECTION.HORIZONTAL, false);
    scrollbarPos(DIRECTION.VERTICAL, false);

  }

  private void scrollbarPos(DIRECTION direction, boolean hidden) {

    double pos = direction == DIRECTION.HORIZONTAL ? this.x : this.y;
    int size;
    int dir = direction.ordinal();

    if (!this.scrollBar[dir]) {
      return;
    }

    pos = this.scrollbarProp[dir] * pos;

    if (pos < 0) {
      if (!this.fixedScrollbar) {
        size = (int) (this.scrollbarIndicatorSize[dir] + Math.round(pos * 3));
        if (size < 8)
          size = 8;
        if (direction == DIRECTION.HORIZONTAL) {
          this.scrollBarIndicator[dir].getStyle().setWidth(size, Unit.PX);
        } else {
          this.scrollBarIndicator[dir].getStyle().setHeight(size, Unit.PX);
        }

      }
      pos = 0;
    } else {
      if (pos > this.scrollbarMaxScroll[dir]) {
        if (!this.fixedScrollbar) {
          size = (int) (this.scrollbarIndicatorSize[dir] - Math.round((pos - this.scrollbarMaxScroll[dir]) * 3));

          if (size < 8)
            size = 8;

          if (direction == DIRECTION.HORIZONTAL) {
            this.scrollBarIndicator[dir].getStyle().setWidth(size, Unit.PX);
          } else {
            this.scrollBarIndicator[dir].getStyle().setHeight(size, Unit.PX);
          }
          pos = this.scrollbarMaxScroll[dir] + (this.scrollbarIndicatorSize[dir] - size);

        } else {
          pos = this.scrollbarMaxScroll[dir];
        }
      }
    }

    CssUtil.setTransitionsDelay(this.scrollBarWrapper[dir], 0);
    CssUtil.setOpacity(this.scrollBarWrapper[dir], hidden && this.hideScrollBar ? 0 : 1);
    if (direction == DIRECTION.HORIZONTAL) {
      CssUtil.translate(this.scrollBarIndicator[dir], (int) pos, 0);
    } else {
      CssUtil.translate(this.scrollBarIndicator[dir], 0, (int) pos);

    }

  }

  private void start(TouchStartEvent event) {
    int x, y;
    if (!this.enabled) {
      return;
    }

    fireEvent(new BeforeScrollStartEvent(event));

    if (this.useTransistion || this.zoom) {
      setTransistionTime(0);
    }

    this.moved = false;
    this.animating = false;
    this.zoomed = false;
    this.distX = 0;
    this.distY = 0;
    this.absDistX = 0;
    this.absDistY = 0;
    this.dirX = 0;
    this.dirY = 0;

    JsArray<Touch> touches = event.getTouches();

    if (this.zoom && touches.length() > 1) {
      int c1 = Math.abs(touches.get(0).getPageX() - touches.get(1).getPageX());
      int c2 = Math.abs(touches.get(0).getPageY() - touches.get(1).getPageY());
      this.touchesDistStart = Math.sqrt(c1 * c1 + c2 * c2);

      this.originX = Math.abs(touches.get(0).getPageX() + touches.get(1).getPageX() - this.wrapperOffsetLeft * 2 / 2 - this.x);
      this.originY = Math.abs(touches.get(0).getPageY() + touches.get(1).getPageY() - this.wrapperOffsetTop * 2 / 2 - this.y);

      // TODO call on zoom start
    }

    if (this.momentum) {
      if (this.useTransform) {
        int[] pos = CssUtil.getPositionFromTransForm(scroller.getElement());
        x = pos[0];
        y = pos[1];
      } else {
        x = CssUtil.getLeftPositionFromCssPosition(scroller.getElement());
        y = CssUtil.getTopPositionFromCssPosition(scroller.getElement());
      }

      if (x != this.x || y != this.y) {
        if (this.useTransistion) {
          unbindTransistionEnd();
        } else {
          cancelAnimationFrame();
        }
        this.steps = CollectionFactory.constructArray();
        pos(x, y);
      }
    }

    this.absStartX = this.x;
    this.absStartY = this.y;

    this.startX = this.x;
    this.startY = this.y;
    this.pointX = touches.get(0).getPageX();
    this.pointY = touches.get(0).getPageY();

    this.startTime = System.currentTimeMillis();

    fireEvent(new ScrollStartEvent(event));

    bindMoveEvent();
    bindEndEvent();
    bindCancelEvent();
  }

  private void move(TouchMoveEvent event) {
    // old android needs to prevent default on move
    if (MGWT.getOsDetection().isAndroid()) {
      event.preventDefault();
    }

    JsArray<Touch> touches = event.getTouches();
    int deltaX = touches.get(0).getPageX() - this.pointX;
    int deltaY = touches.get(0).getPageY() - this.pointY;
    int newX = this.x + deltaX;
    int newY = this.y + deltaY;
    long timeStamp = System.currentTimeMillis();

    // fire onbeforescroll event
    fireEvent(new BeforeScrollMoveEvent(event));

    if (zoom && touches.length() > 1) {
      int c1 = Math.abs(touches.get(0).getPageX() - touches.get(1).getPageX());
      int c2 = Math.abs(touches.get(0).getPageY() - touches.get(1).getPageY());
      this.touchesDist = Math.sqrt(c1 * c1 + c2 * c2);
      this.zoomed = true;

      double scale = 1 / this.touchesDistStart * this.touchesDist * this.scale;
      if (scale < this.zoomMin) {
        scale = 0.5 * zoomMin * Math.pow(2.0, scale / zoomMin);
      } else {
        if (scale > zoomMax) {
          scale = 2.0 * zoomMax * Math.pow(0.5, zoomMax / scale);
        }
      }
      this.lastScale = scale / this.scale;

      newX = (int) Math.round(this.originX - this.originX * this.lastScale + this.x);
      newY = (int) Math.round(this.originY - this.originY * this.lastScale + this.y);

      CssUtil.setTranslateAndZoom(this.scroller.getElement(), newX, newY, scale);

      // TODO call on zoom
      return;
    }

    this.pointX = touches.get(0).getPageX();
    this.pointY = touches.get(0).getPageY();

    // slower outside the bounds!
    if (newX > 0 || newX < this.maxScrollX) {
      if (bounce) {
        newX = (int) (this.x + Math.round(deltaX / this.bounceFactor));
      } else {
        if (newX >= 0 || this.maxScrollX >= 0) {
          newX = 0;
        } else {
          newX = this.maxScrollX;
        }
      }
    }

    if (newY > this.minScrollY || newY < this.maxScrollY) {
      if (bounce) {
        newY = (int) (this.y + Math.round(deltaY / this.bounceFactor));
      } else {
        if (newY >= this.minScrollY || this.maxScrollY >= 0) {
          newY = this.minScrollY;
        } else {
          newY = this.maxScrollY;
        }
      }
    }

    this.distX += deltaX;
    this.distY += deltaY;
    this.absDistX = Math.abs(this.distX);
    this.absDistY = Math.abs(this.distY);

    if (this.absDistX < 6 && this.absDistY < 6) {
      return;
    }

    if (lockDirection) {
      if (this.absDistX > this.absDistY + 5) {
        newY = this.y;
        deltaY = 0;
      } else {
        if (this.absDistY > this.absDistX + 5) {
          newX = this.x;
          deltaX = 0;
        }
      }
    }

    this.moved = true;
    pos(newX, newY);

    this.dirX = deltaX > 0 ? -1 : deltaX < 0 ? 1 : 0;
    this.dirY = deltaY > 0 ? -1 : deltaY < 0 ? 1 : 0;

    if (timeStamp - this.startTime > 300) {
      this.startTime = timeStamp;
      this.startX = this.x;
      this.startY = this.y;
    }

    fireEvent(new ScrollMoveEvent(event));
  }

  private void end(final TouchEvent<?> event) {
    if (event != null && event.getTouches().length() != 0) {
      return;
    }

    long duration = System.currentTimeMillis() - this.startTime;
    int newPosX = this.x;
    int newPosY = this.y;
    Momentum momentumX = Momentum.ZERO_MOMENTUM;
    Momentum momentumY = Momentum.ZERO_MOMENTUM;

    unbindMoveEvent();
    unbindEndEvent();
    unbindCancelEvent();

    // fire on before scroll end
    fireEvent(new BeforeScrollEndEvent(event));

    if (zoomed) {
      double scale = this.scale * this.lastScale;
      scale = Math.max(this.zoomMin, scale);
      scale = Math.min(this.zoomMax, scale);
      this.lastScale = scale / this.scale;

      this.x = (int) Math.round(this.originX - this.originX * this.lastScale + this.x);
      this.y = (int) Math.round(this.originY - this.originY * this.lastScale + this.y);

      CssUtil.setTransitionDuration(this.scroller.getElement(), 200);
      CssUtil.setTranslateAndZoom(this.scroller.getElement(), this.x, this.y, this.scale);

      this.zoomed = false;
      this.refresh();

      // TODO fire onzoomend

      return;

    }

    if (!this.moved) {
      if (this.doubleTapTimer != null && zoom) {
        doubleTapTimer.cancel();
        doubleTapTimer = null;

        // TODO fire on zoom start

        // TODO fire zoom end after duration
      } else {
        this.doubleTapTimer = new Timer() {

          @Override
          public void run() {
            doubleTapTimer = null;

            // TODO dispatch tap event

          }
        };
        this.doubleTapTimer.schedule(this.zoom ? 250 : 1);
      }

      resetPos(200);

      // TODO fire touchend!
      return;
    }

    if (duration < 300 && momentum) {
      if (newPosX != 0) {
        momentumX = momentum(newPosX - this.startX, duration, -this.x, this.scrollerWidth - this.wrapperWidth + this.x, this.bounce ? this.wrapperWidth : 0);
      }
      if (newPosY != 0) {
        momentumY =
            momentum(newPosY - this.startY, duration, -this.y, (this.maxScrollY < 0 ? this.scrollerHeight - this.wrapperHeight + this.y - this.minScrollY : 0), this.bounce ? this.wrapperHeight : 0);
      }

      newPosX = this.x + momentumX.getDist();
      newPosY = this.y + momentumY.getDist();

      if ((this.x > 0 && newPosX > 0) || (this.x < this.maxScrollX && newPosX < this.maxScrollX)) {
        momentumX = Momentum.ZERO_MOMENTUM;
      }

      if ((this.y > this.minScrollY && newPosY > this.minScrollY) || (this.y < this.maxScrollY && newPosY < this.maxScrollY)) {
        momentumY = Momentum.ZERO_MOMENTUM;
      }

    }

    int distX = 0;
    int distY = 0;

    if (momentumX.getDist() != 0 || momentumY.getDist() != 0) {

      int newDuration = Math.max(Math.max(momentumX.getTime(), momentumY.getTime()), 10);

      if (this.snap) {
        distX = newPosX - this.absStartX;
        distY = newPosY - this.absStartY;

        if (Math.abs(distX) < this.snapThreshold && Math.abs(distY) < this.snapThreshold) {
          scrollTo(this.absStartX, this.absStartY, 200);
        } else {
          Snap snap = snap(newPosX, newPosY);
          newPosX = snap.getX();
          newPosY = snap.getY();
          newDuration = Math.max(snap.getTime(), newDuration);
        }

      }

      scrollTo(newPosX, newPosY, newDuration);

      // TODO fire touch end!
      return;
    }

    if (this.snap) {
      distX = newPosX - this.absDistX;
      distY = newPosY - this.absDistY;

      if (Math.abs(distX) < this.snapThreshold && Math.abs(distY) < this.snapThreshold) {
        scrollTo(this.absStartX, this.absStartY, 200);
      } else {
        Snap snap = snap(this.x, this.y);
        if (snap.x != this.x || snap.y != this.y) {
          scrollTo(snap.x, snap.y, snap.time);
        }
      }

      // fire on touch end
      return;

    }

    resetPos(200);
    // TODO fire on touch end

  }

  private void resetPos(int time) {

    int resetX = this.x >= 0 ? 0 : this.x < this.maxScrollX ? this.maxScrollX : this.x;

    int resetY = this.y >= this.minScrollY || this.maxScrollY > 0 ? this.minScrollY : this.y < this.maxScrollY ? this.maxScrollY : this.y;

    if (resetX == this.x && resetY == this.y) {
      if (this.moved) {
        this.moved = false;
        // fire on scroll end
        fireEvent(new ScrollEndEvent());
      }

      if (this.scrollBar[DIRECTION.HORIZONTAL.ordinal()] && this.hideScrollBar) {
        CssUtil.setTransitionsDelay(this.scrollBarWrapper[DIRECTION.HORIZONTAL.ordinal()], 300);
        CssUtil.setOpacity(this.scrollBarWrapper[DIRECTION.HORIZONTAL.ordinal()], 0);

      }

      if (this.scrollBar[DIRECTION.VERTICAL.ordinal()] && this.hideScrollBar) {
        CssUtil.setTransitionsDelay(this.scrollBarWrapper[DIRECTION.VERTICAL.ordinal()], 300);
        CssUtil.setOpacity(this.scrollBarWrapper[DIRECTION.VERTICAL.ordinal()], 0);

      }

      return;
    }

    scrollTo(resetX, resetY, time);

  }

  private void wheel(int wheelDeltaX, int wheelDeltaY, int pageX, int pageY) {

    if (wheelActionZoom) {
      double deltaScale = this.scale * Math.pow(2, 1.0 / 3 * (wheelDeltaY != 0 ? wheelDeltaY / Math.abs(wheelDeltaY) : 0));
      if (deltaScale < this.zoomMin)
        deltaScale = this.zoomMin;
      if (deltaScale > this.zoomMax)
        deltaScale = this.zoomMax;

      if (Math.abs(deltaScale - this.scale) < 0.00001) {
        if (this.wheelZoomCount == 0) {
          // TODO maybe fire on zoom start
        }
        this.wheelZoomCount++;

        zoom(pageX, pageY, deltaScale, 400);

        new Timer() {

          @Override
          public void run() {
            ScrollPanelTouchImpl.this.wheelZoomCount--;
            if (ScrollPanelTouchImpl.this.wheelZoomCount == 0) {
              // TODO maybe fire zoom end
            }

          }

        }.schedule(400);
      }
      return;
    }

    int deltaX = this.x + wheelDeltaX;
    int deltaY = this.y + wheelDeltaY;

    if (deltaX > 0)
      deltaX = 0;
    else if (deltaX < this.maxScrollX)
      deltaX = this.maxScrollX;

    if (deltaY > this.minScrollY)
      deltaY = this.minScrollY;
    else if (deltaY < this.maxScrollY)
      deltaY = this.maxScrollY;

    scrollTo(deltaX, deltaY, 0);

  }

  private void mouseOut(MouseOutEvent event) {

    EventTarget relatedTarget = event.getRelatedTarget();

    if (relatedTarget == null) {

      end(null);
      return;
    }

    if (!Node.is(relatedTarget)) {
      end(null);
      return;
    }

    Node tmp = relatedTarget.cast();

    while (true) {

      tmp = tmp.getParentNode();

      if (tmp == this.wrapper.getElement()) {
        return;
      }

      if (tmp == null) {
        break;
      }

    }

    this.end(null);

  }

  private void onTransistionEnd(TransitionEndEvent event, boolean issueEvent) {
    EventTarget eventTarget = event.getNativeEvent().getEventTarget();
    if (Node.is(eventTarget)) {
      if (Element.is(eventTarget)) {
        Element target = eventTarget.cast();
        Element scrollerElement = this.scroller.getElement();
        // reference id should be okay according to
        // http://google-web-toolkit.googlecode.com/svn/javadoc/latest/com/google/gwt/user/client/DOM.html#compare(com.google.gwt.user.client.Element,
        // com.google.gwt.user.client.Element)
        if (target != scrollerElement) {
          return;
        }

      }
    }

    unbindTransistionEnd();

    startAnimation(issueEvent);

  }

  private void startAnimation(final boolean issueEvent) {
    if (this.animating)
      return;

    final int startX = this.x;
    final int startY = this.y;

    if (this.steps.length() == 0) {
      resetPos(400);
      return;
    }
    fireEvent(new ScrollAnimationStartEvent());
    final Step step = this.steps.shift();

    if (step.getX() == startX && step.getY() == startY) {
      step.setTime(0);
    }

    this.animating = true;
    this.moved = true;

    if (this.useTransistion) {
      setTransistionTime(step.getTime());
      pos(step.getX(), step.getY());
      this.animating = false;
      if (step.getTime() != 0) {
        bindTransistionEndEvent(issueEvent);
      } else {
        resetPos(0);
      }
      return;
    }

    final long startTime = System.currentTimeMillis();

    final AnimationCallback animationCallback = new AnimationCallback() {

      @Override
      public void execute(double now) {

        if (now >= startTime + step.getTime()) {
          ScrollPanelTouchImpl.this.pos(step.x, step.y);
          ScrollPanelTouchImpl.this.animating = false;
          if (issueEvent) {
            fireEvent(new ScrollAnimationEndEvent());
          }
          ScrollPanelTouchImpl.this.startAnimation(issueEvent);
          return;
        }

        now = (now - startTime) / step.getTime() - 1;
        double easeOut = Math.sqrt(1 - now * now);
        int newX = (int) Math.round((step.getX() - startX) * easeOut + startX);
        int newY = (int) Math.round((step.getY() - startY) * easeOut + startY);
        ScrollPanelTouchImpl.this.pos(newX, newY);
        fireEvent(new ScrollAnimationMoveEvent());
        if (ScrollPanelTouchImpl.this.animating)
          ScrollPanelTouchImpl.this.aniTime = AnimationScheduler.get().requestAnimationFrame(this);

      }
    };

    animationCallback.execute(startTime);

  }

  private void setTransistionTime(int time) {

    CssUtil.setTransitionDuration(scroller.getElement(), time);

    if (vScrollbar) {
      CssUtil.setTransitionDuration(this.scrollBarIndicator[DIRECTION.VERTICAL.ordinal()], time);
    }

    if (hScrollbar) {
      CssUtil.setTransitionDuration(this.scrollBarIndicator[DIRECTION.HORIZONTAL.ordinal()], time);
    }

  }

  private Momentum momentum(int dist, long time, int maxDistUpper, int maxDistLower, int size) {
    double deceleration = 0.0006;
    double speed = ((double) (Math.abs(dist))) / time;
    double newDist = (speed * speed) / (2 * deceleration);
    double newTime = 0;
    double outSideDist = 0;

    // Proportinally reduce speed if we are outside of the boundaries
    if (dist > 0 && newDist > maxDistUpper) {
      outSideDist = size / (6 / (newDist / speed * deceleration));
      maxDistUpper = (int) (maxDistUpper + outSideDist);
      speed = speed * maxDistUpper / newDist;
      newDist = maxDistUpper;
    } else if (dist < 0 && newDist > maxDistLower) {
      outSideDist = size / (6 / (newDist / speed * deceleration));
      maxDistLower = (int) (maxDistLower + outSideDist);
      speed = speed * maxDistLower / newDist;
      newDist = maxDistLower;
    }

    newDist = newDist * (dist < 0 ? -1 : 1);
    newTime = speed / deceleration;

    return new Momentum((int) Math.round(newDist), (int) Math.round(newTime));
  }

  private int[] offSet(com.google.gwt.dom.client.Element el) {
    int left = -el.getOffsetLeft();
    int top = -el.getOffsetTop();

    com.google.gwt.dom.client.Element domElem = el;
    while (true) {
      domElem = domElem.getOffsetParent();
      if (domElem == null)
        break;
      left -= domElem.getOffsetLeft();
      top -= domElem.getOffsetTop();
    }

    if (el != this.wrapper.getElement()) {
      left *= this.scale;
      top *= this.scale;
    }

    return new int[] {left, top};
  }

  private Snap snap(int x, int y) {

    // Check page X
    int page = this.pagesX.length() - 1;
    for (int i = 0, l = this.pagesX.length(); i < l; i++) {
      if (x >= this.pagesX.get(i)) {
        page = i;
        break;
      }
    }
    if (page == this.currPageX && page > 0 && this.dirX < 0)
      page--;
    x = this.pagesX.get(page);
    int sizeX = Math.abs(x - this.pagesX.get(this.currPageX));
    sizeX = sizeX != 0 ? Math.abs(this.x - x) / sizeX * 500 : 0;
    this.currPageX = page;

    // Check page Y
    page = this.pagesY.length() - 1;
    for (int i = 0; i < page; i++) {
      if (y >= this.pagesY.get(i)) {
        page = i;
        break;
      }
    }
    if (page == this.currPageY && page > 0 && this.dirY < 0)
      page--;
    y = this.pagesY.get(page);
    int sizeY = Math.abs(y - this.pagesY.get(this.currPageY));
    sizeY = sizeY != 0 ? Math.abs(this.y - y) / sizeY * 500 : 0;
    this.currPageY = page;

    // Snap with constant speed (proportional duration)
    int time = Math.max(sizeX, sizeY);
    if (time == 0) {
      time = 200;
    }

    return new Snap(x, y, time);
  }

  private void bindTransistionEndEvent(final boolean issueEvent) {
    if (CssUtil.hasTransistionEndEvent()) {
      transistionEndRegistration = scroller.addDomHandler(new TransitionEndHandler() {

        @Override
        public void onTransitionEnd(TransitionEndEvent event) {
          onTransistionEnd(event, issueEvent);

        }
      }, TransitionEndEvent.getType());
    }

  }

  private void unbindTransistionEnd() {
    if (transistionEndRegistration != null) {
      transistionEndRegistration.removeHandler();
      transistionEndRegistration = null;
    }

  }

  @Override
  public void refresh() {
    if (!isAttached())
      return;

    if (scale < zoomMin) {
      scale = zoomMin;
    }
    wrapperHeight = getClientHeight(wrapper.getElement());
    if (wrapperHeight == 0) {
      wrapperHeight = 1;
    }
    wrapperWidth = getClientWidth(wrapper.getElement());
    if (wrapperWidth == 0) {
      wrapperWidth = 1;
    }

    minScrollY = -topOffset;

    scrollerWidth = (int) Math.round((scroller.getOffsetWidth() + getMarginWidth(scroller.getElement())) * scale);
    scrollerHeight = (int) Math.round((scroller.getOffsetHeight() + minScrollY + +getMarginHeight(scroller.getElement())) * scale);

    maxScrollX = wrapperWidth - scrollerWidth;

    maxScrollY = wrapperHeight - scrollerHeight + minScrollY + offsetMaxY;

    dirX = 0;
    dirY = 0;

    hScroll = (hScrollDesired && maxScrollX < 0);
    vScroll = vScrollDesired && (!bounceLock && !hScroll || scrollerHeight > wrapperHeight);

    hScrollbar = hScroll && hScrollbar;
    vScrollbar = vScroll && vScrollbar && scrollerHeight > wrapperHeight;

    int[] offSet = offSet(ScrollPanelTouchImpl.this.wrapper.getElement());

    wrapperOffsetLeft = -offSet[0];
    wrapperOffsetTop = -offSet[1];

    // prep stuff
    if (ScrollPanelTouchImpl.this.snapSelector != null) {
      ScrollPanelTouchImpl.this.pagesX = CollectionFactory.constructIntegerArray();
      ScrollPanelTouchImpl.this.pagesY = CollectionFactory.constructIntegerArray();

      ScrollPanelTouchImpl.this.pagesActualX = CollectionFactory.constructIntegerArray();
      ScrollPanelTouchImpl.this.pagesActualY = CollectionFactory.constructIntegerArray();

      JsArray<com.google.gwt.dom.client.Element> elements = querySelectorAll(ScrollPanelTouchImpl.this.scroller.getElement(), snapSelector);

      for (int i = 0; i < elements.length(); i++) {
        int[] pos = offSet(elements.get(i));
        int left = pos[0] + ScrollPanelTouchImpl.this.wrapperOffsetLeft;
        int top = pos[1] + ScrollPanelTouchImpl.this.wrapperOffsetTop;
        ScrollPanelTouchImpl.this.pagesX.push((int) (left < ScrollPanelTouchImpl.this.maxScrollX ? ScrollPanelTouchImpl.this.maxScrollX : left * ScrollPanelTouchImpl.this.scale));
        ScrollPanelTouchImpl.this.pagesY.push((int) (top < ScrollPanelTouchImpl.this.maxScrollY ? ScrollPanelTouchImpl.this.maxScrollY : top * ScrollPanelTouchImpl.this.scale));

        ScrollPanelTouchImpl.this.pagesActualX.push((int) (left * ScrollPanelTouchImpl.this.scale));
        ScrollPanelTouchImpl.this.pagesActualY.push((int) (top * ScrollPanelTouchImpl.this.scale));
      }
    } else {
      if (ScrollPanelTouchImpl.this.snap) {
        int pos = 0;
        int page = 0;
        ScrollPanelTouchImpl.this.pagesX = CollectionFactory.constructIntegerArray();

        while (pos >= ScrollPanelTouchImpl.this.maxScrollX) {
          ScrollPanelTouchImpl.this.pagesX.set(page, pos);
          pos = pos - ScrollPanelTouchImpl.this.wrapperWidth;
          page++;
        }
        if (ScrollPanelTouchImpl.this.maxScrollX % ScrollPanelTouchImpl.this.wrapperWidth != 0)
          ScrollPanelTouchImpl.this.pagesX.set(ScrollPanelTouchImpl.this.pagesX.length(), ScrollPanelTouchImpl.this.maxScrollX
              - ScrollPanelTouchImpl.this.pagesX.get(ScrollPanelTouchImpl.this.pagesX.length() - 1) + ScrollPanelTouchImpl.this.pagesX.get(ScrollPanelTouchImpl.this.pagesX.length() - 1));

        pos = 0;
        page = 0;
        ScrollPanelTouchImpl.this.pagesY = CollectionFactory.constructIntegerArray();
        while (pos >= ScrollPanelTouchImpl.this.maxScrollY) {
          ScrollPanelTouchImpl.this.pagesY.set(page, pos);
          pos = pos - ScrollPanelTouchImpl.this.wrapperHeight;
          page++;
        }
        if (ScrollPanelTouchImpl.this.maxScrollY % ScrollPanelTouchImpl.this.wrapperHeight != 0)
          ScrollPanelTouchImpl.this.pagesY.set(ScrollPanelTouchImpl.this.pagesY.length(), ScrollPanelTouchImpl.this.maxScrollY
              - ScrollPanelTouchImpl.this.pagesY.get(ScrollPanelTouchImpl.this.pagesY.length() - 1) + ScrollPanelTouchImpl.this.pagesY.get(ScrollPanelTouchImpl.this.pagesY.length() - 1));

        ScrollPanelTouchImpl.this.pagesActualX = this.pagesX;
        ScrollPanelTouchImpl.this.pagesActualY = this.pagesY;
      }
    }

    scrollBar(DIRECTION.HORIZONTAL);
    scrollBar(DIRECTION.VERTICAL);

    if (!ScrollPanelTouchImpl.this.zoomed) {
      CssUtil.setTransitionDuration(ScrollPanelTouchImpl.this.scroller.getElement(), 0);
      resetPos(200);
    }

    updateDefaultStyles();

    // fire refresh event
    fireEvent(new ScrollRefreshEvent());

  }

  @Override
  public void scrollTo(int x, int y, int time) {
    scrollTo(x, y, time, false);

  }

  public void scrollTo(int x, int y, int time, boolean relative) {
    scrollTo(x, y, time, relative, true);
  }

  public void scrollTo(int x, int y, int time, boolean relative, boolean issueEvent) {
    stop();

    int destX;
    int destY;

    if (relative) {
      destX = this.x - x;
      destY = this.y - y;
    } else {
      destX = x;
      destY = y;
    }

    Step step = new Step(destX, destY, time);

    this.steps.push(step);

    startAnimation(issueEvent);

  }

  public void scrollToElement(com.google.gwt.dom.client.Element el, int time) {

    int[] offSet = offSet(el);
    int left = offSet[0] + this.wrapperOffsetLeft;
    int top = offSet[1] + this.wrapperOffsetTop;

    left = left > 0 ? 0 : left < this.maxScrollX ? this.maxScrollX : left;
    top = top > this.minScrollY ? this.minScrollY : top < this.maxScrollY ? this.maxScrollY : top;

    scrollTo(left, top, time);
  }

  public void scrollToPage(int pageX, int pageY) {
    scrollToPage(pageX, pageY, 400);
  }

  public void scrollToPage(int pageX, int pageY, int time) {
    scrollToPage(pageX, pageY, time, true);
  }


  @Override
  public void scrollToPage(int pageX, int pageY, int time, boolean issueEvent) {
    if (issueEvent) {
      fireEvent(new ScrollStartEvent(null));
    }

    int x, y;
    if (this.snap || snapSelector != null) {

      pageX = pageX < 0 ? 0 : pageX > this.pagesX.length() - 1 ? this.pagesX.length() - 1 : pageX;
      pageY = pageY < 0 ? 0 : pageY > this.pagesY.length() - 1 ? this.pagesY.length() - 1 : pageY;

      this.currPageX = pageX;
      this.currPageY = pageY;
      x = this.pagesX.get(pageX);
      y = this.pagesY.get(pageY);
    } else {
      x = -this.wrapperWidth * pageX;
      y = -this.wrapperHeight * pageY;
      if (x < this.maxScrollX)
        x = this.maxScrollX;
      if (y < this.maxScrollY)
        y = this.maxScrollY;
    }

    scrollTo(x, y, time, false, issueEvent);
  }

  public void disable() {
    stop();
    resetPos(0);
    this.enabled = false;

    unbindMoveEvent();
    unbindEndEvent();
    unbindCancelEvent();
  }

  public void enable() {
    this.enabled = true;
  }

  public void stop() {
    if (this.useTransistion) {
      unbindTransistionEnd();
    } else {
      if (this.aniTime != null)
        this.aniTime.cancel();
    }

    this.steps = CollectionFactory.constructArray();
    this.moved = false;
    this.animating = false;
  }

  public void zoom(int x, int y, double scale, int time) {

    if (!this.useTransform)
      return;

    double relScale = scale / this.scale;

    this.zoomed = true;

    x = x - this.wrapperOffsetLeft - this.x;
    y = y - this.wrapperOffsetTop - this.y;

    this.x = (int) Math.round(x - x * relScale + this.x);
    this.y = (int) Math.round(y - y * relScale + this.y);

    this.scale = scale;
    this.refresh();

    this.x = this.x > 0 ? 0 : this.x < this.maxScrollX ? this.maxScrollX : this.x;
    this.y = this.y > this.minScrollY ? this.minScrollY : this.y < this.maxScrollY ? this.maxScrollY : this.y;

    CssUtil.setTransitionDuration(this.scroller.getElement(), time);
    CssUtil.setTranslateAndZoom(this.scroller.getElement(), x, y, scale);
    this.zoomed = true;

  }

  public boolean isReady() {
    return !this.moved && !this.zoomed && !this.animating;
  }

  /*
   * Helpers!
   */

  // TODO move in util
  private native int getClientHeight(Element element)/*-{
		return element.clientHeight || 0;
  }-*/;

  private native int getClientWidth(Element element) /*-{
		return element.clientWidth || 0;
  }-*/;

  private native JsArray<com.google.gwt.dom.client.Element> querySelectorAll(Element el, String selector)/*-{
		return el.querySelectorAll(selector);
  }-*/;

  @Override
  public void add(Widget w) {
    if (scroller != null) {
      throw new IllegalStateException("scrollpanel can only have one child");
    }
    setWidget(w);

  }

  /*
   * GWT stuff
   */

  @Override
  public void clear() {
    setWidget(null);

  }

  @Override
  public Iterator<Widget> iterator() {
    return wrapper.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    if (w == scroller) {
      scroller = null;
      return wrapper.remove(w);
    }
    return false;
  }

  @Override
  public void setUsePos(boolean pos) {
    this.useTransform = !pos;
  }

  @Override
  public boolean isScrollingEnabledX() {
    return hScroll;
  }

  @Override
  public void setScrollingEnabledX(boolean scrollingEnabledX) {
    this.hScrollDesired = scrollingEnabledX;
    // this.hScroll = scrollingEnabledX;
    // this.scrollBar[DIRECTION.HORIZONTAL.ordinal()] = scrollingEnabledX;

  }

  @Override
  public boolean isScrollingEnabledY() {
    return this.vScroll;
  }

  @Override
  public void setScrollingEnabledY(boolean scrollingEnabledY) {
    this.vScrollDesired = scrollingEnabledY;
    // this.vScroll = scrollingEnabledY;
    // this.scrollBar[DIRECTION.VERTICAL.ordinal()] = scrollingEnabledY;
  }

  @Override
  public void setWidget(IsWidget child) {
    setWidget(child.asWidget() != null ? child.asWidget() : null);

  }

  private HandlerRegistration touchStartRegistration;
  private HandlerRegistration orientationChangeRegistration;
  private TouchDelegate touchDelegate;
  private HandlerRegistration mouseOutRegistration;
  private HandlerRegistration mouseWheelRegistration;
  private HandlerRegistration touchCancelRegistration;
  private HandlerRegistration touchEndRegistration;
  private HandlerRegistration touchMoveRegistration;

  private boolean listenForStart;

  private boolean listenForCancelEvent;

  private boolean listenForEndEvent;

  private boolean listenForMoveEvent;

  private int offsetMaxY;

  public void setWidget(Widget w) {

    // clear old event handlers
    unbindStartEvent();
    unbindResizeEvent();
    if (MGWT.getOsDetection().isDesktop()) {
      unbindMouseoutEvent();
      unbindMouseWheelEvent();
    }

    if (scroller != null) {
      // clean up
      scroller.removeStyleName(css.container());
      remove(scroller);

    }

    scroller = w;

    if (scroller != null) {
      wrapper.setWidget(scroller);
      scroller.addStyleName(css.container());
      if (isAttached()) {
        bindResizeEvent();
        bindStartEvent();
        if (MGWT.getOsDetection().isDesktop()) {
          bindMouseoutEvent();
          bindMouseWheelEvent();
        }
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {

          @Override
          public void execute() {
            refresh();

          }
        });

      }

    }

  }

  @Override
  protected void onDetach() {
    super.onDetach();
    unbindResizeEvent();
  }

  @Override
  protected void onAttach() {
    super.onAttach();

    if (scroller != null) {

      // bind events
      bindResizeEvent();
      bindStartEvent();
      if (MGWT.getOsDetection().isDesktop()) {
        bindMouseoutEvent();
        bindMouseWheelEvent();
      }

      Scheduler.get().scheduleDeferred(new ScheduledCommand() {

        @Override
        public void execute() {
          refresh();

        }
      });

    }

  }

  private void setupEvents() {
    touchMoveRegistration = touchDelegate.addTouchMoveHandler(touchListener);
    touchStartRegistration = touchDelegate.addTouchStartHandler(touchListener);
    touchCancelRegistration = touchDelegate.addTouchCancelHandler(touchListener);
    touchEndRegistration = touchDelegate.addTouchEndHandler(touchListener);

  }

  private void bindMouseWheelEvent() {
    mouseWheelRegistration = scroller.addDomHandler(new MouseWheelHandler() {

      @Override
      public void onMouseWheel(MouseWheelEvent event) {
        int wheelDeltaX = 0;
        int wheelDeltaY = 0;

        if (isScrollingEnabledX()) {
          wheelDeltaX = getMouseWheelVelocityX(event.getNativeEvent()) / 10;
        }

        if (isScrollingEnabledY()) {
          wheelDeltaY = getMouseWheelVelocityY(event.getNativeEvent()) / 10;
        }
        wheel(wheelDeltaX, wheelDeltaY, event.getClientX(), event.getClientY());

      }
    }, MouseWheelEvent.getType());

  }

  private native int getMouseWheelVelocityX(NativeEvent evt)/*-{
		return Math.round(-evt.wheelDeltaX) || 0;
  }-*/;

  private native int getMouseWheelVelocityY(NativeEvent evt)/*-{

		var val = (evt.detail * 40) || -evt.wheelDeltaY || 0;
		return Math.round(val);
  }-*/;

  private void unbindMouseWheelEvent() {
    if (mouseWheelRegistration != null) {
      mouseWheelRegistration.removeHandler();
      mouseWheelRegistration = null;
    }

  }

  private void bindMouseoutEvent() {
    mouseOutRegistration = this.wrapper.addDomHandler(new MouseOutHandler() {

      @Override
      public void onMouseOut(MouseOutEvent event) {
        mouseOut(event);

      }
    }, MouseOutEvent.getType());

  }

  private void unbindMouseoutEvent() {
    if (mouseOutRegistration != null) {
      mouseOutRegistration.removeHandler();
      mouseOutRegistration = null;
    }

  }

  private void bindStartEvent() {
    listenForStart = true;
  }

  private void unbindStartEvent() {
    listenForStart = false;

  }

  private void bindCancelEvent() {
    listenForCancelEvent = true;
  }

  private void bindEndEvent() {
    listenForEndEvent = true;

  }

  private void bindMoveEvent() {
    listenForMoveEvent = true;
  }

  private void unbindCancelEvent() {
    listenForCancelEvent = false;

  }

  private void unbindEndEvent() {
    listenForEndEvent = false;
  }

  private void unbindMoveEvent() {
    listenForMoveEvent = false;
  }

  /**
	 *
	 */
  private void bindResizeEvent() {
    if (!MGWT.getFormFactor().isDesktop()) {
      orientationChangeRegistration = MGWT.addOrientationChangeHandler(new OrientationChangeHandler() {

        @Override
        public void onOrientationChanged(OrientationChangeEvent event) {
          if (shouldHandleResize) {
            resize();
          }

        }
      });
    } else {
      orientationChangeRegistration = Window.addResizeHandler(new ResizeHandler() {

        @Override
        public void onResize(ResizeEvent event) {
          if (shouldHandleResize) {
            resize();
          }

        }
      });
    }

  }

  private void unbindResizeEvent() {
    if (orientationChangeRegistration != null) {
      orientationChangeRegistration.removeHandler();
      orientationChangeRegistration = null;
    }
  }

  private void updateDefaultStyles() {
    if (scroller != null) {

      CssUtil.setTransistionProperty(scroller.getElement(), useTransform ? CssUtil.getTransformProperty() : "top left");
      CssUtil.setTransitionDuration(scroller.getElement(), 0);
      CssUtil.setTransFormOrigin(scroller.getElement(), 0, 0);
      if (useTransistion) {
        CssUtil.setTransistionTimingFunction(scroller.getElement(), "cubic-bezier(0.33,0.66,0.66,1)");
      }
      if (useTransform) {
        CssUtil.translate(scroller.getElement(), this.x, this.y);
      } else {
        scroller.getElement().getStyle().setPosition(Position.ABSOLUTE);
        scroller.getElement().getStyle().setLeft(this.x, Unit.PX);
        scroller.getElement().getStyle().setTop(this.y, Unit.PX);

      }

      if (useTransistion) {
        this.fixedScrollbar = true;
      }

    }

  }

  private void cancelAnimationFrame() {
    if (aniTime != null) {
      aniTime.cancel();
      aniTime = null;
    }

  }

  @Override
  public void setOffSetY(int y) {
    this.topOffset = y;

  }

  @Override
  public void setMaxScrollY(int y) {
    this.maxScrollY = y;

  }

  @Override
  public int getMaxScrollY() {
    return this.maxScrollY;
  }

  @Override
  public void setMinScrollY(int y) {
    this.minScrollY = y;

  }

  @Override
  public int getMinScrollY() {
    return this.minScrollY;
  }

  @Override
  public void setBounce(boolean bounce) {
    this.bounce = bounce;

  }

  @Override
  public void setMomentum(boolean momentum) {
    this.momentum = momentum;

  }

  @Override
  public void setSnap(boolean snap) {
    this.snap = snap;

  }

  @Override
  public void setSnapThreshold(int threshold) {
    this.snapThreshold = threshold;

  }

  private native int getMarginWidth(Element el)/*-{
		var left = 0;
		var right = 0;
		var style = $wnd.getComputedStyle(el);

		left = parseInt(style.marginLeft, 10) || 0;
		right = parseInt(style.marginRight, 10) || 0;

		return left + right;
  }-*/;

  private native int getMarginHeight(Element el)/*-{

		var top = 0;
		var bottom = 0;
		var style = $wnd.getComputedStyle(el);

		top = parseInt(style.marginTop, 10) || 0;
		bottom = parseInt(style.marginBottom, 10) || 0;

		return top + bottom;
  }-*/;

  @Override
  public int getY() {
    return y;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public void setBounceFactor(double factor) {
    this.bounceFactor = factor;
  }

  @Deprecated
  @Override
  public void setShowScrollBarX(boolean show) {
    this.hScrollbar = show;
    scrollBar[DIRECTION.VERTICAL.ordinal()] = show;
  }

  @Deprecated
  @Override
  public void setShowScrollBarY(boolean show) {
    this.vScrollbar = show;
    scrollBar[DIRECTION.HORIZONTAL.ordinal()] = show;
  }

  @Override
  public void setShowHorizontalScrollBar(boolean show) {
    this.hScrollbar = show;
    scrollBar[DIRECTION.HORIZONTAL.ordinal()] = show;
  }

  @Override
  public void setShowVerticalScrollBar(boolean show) {
    this.vScrollbar = show;
    scrollBar[DIRECTION.VERTICAL.ordinal()] = show;
  }

  @Override
  public int getCurrentPageX() {
    return currPageX;
  }

  @Override
  public int getCurrentPageY() {
    return currPageY;
  }

  @Override
  public void setAutoHandleResize(boolean handle) {
    shouldHandleResize = handle;

  }

  @Override
  public void setOffSetMaxY(int height) {
    this.offsetMaxY = height;

  }

  @Override
  public void setSnapSelector(String selector) {
    this.snapSelector = selector;

  }

  @Override
  public LightArrayInt getPagesY() {
    return this.pagesActualY;
  }

  @Override
  public LightArrayInt getPagesX() {
    return this.pagesActualX;
  }

  public void setHideScrollBar(boolean hideScrollBar) {
    this.hideScrollBar = hideScrollBar;
  }

  @Override
  public void setScrollLock(boolean lock) {
    this.lockDirection = lock;
  }
}
