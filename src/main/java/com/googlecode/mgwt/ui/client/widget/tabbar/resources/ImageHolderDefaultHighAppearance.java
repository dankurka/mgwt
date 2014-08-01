package com.googlecode.mgwt.ui.client.widget.tabbar.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public class ImageHolderDefaultHighAppearance implements TabBarImageHolder.Appearance {
  interface Resources extends ClientBundle, Images {
    Resources INSTANCE = GWT.create(Resources.class);

    @Override
    @Source("bookmarks_hdpi.png")
    ImageResource bookmarks();
    @Override
    @Source("bookmarksSelected_hdpi.png")
    ImageResource bookmarksSelected();
    @Override
    @Source("contacts_hdpi.png")
    ImageResource contacts();
    @Override
    @Source("contactsSelected_hdpi.png")
    ImageResource contactsSelected();
    @Override
    @Source("downloads_hdpi.png")
    ImageResource downloads();
    @Override
    @Source("downloadsSelected_hdpi.png")
    ImageResource downloadsSelected();
    @Override
    @Source("favorites_hdpi.png")
    ImageResource favorites();
    @Override
    @Source("favoritesSelected_hdpi.png")
    ImageResource favoritesSelected();
    @Override
    @Source("history_hdpi.png")
    ImageResource history();
    @Override
    @Source("historySelected_hdpi.png")
    ImageResource historySelected();
    @Override
    @Source("more_hdpi.png")
    ImageResource more();
    @Override
    @Source("moreSelected_hdpi.png")
    ImageResource moreSelected();
    @Override
    @Source("mostViewed_hdpi.png")
    ImageResource mostViewed();
    @Override
    @Source("mostViewedSelected_hdpi.png")
    ImageResource mostViewedSelected();
    @Override
    @Source("search_hdpi.png")
    ImageResource search();
    @Override
    @Source("searchSelected_hdpi.png")
    ImageResource searchSelected();
  }

  @Override
  public Images get() {
    return Resources.INSTANCE;
  }
}