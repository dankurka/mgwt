package com.googlecode.mgwt.ui.client.widget.animation.bundle;

import com.googlecode.mgwt.ui.client.widget.animation.Animation;

public abstract class AnimationBase implements Animation {

  protected final String key;
  protected final boolean reversed;

  public AnimationBase(String key, boolean reversed) {
    this.key = key;
    this.reversed = reversed;
  }

  @Override
  public String key() {
    if (reversed) {
      return key + "-reverse";
    }
    return key;
  }

  @Override
  public boolean isReversed() {
    return reversed;
  }
}
