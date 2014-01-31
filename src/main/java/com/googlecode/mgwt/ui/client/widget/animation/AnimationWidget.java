package com.googlecode.mgwt.ui.client.widget.animation;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;

import com.googlecode.mgwt.ui.client.widget.animation.impl.AnimationWidgetImpl;

public class AnimationWidget extends Composite implements AnimatableDisplay {

  private final AnimationWidgetImpl IMPL = GWT.create(AnimationWidgetImpl.class);

  public AnimationWidget() {
    initWidget(IMPL.asWidget());
  }

  @Override
  public void setFirstWidget(IsWidget w) {
    IMPL.setFirstWidget(w);
  }

  @Override
  public void setSecondWidget(IsWidget w) {
    IMPL.setSecondWidget(w);
  }

  @Override
  public void animate(Animation animation, boolean animateToFirst, AnimationEndCallback callback) {
    IMPL.animate(animation, animateToFirst, callback);
  }
}
