package com.googlecode.mgwt.ui.client.widget.animation;

import com.googlecode.mgwt.ui.client.widget.animation.bundle.DissolveAnimation;
import com.googlecode.mgwt.ui.client.widget.animation.bundle.FadeAnimation;
import com.googlecode.mgwt.ui.client.widget.animation.bundle.FlipAnimation;
import com.googlecode.mgwt.ui.client.widget.animation.bundle.PopAnimation;
import com.googlecode.mgwt.ui.client.widget.animation.bundle.SlideAnimation;
import com.googlecode.mgwt.ui.client.widget.animation.bundle.SlideUpAnimation;
import com.googlecode.mgwt.ui.client.widget.animation.bundle.SwapAnimation;

public final class Animations {

  public static final Animation DISSOLVE = new DissolveAnimation(false);
  public static final Animation DISSOLVE_REVERSE = new DissolveAnimation(true);

  public static final Animation FADE = new FadeAnimation(false);
  public static final Animation FADE_REVERSE = new FadeAnimation(true);

  public static final Animation FLIP = new FlipAnimation(false);
  public static final Animation FLIP_REVERSE = new FlipAnimation(true);

  public static final Animation POP = new PopAnimation(false);
  public static final Animation POP_REVERSE = new PopAnimation(true);

  public static final Animation SLIDE = new SlideAnimation(false);
  public static final Animation SLIDE_REVERSE = new SlideAnimation(true);

  public static final Animation SLIDE_UP = new SlideUpAnimation(false);
  public static final Animation SLIDE_UP_REVERSE = new SlideUpAnimation(true);

  public static final Animation SWAP = new SwapAnimation(false);
  public static final Animation SWAP_REVERSE = new SwapAnimation(true);

  private Animations() {
  }
}
