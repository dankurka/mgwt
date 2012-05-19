/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;

public interface CarouselCss extends CssResource {

	@ClassName("mgwt-Carousel")
	String carousel();

	@ClassName("mgwt-Carousel-Scroller")
	String carouselScroller();

	@ClassName("mgwt-Carousel-Container")
	String carouselContainer();

	@ClassName("mgwt-Carousel-Indicator-Container")
	String indicatorContainer();

	@ClassName("mgwt-Carousel-Indicator")
	String indicator();

	@ClassName("mgwt-Carousel-Indicator-active")
	String indicatorActive();

	@ClassName("mgwt-Carousel-Holder")
	String carouselHolder();

}
