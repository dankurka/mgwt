package com.googlecode.mgwt.ui.client.widget.list.celllist;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.list.celllist.GroupingCellList.HeaderTemplate;

public interface GroupingCellListAppearance extends CellListAppearance {
  public interface GroupingListCss extends MGWTCssResource {

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
