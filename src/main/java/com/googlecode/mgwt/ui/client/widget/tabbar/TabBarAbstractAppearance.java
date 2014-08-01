/*
 * Copyright 2014 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel.TabBar;

public abstract class TabBarAbstractAppearance implements TabBarAppearance {

  @UiTemplate("TabPanelAbstractAppearance.ui.xml")
  interface PanelBinder extends UiBinder<Widget, TabPanel> {}

  @UiTemplate("TabBarAbstractAppearance.ui.xml")
  interface BarBinder extends UiBinder<Widget, TabPanel.TabBar> {}

  @UiTemplate("TabBarButtonBaseAppearance.ui.xml")
  interface ButtonBinder extends UiBinder<Element, TabBarButtonBase> {}

  private static final PanelBinder UI_BINDER_PANEL = GWT.create(PanelBinder.class);

  private static final ButtonBinder UI_BINDER = GWT.create(ButtonBinder.class);

  private static final BarBinder UI_BINDER_BAR = GWT.create(BarBinder.class);

  @Override
  public UiBinder<? extends Element, TabBarButtonBase> uiBinder() {
    return UI_BINDER;
  }

  @Override
  public UiBinder<Widget, TabPanel> panelBinder() {
    return UI_BINDER_PANEL;
  }

  @Override
  public UiBinder<Widget, TabBar> barBinder() {
    return UI_BINDER_BAR;
  }
}
