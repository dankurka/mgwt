package com.googlecode.mgwt.ui.client.widget.animation.impl;

import com.google.gwt.user.client.ui.IsWidget;

import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationEndCallback;

public interface AnimationWidgetImpl extends IsWidget {
  /**
   * Set the first Widget of the display
   * 
   * @param w the widet to set
   */
  public void setFirstWidget(IsWidget w);

  /**
   * Set the second Widget of the display
   * 
   * @param w the widet to set
   */
  public void setSecondWidget(IsWidget w);

  /**
   * Start an animation on the display.
   * 
   * @param animation the animation that the display should execute
   * @param animateToFirst - which widget should be visible at the end of the animation
   * @param callback a {@link com.googlecode.mgwt.mvp.client.AnimationEndCallback} object.
   */
  public void animate(Animation animation, boolean animateToFirst, AnimationEndCallback callback);
}
