package com.googlecode.mgwt.ui.client.theme.platform.tabbar;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarAbstractAppearance;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

public class TabBarAndroidAppearance extends TabBarAbstractAppearance {

  static {
    Resources.INSTANCE.css().ensureInjected();
    Resources.INSTANCE.barCss().ensureInjected();
  }

  @UiTemplate("TabPanelAndroidAppearance.ui.xml")
  interface PanelBinder extends UiBinder<Widget, TabPanel> {
  }

  interface Resources extends ClientBundle, TabBarIcons {

    Resources INSTANCE = GWT.create(Resources.class);

    @Source({"tabbar-base.css", "tabbar-android.css"})
    TabBarCss barCss();
    @Source({"tabbar-button-base.css", "tabbar-button-android.css"})
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
