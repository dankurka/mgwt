package com.googlecode.mgwt.ui.client.widget.tabbar.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;


public class ImageHolderDefaultXHighAppearance implements TabBarImageHolder.Appearance {
  interface Resources extends ClientBundle, Images {
    Resources INSTANCE = GWT.create(Resources.class);

    @Override
    @Source("bookmarks_xhdpi.png")
    ImageResource bookmarks();
    @Override
    @Source("bookmarksSelected_xhdpi.png")
    ImageResource bookmarksSelected();
    @Override
    @Source("contacts_xhdpi.png")
    ImageResource contacts();
    @Override
    @Source("contactsSelected_xhdpi.png")
    ImageResource contactsSelected();
    @Override
    @Source("downloads_xhdpi.png")
    ImageResource downloads();
    @Override
    @Source("downloadsSelected_xhdpi.png")
    ImageResource downloadsSelected();
    @Override
    @Source("favorites_xhdpi.png")
    ImageResource favorites();
    @Override
    @Source("favoritesSelected_xhdpi.png")
    ImageResource favoritesSelected();
    @Override
    @Source("history_xhdpi.png")
    ImageResource history();
    @Override
    @Source("historySelected_xhdpi.png")
    ImageResource historySelected();
    @Override
    @Source("more_xhdpi.png")
    ImageResource more();
    @Override
    @Source("moreSelected_xhdpi.png")
    ImageResource moreSelected();
    @Override
    @Source("mostViewed_xhdpi.png")
    ImageResource mostViewed();
    @Override
    @Source("mostViewedSelected_xhdpi.png")
    ImageResource mostViewedSelected();
    @Override
    @Source("search_xhdpi.png")
    ImageResource search();
    @Override
    @Source("searchSelected_xhdpi.png")
    ImageResource searchSelected();
  }

  @Override
  public Images get() {
    return Resources.INSTANCE;
  }
}
