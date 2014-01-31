/*
 * Copyright 2010 Daniel Kurka
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
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A list that can contain widgets
 * 
 * This class renders widgets into a list. The same thing is done by {@link CellList} much more
 * efficient.
 * 
 * <b>Note:</b> Normally you should be using {@link CellList}. Only if you really need Widgets
 * inside the list (which won't be the case most of the time) you should be using WidgetList.
 * 
 * The reference for styling can be found in CellList as well.
 * 
 * 
 * @author Daniel Kurka
 */
public class WidgetList extends Composite implements HasWidgets {
  
  public static class WidgetListEntry extends Composite implements AcceptsOneWidget {

    @UiField
    protected Panel container;
    // UiBinder needs this field
    private WidgetListAppearance appearance;

    public WidgetListEntry(WidgetListAppearance appearance) {
      this.appearance = appearance;
      initWidget(this.appearance.uiBinderEntry().createAndBindUi(this));
    }

    @Override
    public void setWidget(IsWidget w) {
      container.add(w);
    }
  }
  

  private static final WidgetListAppearance DEFAULT_APPEARANCE = GWT.create(WidgetListAppearance.class);
  
  private int childCount;
  private List<WidgetListEntry> children = new ArrayList<WidgetListEntry>();

  @UiField
  protected Panel container;
  
  @UiField
  protected Panel headerContainer;

  private Map<Widget, WidgetListEntry> map;

  private WidgetListAppearance appearance;

  private Widget header;

  public WidgetList() {
    this(DEFAULT_APPEARANCE);
  }

  public WidgetList(WidgetListAppearance appearance) {
    this.appearance = appearance;
    initWidget(this.appearance.uiBinder().createAndBindUi(this));
    map = new HashMap<Widget, WidgetListEntry>();
  }

  @Override
  public void add(Widget w) {

    WidgetListEntry widgetListEntry = new WidgetListEntry(this.appearance);
    widgetListEntry.setWidget(w);
    if (childCount == 0) {
      widgetListEntry.addStyleName(this.appearance.css().widgetListEntryFirstChild());
    }
    map.put(w, widgetListEntry);
    container.add(widgetListEntry);
    children.add(widgetListEntry);

    if (childCount > 0) {
      children.get(childCount - 1).removeStyleName(this.appearance.css().widgetListEntryLastChild());
    }
    widgetListEntry.addStyleName(this.appearance.css().widgetListEntryLastChild());

    childCount++;

  }

  @Override
  public void clear() {
    container.clear();
    map.clear();
    children.clear();
    childCount = 0;
    if(header!= null) {
      add(header);
    }
  }

  @Override
  public Iterator<Widget> iterator() {
    return map.keySet().iterator();
  }

  @Override
  public boolean remove(Widget w) {
    WidgetListEntry entry = map.remove(w);
    if (entry == null)
      return false;
    // did we remove the last child?
    if (children.get(childCount - 1) == entry) {
      // are there others in the list?
      if (childCount - 2 >= 0) {
        children.get(childCount - 2).addStyleName(this.appearance.css().widgetListEntryLastChild());
      }
    }

    // did we remove the first child
    if (children.get(0) == entry) {
      if (children.size() > 1) {
        children.get(1).addStyleName(this.appearance.css().widgetListEntryFirstChild());
      }
    }
    childCount--;
    children.remove(entry);
    return container.remove(entry);
  }

  /**
   * Should the list be displayed with rounded corners
   * 
   * @param round true to display with rounded corners
   */
  public void setRound(boolean round) {
    if (round) {
      addStyleName(this.appearance.css().round());
    } else {
      removeStyleName(this.appearance.css().round());
    }
  }

  public int getWidgetCount() {
    return childCount;
  }

  public void setSelectAble(int index, boolean group) {
    if (group) {
      children.get(index).addStyleName(this.appearance.css().widgetListEntrySelectedable());
    } else {
      children.get(index).removeStyleName(this.appearance.css().widgetListEntrySelectedable());
    }
  }

  public void setHeader(Widget header) {
    headerContainer.clear();
    headerContainer.add(header);
  }
}
