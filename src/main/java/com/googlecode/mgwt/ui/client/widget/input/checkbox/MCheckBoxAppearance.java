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
package com.googlecode.mgwt.ui.client.widget.input.checkbox;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface MCheckBoxAppearance {
  public interface CheckBoxCss extends MGWTCssResource {

    @ClassName("mgwt-CheckBox")
    public String checkBox();

    @ClassName("mgwt-CheckBox-middle")
    public String middle();

    @ClassName("mgwt-CheckBox-middle-content")
    public String content();

    @ClassName("mgwt-CheckBox-on")
    public String on();

    @ClassName("mgwt-CheckBox-off")
    public String off();

    @ClassName("mgwt-CheckBox-important")
    public String important();

    @ClassName("mgwt-CheckBox-checked")
    public String checked();

    @ClassName("mgwt-CheckBox-notchecked")
    public String notChecked();

    public int DRAG_DEADZONE();

    public int CONTAINER_MIN_ON();

    public int CONTAINER_MAX_ON();

    public int CONTAINER_OFFSET_ON();

    public int CONTAINER_MIN_OFF();

    public int CONTAINER_MAX_OFF();

    public int CONTAINER_OFFSET_OFF();
  }

  CheckBoxCss css();

  UiBinder<? extends Element, MCheckBox> uiBinder();
}
