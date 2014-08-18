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
package com.googlecode.mgwt.ui.client.widget.input.radio;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface MRadioButtonAppearance {
  public interface MRadioButtonCss extends MGWTCssResource {
    @ClassName("mgwt-RadioButton-disabled")
    String disabled();
    @ClassName("mgwt-RadioButton")
    String radio();
    @ClassName("mgwt-RadioButton-label")
    String label();
    @ClassName("mgwt-RadioButton-input")
    String  input();
  }

  MRadioButtonCss css();

  UiBinder<? extends Element, MRadioButton> uiBinder();
}
