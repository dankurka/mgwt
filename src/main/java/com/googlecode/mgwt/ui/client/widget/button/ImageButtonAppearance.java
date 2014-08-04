/*
 * Copyright 2013 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.button;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;



public interface ImageButtonAppearance extends ButtonBaseAppearance {

  interface ImageButtonCss extends ButtonBaseCss {
    @Override
    @ClassName("mgwt-ImageButton")
    String button();

    @Override
    @ClassName("mgwt-ImageButton-active")
    String active();

    @ClassName("mgwt-ImageButton-disabled")
    String disabled();

    @ClassName("mgwt-ImageButton-image")
    String image();

    @ClassName("mgwt-ImageButton-text")
    String text();

    @ClassName("mgwt-ImageButton-small")
    String small();

    String ICON_BACKGROUND_COLOR();

    String ICON_BACKGROUND_COLOR_HIGHLIGHT();
  }

  ImageButtonCss css();

  @Override
  UiBinder<Element, ImageButton> uiBinder();
}
