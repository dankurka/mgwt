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

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList.EntryTemplate;

public interface CellListAppearance {

  interface CellListCss extends MGWTCssResource {

    @ClassName("mgwt-List")
    public String listCss();

    @ClassName("mgwt-List-entry")
    public String entry();

    @ClassName("mgwt-List-entry-selected")
    public String selected();

    @ClassName("mgwt-List-entry-first")
    public String first();

    @ClassName("mgwt-List-entry-last")
    public String last();

    @ClassName("mgwt-List-entry-canbeSelected")
    public String canbeSelected();

    @ClassName("mgwt-List-Head-Element")
    public String listHeadElement();

    @ClassName("mgwt-List-Header")
    String header();
  }

  CellListCss css();

  UiBinder<? extends Element, CellList<?>> uiBinder();

  EntryTemplate getEntryTemplate();
}
