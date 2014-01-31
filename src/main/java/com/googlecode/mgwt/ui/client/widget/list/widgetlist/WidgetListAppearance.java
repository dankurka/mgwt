package com.googlecode.mgwt.ui.client.widget.list.widgetlist;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList.WidgetListEntry;

public interface WidgetListAppearance {
  
  public interface WidgetListCss extends CssResource {

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
