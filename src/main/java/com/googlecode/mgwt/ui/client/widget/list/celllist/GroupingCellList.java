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
package com.googlecode.mgwt.ui.client.widget.list.celllist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiFactory;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellListAppearance.CellListCss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A grouping cell list renders a number of groups with their children
 *
 * @author Daniel Kurka
 *
 * @param <G> the type of the model for the header
 * @param <T> the type if the model for the content
 */
public class GroupingCellList<G, T> extends CellList<T> implements HasSelectionHandlers<T> {

  public static final GroupingCellListAppearance DEFAULT_APPEARANCE = GWT.create(GroupingCellListAppearance.class);

  private final Cell<G> header;
  private final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
  private final Map<Integer, T> modelMap = new HashMap<Integer, T>();

  private GroupingCellListAppearance groupAppearance;

  private HeaderTemplate headerTemplate;

  /**
   * The cellgroup interface
   *
   * @author Daniel Kurka
   *
   * @param <G> the type of the model for the header
   * @param <T> the type if the model for the content
   */
  public interface CellGroup<G, T> {
    /**
     * get the name of the group
     *
     * @return the name of the group
     */
    public String getKey();

    /**
     * get the group header model
     *
     * @return the group header model
     */
    public G getGroup();

    /**
     * get the group content models
     *
     * @return the group content models
     */
    public List<T> getMember();
  }

  /**
   * The standard implementation of {@link GroupingCellList.CellGroup}
   *
   * @author Daniel Kurka
   *
   * @param <G> the type of the model for the header
   * @param <T> the type if the model for the content
   */
  public static class StandardCellGroup<G, T> implements CellGroup<G, T> {

    protected final String key;
    protected final G group;
    protected final List<T> member;

    /**
     * Construct a {@link GroupingCellList.StandardCellGroup}
     *
     * @param key the key to use
     * @param group the group model to use
     * @param member the list of content models for this group
     */
    public StandardCellGroup(String key, G group, List<T> member) {
      this.key = key;
      this.group = group;
      this.member = member;
    }

    @Override
    public String getKey() {
      return key;
    }

    @Override
    public G getGroup() {
      return group;
    }

    @Override
    public List<T> getMember() {
      return member;
    }

  }

  interface HeaderTemplate {
    SafeHtml li(SafeHtml cellContents, String classes);
  }



  /**
   * Construct a cell list with a given cell for content and for the header
   *
   * @param cell the cell for content
   * @param header the cell for the headers
   */
  public GroupingCellList(Cell<T> cell, Cell<G> header) {
    this(cell, header, DEFAULT_APPEARANCE);
  }

  /**
   * Construct a cell list with a given cell for content, for the header and a given css
   *
   * @param cell the cell for content
   * @param header the cell for the headers
   * @param css the css to use
   */
  public GroupingCellList(Cell<T> cell, Cell<G> header, GroupingCellListAppearance groupAppearance) {
    super(cell, groupAppearance);
    this.groupAppearance = groupAppearance;
    this.header = header;
    headerTemplate = this.groupAppearance.getHeaderTemplate();
  }

  /**
   * render a given set of models
   *
   * @param groups the model to render
   */
  public void renderGroup(List<CellGroup<G, T>> groups) {
    SafeHtmlBuilder sb = new SafeHtmlBuilder();

    int count = 0;

    map.clear();
    modelMap.clear();

    int groupCounter = 0;
    int renderedGroups = 0;

    for (CellGroup<G, T> cellGroup : groups) {

      if (cellGroup.getMember().isEmpty()) {
        groupCounter++;
        continue;
      }

      // render header of group
      SafeHtmlBuilder headerBuilder = new SafeHtmlBuilder();
      header.render(headerBuilder, cellGroup.getGroup());

      sb.append(headerTemplate.li(headerBuilder.toSafeHtml(), this.groupAppearance.css().listHeadElement()));

      // render members of group
      List<T> models = cellGroup.getMember();
      for (T model : models) {
        SafeHtmlBuilder cellBuilder = new SafeHtmlBuilder();

        String clazz = this.groupAppearance.css().entry() + " ";
        if (cell.canBeSelected(model)) {
          clazz += this.groupAppearance.css().canbeSelected() + " ";
        }

        cell.render(cellBuilder, model);

        sb.append(entryTemplate.li(count, clazz, cellBuilder.toSafeHtml()));

        modelMap.put(count, model);

        count++;
      }

      map.put(renderedGroups, groupCounter);

      renderedGroups++;
      groupCounter++;

    }

    final String html = sb.toSafeHtml().asString();

    getElement().setInnerHTML(html);

    if (count > 0) {
      String innerHTML = getElement().getInnerHTML();
      if ("".equals(innerHTML.trim())) {
        fixBug(html);
      }
    }
  }

  /**
   * get the css selector for header elements
   *
   * @return the css selector for header elements
   */
  public String getHeaderSelector() {
    return "li." + this.groupAppearance.css().listHeadElement();
  }

  /**
   * get the css used by this {@link GroupingCellList}
   *
   * @return the css used by this {@link GroupingCellList}
   */
  public CellListCss getListCss() {
    return this.groupAppearance.css();
  }

  /**
   * get the mapping of index to content
   *
   * @return the mapping of index to content
   */
  public Map<Integer, Integer> getMapping() {
    return map;
  }

  /**
   * render a header and return the value as html
   *
   * @param group the header to render
   * @return the string value
   */
  public String renderGroupHeader(G group) {
    SafeHtmlBuilder headerBuilder = new SafeHtmlBuilder();
    header.render(headerBuilder, group);
    return headerBuilder.toSafeHtml().asString();
  }

  @Override
  protected void fireSelectionAtIndex(int index, Element targetElement) {
    T t = modelMap.get(index);
    if (t != null) {
      SelectionEvent.fire(this, t);
    }
  }

  @Override
  @UiFactory
  protected CellListAppearance getAppearance() {
	  return super.getAppearance();
  }

  @Override
  public HandlerRegistration addSelectionHandler(SelectionHandler<T> handler) {
    return addHandler(handler, SelectionEvent.getType());
  }
}
