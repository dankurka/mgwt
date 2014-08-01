package com.googlecode.mgwt.ui.client.widget.tabbar.resources;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public class ImageHolderDefaultAppearance implements TabBarImageHolder.Appearance {
  interface Resources extends ClientBundle, Images {
    Resources INSTANCE = GWT.create(Resources.class);

    @Override
    @Source("bookmarks_mdpi.png")
    ImageResource bookmarks();
    @Override
    @Source("bookmarksSelected_mdpi.png")
    ImageResource bookmarksSelected();
    @Override
    @Source("contacts_mdpi.png")
    ImageResource contacts();
    @Override
    @Source("contactsSelected_mdpi.png")
    ImageResource contactsSelected();
    @Override
    @Source("downloads_mdpi.png")
    ImageResource downloads();
    @Override
    @Source("downloadsSelected_mdpi.png")
    ImageResource downloadsSelected();
    @Override
    @Source("favorites_mdpi.png")
    ImageResource favorites();
    @Override
    @Source("favoritesSelected_mdpi.png")
    ImageResource favoritesSelected();
    @Override
    @Source("history_mdpi.png")
    ImageResource history();
    @Override
    @Source("historySelected_mdpi.png")
    ImageResource historySelected();
    @Override
    @Source("more_mdpi.png")
    ImageResource more();
    @Override
    @Source("moreSelected_mdpi.png")
    ImageResource moreSelected();
    @Override
    @Source("mostViewed_mdpi.png")
    ImageResource mostViewed();
    @Override
    @Source("mostViewedSelected_mdpi.png")
    ImageResource mostViewedSelected();
    @Override
    @Source("search_mdpi.png")
    ImageResource search();
    @Override
    @Source("searchSelected_mdpi.png")
    ImageResource searchSelected();
  }

  @Override
  public Images get() {
    return Resources.INSTANCE;
  }
}
