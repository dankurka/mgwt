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
package com.googlecode.mgwt.ui.client.widget.header;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

public abstract class HeaderAbstractAppearance implements HeaderAppearance {

  @UiTemplate("HeaderPanelAbstractAppearance.ui.xml")
  interface BinderPanel extends UiBinder<Widget, HeaderPanel> {
  }

  private static final BinderPanel UI_BINDER_PANEL = GWT.create(BinderPanel.class);

  @UiTemplate("HeaderTitleAbstractAppearance.ui.xml")
  interface BinderTitle extends UiBinder<Element, HeaderTitle> {
  }

  private static final BinderTitle UI_BINDER_TITLE = GWT.create(BinderTitle.class);

  @Override
  public UiBinder<Widget, HeaderPanel> panelBinder() {
    return UI_BINDER_PANEL;
  }

  @Override
  public UiBinder<? extends Element, HeaderTitle> uiBinderTitle() {
    return UI_BINDER_TITLE;
  }
}
