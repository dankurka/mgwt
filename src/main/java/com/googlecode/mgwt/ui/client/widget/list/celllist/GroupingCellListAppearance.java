package com.googlecode.mgwt.ui.client.widget.list.celllist;

import com.google.gwt.resources.client.CssResource;

import com.googlecode.mgwt.ui.client.widget.list.celllist.GroupingCellList.HeaderTemplate;

public interface GroupingCellListAppearance extends CellListAppearance {
  public interface GroupingListCss extends CssResource {

    @ClassName("mgwt-GroupingList")
    public String groupingHeaderList();

    @ClassName("mgwt-GroupingList-Moving-Header")
    public String movingHeader();

    @ClassName("mgwt-GroupingList-Selection-Bar")
    public String selectionBar();

    @ClassName("mgwt-GroupingList-Selection-Bar-active")
    public String selectionBarActive();
  }

  GroupingListCss groupCss();
  
  HeaderTemplate getHeaderTemplate();
}
