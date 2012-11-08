package com.googlecode.mgwt.mvp.client.display;

import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;
import com.googlecode.mgwt.mvp.client.resources.AnimationCss;
import com.googlecode.mgwt.mvp.client.resources.AnimationSelector;

public class AnimatableDisplayIE9Impl implements AnimatableDisplay {

  protected FlowPanel main;

  protected SimplePanel first;

  protected SimplePanel second;

  private final AnimationCss css;

  public AnimatableDisplayIE9Impl() {
    this(AnimationSelector.getBundle().animationCss());
  }

  public AnimatableDisplayIE9Impl(AnimationCss css) {
    this.css = css;
    this.css.ensureInjected();
    main = new FlowPanel();

    main.setStylePrimaryName(this.css.display());
    main.getElement().getStyle().setPosition(Position.ABSOLUTE);
    main.getElement().getStyle().setOverflow(Overflow.HIDDEN);
    main.getElement().getStyle().setLeft(0, Unit.PX);
    main.getElement().getStyle().setTop(0, Unit.PX);
    main.getElement().getStyle().setRight(0, Unit.PX);
    main.getElement().getStyle().setBottom(0, Unit.PX);

    first = new SimplePanel();
    first.addStyleName(this.css.displayContainer());
    first.getElement().getStyle().setBackgroundColor("blue");
    first.getElement().getStyle().setPosition(Position.ABSOLUTE);
    first.setWidth("100%");
    first.setHeight("100%");
    first.getElement().getStyle().setOverflow(Overflow.HIDDEN);
    main.add(first);

    second = new SimplePanel();
    second.addStyleName(this.css.displayContainer());
    second.getElement().getStyle().setBackgroundColor("blue");
    second.getElement().getStyle().setPosition(Position.ABSOLUTE);
    second.setWidth("100%");
    second.setHeight("100%");
    second.getElement().getStyle().setOverflow(Overflow.HIDDEN);
    main.add(second);
    //
    // .displayContainer {
    // position: absolute;
    // width: 100%;
    // height: 100%;
    // overflow:hidden;
    //
    // background-color: red;
    // }
    //
    //
    //
    // .display {
    // position: absolute;
    // top: 0px;
    // left: 0px;
    // right: 0px;
    // bottom: 0px;
    // overflow:hidden;
    // background-color: blue;
    //
    // }

  }

  @Override
  public Widget asWidget() {
    return main;
  }

  @Override
  public void setFirstWidget(IsWidget w) {
    first.setWidget(w);
  }

  @Override
  public void setSecondWidget(IsWidget w) {
    second.setWidget(w);
  }

  private class AnimationFrame implements AnimationCallback {

    private final double endTime;
    // private final Animation animation;
    // private double startTime;
    private final AnimationEndCallback callback;
    private final boolean animateToFirst;

    public AnimationFrame(long startTime, long endTime, Animation animation, AnimationEndCallback callback, boolean animateToFirst) {
      this.endTime = endTime;
      // this.startTime = startTime;
      // this.animation = animation;
      this.callback = callback;
      this.animateToFirst = animateToFirst;

      if (animateToFirst) {
        main.add(first);
      } else {
        main.add(second);
      }

      // int offsetWidth = main.getOffsetWidth();
      //
      // CssUtil.translate(first.getElement(), 0, 0);
      // CssUtil.translate(second.getElement(), offsetWidth, 0);

    }

    @Override
    public void execute(double timestamp) {
      long now = System.currentTimeMillis();
      if (now > endTime) {
        // render end position and quit

        if (animateToFirst) {
          second.removeFromParent();
        } else {
          first.removeFromParent();
        }

        // fire animation end
        callback.onAnimationEnd();

        return;
      }

      // render current step

      // int offsetWidth = main.getOffsetWidth();

      // int pos = (int) (-offsetWidth * (now - startTime) / (endTime - startTime));

      AnimationScheduler.get().requestAnimationFrame(this);

      // CssUtil.translate(first.getElement(), 0, 0);
      // CssUtil.translate(second.getElement(), 0, 0);

    }

  }

  @Override
  public void animate(Animation animation, boolean animateToFirst, AnimationEndCallback callback) {

    AnimationFrame frame = new AnimationFrame(System.currentTimeMillis(), System.currentTimeMillis() + 300, animation, callback, animateToFirst);

    AnimationScheduler.get().requestAnimationFrame(frame);

  }

}
