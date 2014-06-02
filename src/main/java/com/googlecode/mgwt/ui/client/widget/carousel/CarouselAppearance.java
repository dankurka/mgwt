package com.googlecode.mgwt.ui.client.widget.carousel;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface CarouselAppearance {
  public interface CarouselCss extends MGWTCssResource {

    @ClassName("mgwt-Carousel")
    String carousel();

    @ClassName("mgwt-Carousel-Scroller")
    String carouselScroller();

    @ClassName("mgwt-Carousel-Container")
    String carouselContainer();

    @ClassName("mgwt-Carousel-Indicator-Container")
    String indicatorContainer();

    @ClassName("mgwt-Carousel-Indicator-main")
    String indicatorMain();

    @ClassName("mgwt-Carousel-Indicator")
    String indicator();

    @ClassName("mgwt-Carousel-Indicator-active")
    String indicatorActive();

    @ClassName("mgwt-Carousel-Holder")
    String carouselHolder();
  }

  CarouselCss css();
}
