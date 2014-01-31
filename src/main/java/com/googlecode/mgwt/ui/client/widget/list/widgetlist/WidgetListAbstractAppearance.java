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
package com.googlecode.mgwt.ui.client.widget.list.widgetlist;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList.WidgetListEntry;

public abstract class WidgetListAbstractAppearance implements WidgetListAppearance {

  @UiTemplate("WidgetListAbstractAppearance.ui.xml")
  interface Binder extends UiBinder<Widget, WidgetList> {
  }

  private static final Binder UI_BINDER = GWT.create(Binder.class);
  
  @UiTemplate("WidgetListEntryAbstractAppearance.ui.xml")
  interface BinderEntry extends UiBinder<Widget, WidgetListEntry> {
  }

  private static final BinderEntry UI_BINDER_ENTRY = GWT.create(BinderEntry.class);

  @Override
  public UiBinder<Widget, WidgetList> uiBinder() {
    return UI_BINDER;
  }

  @Override
  public UiBinder<Widget, WidgetListEntry> uiBinderEntry() {
    return UI_BINDER_ENTRY;
  }
}
