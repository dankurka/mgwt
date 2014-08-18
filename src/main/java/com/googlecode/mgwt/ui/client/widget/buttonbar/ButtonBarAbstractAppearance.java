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
package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public abstract class ButtonBarAbstractAppearance implements ButtonBarAppearance {

  @UiTemplate("ButtonBarAbstractAppearance.ui.xml")
  interface BinderBar extends UiBinder<Widget, ButtonBar> {
  }

  @UiTemplate("ButtonBarTextAbstractAppearance.ui.xml")
  interface BinderText extends UiBinder<Element, ButtonBarText> {
  }

  private static final BinderBar UI_BINDER_BAR = GWT.create(BinderBar.class);

  private static final BinderText UI_BINDER_TEXT = GWT.create(BinderText.class);

  @Override
  public UiBinder<? extends Widget, ButtonBar> barBinder() {
    return UI_BINDER_BAR;
  }

  @Override
  public UiBinder<? extends Element, ButtonBarText> barText() {
    return UI_BINDER_TEXT;
  }
}
