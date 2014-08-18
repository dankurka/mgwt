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
package com.googlecode.mgwt.ui.client.widget.list.widgetlist;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList.WidgetListEntry;

public interface WidgetListAppearance {

  public interface WidgetListCss extends MGWTCssResource {

    @ClassName("mgwt-WidgetList")
    String widgetList();

    @ClassName("mgwt-WidgetList-headerContainer")
    String headerContainer();

    @ClassName("mgwt-WidgetList-Entry-first")
    String widgetListEntryFirstChild();

    @ClassName("mgwt-WidgetList-Entry-last")
    String widgetListEntryLastChild();

    @ClassName("mgwt-WidgetList-round")
    String round();

    @ClassName("mgwt-WidgetList-Entry")
    String widgetListEntry();

    @ClassName("mgwt-WidgetList-Entry-selectable")
    String widgetListEntrySelectedable();
  }

  WidgetListCss css();

  UiBinder<Widget, WidgetList> uiBinder();

  UiBinder<Widget, WidgetListEntry> uiBinderEntry();
}
