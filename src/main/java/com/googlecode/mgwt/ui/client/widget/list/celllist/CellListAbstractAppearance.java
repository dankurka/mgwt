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
package com.googlecode.mgwt.ui.client.widget.list.celllist;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;

import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList.EntryTemplate;

public abstract class CellListAbstractAppearance implements CellListAppearance {

  /**
   * the li template instance
   */
  private static final Template LI_TEMPLATE = GWT.create(Template.class);
  
  @UiTemplate("CellListBaseAppearance.ui.xml")
  interface Binder extends UiBinder<Element, CellList<?>> {
  }

  private static final Binder UI_BINDER = GWT.create(Binder.class);

  @Override
  public UiBinder<? extends Element, CellList<?>> uiBinder() {
    return UI_BINDER;
  }
  
  
  /**
   * Standard li template
   * 
   * @author Daniel Kurka
   * 
   */
  public interface Template extends EntryTemplate, SafeHtmlTemplates {
    /**
     * get the template
     * 
     * @param idx
     * @param classes
     * @param cellContents
     * @return the safe html with values
     */
    @Override
    @SafeHtmlTemplates.Template("<li __idx=\"{0}\" class=\"{1}\">{2}</li>")
    SafeHtml li(int idx, String classes, SafeHtml cellContents);
  }
  
  @Override
  public EntryTemplate getEntryTemplate() {
    return LI_TEMPLATE;
  }
}
