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
package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;

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

  private static class WidgetListEntry extends Composite implements HasWidgets {
    private static class LIFlowPanel extends ComplexPanel {

      public LIFlowPanel() {
        setElement(Document.get().createLIElement());

      }

      @Override
      public void add(Widget w) {
        add(w, getElement().<Element>cast());
      }
    }

    private Panel container;

    public WidgetListEntry(ListCss css) {
      container = new LIFlowPanel();
      initWidget(container);

    }

    @Override
    public void add(Widget w) {
      container.add(w);

    }

    @Override
    public void clear() {
      container.clear();

    }

    @Override
    public Iterator<Widget> iterator() {
      return container.iterator();
    }

    @Override
    public boolean remove(Widget w) {
      return container.remove(w);
    }
  }

  private static class ULFlowPanel extends ComplexPanel {

    public ULFlowPanel() {
      setElement(Document.get().createULElement());
    }

    @Override
    public void add(Widget w) {
      add(w, getElement().<Element>cast());
    }
  }

  private int childCount;
  private List<WidgetListEntry> children = new ArrayList<WidgetListEntry>();

  private Panel container;
  private Map<Widget, WidgetListEntry> map;
  protected final ListCss css;

  /**
   * Construct a widget list using the default css
   */
  public WidgetList() {
    this(MGWTStyle.getTheme().getMGWTClientBundle().getListCss());
  }

  /**
   * Construct a widget list using a specific css
   * 
   * @param css the css to use
   */
  public WidgetList(ListCss css) {
    this.css = css;
    css.ensureInjected();
    container = new ULFlowPanel();
    initWidget(container);

    setStylePrimaryName(css.listCss());

    map = new HashMap<Widget, WidgetListEntry>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
   */
  /** {@inheritDoc} */
  @Override
  public void add(Widget w) {

    WidgetListEntry widgetListEntry = new WidgetListEntry(css);
    widgetListEntry.add(w);
    if (childCount == 0) {
      widgetListEntry.addStyleName(css.first());
    }
    map.put(w, widgetListEntry);
    container.add(widgetListEntry);
    children.add(widgetListEntry);

    if (childCount > 0) {
      children.get(childCount - 1).removeStyleName(css.last());
    }
    widgetListEntry.addStyleName(css.last());

    childCount++;

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.HasWidgets#clear()
   */
  /** {@inheritDoc} */
  @Override
  public void clear() {
    container.clear();
    map.clear();
    children.clear();
    childCount = 0;

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
   */
  /** {@inheritDoc} */
  @Override
  public Iterator<Widget> iterator() {
    return map.keySet().iterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
   */
  /** {@inheritDoc} */
  @Override
  public boolean remove(Widget w) {
    WidgetListEntry entry = map.remove(w);
    if (entry == null)
      return false;
    // did we remove the last child?
    if (children.get(childCount - 1) == entry) {
      // are there others in the list?
      if (childCount - 2 >= 0) {
        children.get(childCount - 2).addStyleName(css.last());
      }
    }

    // did we remove the first child
    if (children.get(0) == entry) {
      if (children.size() > 1) {
        children.get(1).addStyleName(css.first());
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
      addStyleName(css.round());
    } else {
      removeStyleName(css.round());
    }
  }

  public int getWidgetCount() {
    return childCount;
  }

  public void setGroup(int index, boolean group) {
    if (group) {
      children.get(index).addStyleName(css.group());
    } else {
      children.get(index).removeStyleName(css.group());
    }

  }

}
