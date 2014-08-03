package com.googlecode.mgwt.ui.client.widget.list.celllist;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellList.EntryTemplate;

public interface CellListAppearance {

  interface CellListCss extends MGWTCssResource {

    @ClassName("mgwt-List")
    public String listCss();

    @ClassName("mgwt-List-selected")
    public String selected();

    @ClassName("mgwt-List-first")
    public String first();

    @ClassName("mgwt-List-last")
    public String last();

    @ClassName("mgwt-List-Head-Element")
    public String listHeadElement();

    @ClassName("mgwt-List-canbeSelected")
    public String canbeSelected();

    @ClassName("mgwt-List-Header")
    String header();
  }

  CellListCss css();

  UiBinder<? extends Element, CellList<?>> uiBinder();

  EntryTemplate getEntryTemplate();
}
