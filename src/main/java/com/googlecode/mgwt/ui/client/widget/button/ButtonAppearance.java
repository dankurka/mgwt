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

public interface ButtonAppearance extends ButtonBaseAppearance {

  interface ButtonCss extends ButtonBaseAppearance.ButtonBaseCss {
    @Override
    @ClassName("mgwt-Button")
    String button();

    @Override
    @ClassName("mgwt-Button-active")
    String active();

    @ClassName("mgwt-Button-small")
    String small();

    @ClassName("mgwt-Button-round")
    String round();

    @ClassName("mgwt-Button-important")
    String important();

    @ClassName("mgwt-Button-confirm")
    String confirm();
    
    @ClassName("mgwt-Button-disabled")
    String disabled();
  }

  @Override
  ButtonCss css();
  
  @Override
  UiBinder<Element, Button> uiBinder();
}
