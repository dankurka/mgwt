package com.googlecode.mgwt.ui.client.widget.list.celllist;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

public class GroupingCellListDefaultAppearance extends GroupingCellListAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
  }

  interface Resources extends ClientBundle {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"celllist.css"})
    CellListCss css();
    
    @Source({"grouping-celllist.css"})
    GroupingListCss groupCss();
    
    @Source("arrow.png")
    DataResource listArrow();
  }

  @Override
  public CellListCss css() {
    return Resources.INSTANCE.css();
  }

  @Override
  public GroupingListCss groupCss() {
    return Resources.INSTANCE.groupCss();
  }
}
