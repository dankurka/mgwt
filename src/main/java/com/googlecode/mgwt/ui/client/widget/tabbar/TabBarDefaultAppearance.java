package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public class TabBarDefaultAppearance extends TabBarAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.barCss().ensureInjected();
  }

  interface Resources extends ClientBundle, TabBarIcons {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"tabbar-base.css", "tabbar-ios.css"})
    TabBarCss barCss();
    @Source({"tabbar-button-base.css", "tabbar-button-ios.css"})
    TabBarButtonCss css();
    
    @Override
    @Source("resources/bookmarks.png")
    ImageResource bookMarkImage();
    @Override
    @Source("resources/contacts.png")
    ImageResource contactsImage();
    @Override
    @Source("resources/downloads.png")
    ImageResource downloadsImage();
    @Override
    @Source("resources/favorites.png")
    ImageResource favoritesImage();
    @Override
    @Source("resources/featured.png")
    ImageResource featuredImage();
    @Override
    @Source("resources/history.png")
    ImageResource historyImage();
    @Override
    @Source("resources/more.png")
    ImageResource moreImage();
    @Override
    @Source("resources/mostrecent.png")
    ImageResource mostRecentImage();
    @Override
    @Source("resources/mostviewed.png")
    ImageResource mostViewedImage();
    @Override
    @Source("resources/search.png")
    ImageResource searchImage();
  }

  @Override
  public TabBarButtonCss css() {
    return Resources.INSTANCE.css();
  }

  @Override
  public TabBarCss barCss() {
    return Resources.INSTANCE.barCss();
  }

  @Override
  public TabBarIcons icons() {
    return Resources.INSTANCE;
  }
}
