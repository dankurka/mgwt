/*
 * Copyright 2014 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.input.search;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.button.ButtonBaseAppearance;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox.MSearchBoxButton;

public interface MSearchBoxAppearance extends ButtonBaseAppearance {
  interface MSearchBoxCss extends ButtonBaseCss {

    @ClassName("mgwt-SearchBox")
    public String searchBox();

    @ClassName("mgwt-SearchBox-round")
    public String round();

    @Override
    @ClassName("mgwt-SearchBox-clear")
    public String button();

    @Override
    @ClassName("mgwt-SearchBox-clear-active")
    public String active();

    @ClassName("mgwt-SearchBox-input")
    public String input();

    @ClassName("mgwt-SearchBox-icon")
    public String searchIcon();
  }

  MSearchBoxCss css();

  UiBinder<Widget, MSearchBox> uiBinderBox();

  @Override
  UiBinder<Element, MSearchBoxButton> uiBinder();
}
