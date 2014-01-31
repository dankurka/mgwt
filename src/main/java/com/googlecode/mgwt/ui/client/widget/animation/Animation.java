package com.googlecode.mgwt.ui.client.widget.animation;

public interface Animation {
  public interface AnimationCss {
    String in();

    String out();

    String reverse();
  }

  AnimationCss css();

  String key();

  boolean isReversed();
}
