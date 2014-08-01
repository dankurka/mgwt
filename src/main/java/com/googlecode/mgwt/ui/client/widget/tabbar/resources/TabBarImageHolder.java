package com.googlecode.mgwt.ui.client.widget.tabbar.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ImageResource;


public class TabBarImageHolder {
  private static final Appearance APPEARANCE = GWT.create(Appearance.class);

  public interface Appearance {
    public interface Images {
      ImageResource bookmarks();
      ImageResource bookmarksSelected();
      ImageResource contacts();
      ImageResource contactsSelected();
      ImageResource downloads();
      ImageResource downloadsSelected();
      ImageResource favorites();
      ImageResource favoritesSelected();
      ImageResource history();
      ImageResource historySelected();
      ImageResource more();
      ImageResource moreSelected();
      ImageResource mostViewed();
      ImageResource mostViewedSelected();
      ImageResource search();
      ImageResource searchSelected();
    }
    Images get();
  }

  public static Appearance.Images get() {
    return APPEARANCE.get();
  }
}
