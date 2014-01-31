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
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;

import com.googlecode.mgwt.ui.client.widget.list.celllist.GroupingCellList.HeaderTemplate;

public abstract class GroupingCellListAbstractAppearance extends CellListAbstractAppearance
    implements GroupingCellListAppearance {

  interface Template extends SafeHtmlTemplates, HeaderTemplate {
    @SafeHtmlTemplates.Template("<li class=\"{1}\" >{0}</li>")
    SafeHtml li(SafeHtml cellContents, String classes);

  }

  /**
   * the li template instance
   */
  private static final Template LI_TEMPLATE = GWT.create(Template.class);

  @Override
  public HeaderTemplate getHeaderTemplate() {
    return LI_TEMPLATE;
  }
}
