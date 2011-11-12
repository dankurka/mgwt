/*
 * Copyright 2011 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.TextResource;
import com.googlecode.mgwt.ui.client.theme.MGWTClientBundle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ActionButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowDownButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowLeftButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowRightButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowUpButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.BookmarkButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.CameraButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ComposeButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.FastForwardButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.InfoButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.LocateButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.NewIconButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.NextSlideButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.OrganizeButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.PauseButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.PlayButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.PlusButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.PreviousSlideButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.RefreshButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ReplyButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.RewindButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.SearchButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.StopButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.TrashButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.BookmarkTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.ContactsTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.DownloadsTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.FavoritesTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.FeaturedTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.HistoryTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.MoreTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.MostRecentTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.MostViewedTabBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.SearchTabBarButtonCss;

/**
 * <p>
 * MGWTClientBundle interface.
 * </p>
 * 
 * @author kurt
 * @version $Id: $
 */
public interface MGWTClientBundleBaseThemeNonRetina extends ClientBundle, MGWTClientBundle {

	/**
	 * <p>
	 * getProgressBarCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ProgressBarCss}
	 *         object.
	 */
	@Source("css/progressbar.css")
	ProgressBarCss getProgressBarCss();

	// This is a very nasty workaround because GWT CssResource does not support
	// @media correctly!
	/**
	 * <p>
	 * getUtilCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.UtilCss}
	 *         object.
	 */
	@Source("css/util_fake.css")
	UtilCss getUtilCss();

	// This is a very nasty workaround because GWT CssResource does not support
	// @media correctly!
	/**
	 * <p>
	 * utilTextResource
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.TextResource} object.
	 */
	@Source("css/util.css")
	TextResource utilTextResource();

	/**
	 * <p>
	 * getProgressIndicatorCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.ProgressIndicatorCss}
	 *         object.
	 */
	@Source("css/progressindicator.css")
	ProgressIndicatorCss getProgressIndicatorCss();

	/**
	 * <p>
	 * getHeaderCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.HeaderCss}
	 *         object.
	 */
	@Source("css/header.css")
	HeaderCss getHeaderCss();

	/**
	 * <p>
	 * getSliderCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.SliderCss}
	 *         object.
	 */
	@Source("css/slider.css")
	SliderCss getSliderCss();

	/**
	 * <p>
	 * getListCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ListCss}
	 *         object.
	 */
	@Source("css/list.css")
	ListCss getListCss();

	/**
	 * <p>
	 * listArrow
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/list/arrow.png")
	DataResource listArrow();

	/**
	 * <p>
	 * listArrowTouched
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/list/arrow_touched.png")
	DataResource listArrowTouched();

	/**
	 * <p>
	 * getSearchBoxCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.MSearchBoxCss}
	 *         object.
	 */
	@Source("css/searchbox.css")
	MSearchBoxCss getSearchBoxCss();

	/**
	 * <p>
	 * searchSearchImage
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/search/search.png")
	DataResource searchSearchImage();

	/**
	 * <p>
	 * searchClearImage
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/search/clear.png")
	DataResource searchClearImage();

	/**
	 * <p>
	 * searchClearTouchedImage
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/search/clear_touched.png")
	DataResource searchClearTouchedImage();

	/**
	 * <p>
	 * getCheckBoxCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss}
	 *         object.
	 */
	@Source("css/checkbox.css")
	CheckBoxCss getCheckBoxCss();

	/**
	 * <p>
	 * getButtonCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ButtonCss}
	 *         object.
	 */
	@Source("css/buttons.css")
	ButtonCss getButtonCss();

	/**
	 * <p>
	 * getScrollPanelCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss}
	 *         object.
	 */
	@Source("css/scrollpanel.css")
	ScrollPanelCss getScrollPanelCss();

	/**
	 * <p>
	 * getButtonBarCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss}
	 *         object.
	 */
	@Source("css/buttonbar.css")
	ButtonBarCss getButtonBarCss();

	/**
	 * <p>
	 * getDialogCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.DialogCss}
	 *         object.
	 */
	@Source("css/dialog.css")
	DialogCss getDialogCss();

	/**
	 * <p>
	 * getMainCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.MainCss}
	 *         object.
	 */
	@Source("css/main.css")
	MainCss getMainCss();

	/**
	 * <p>
	 * getInputCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.InputCss}
	 *         object.
	 */
	@Source("css/input.css")
	InputCss getInputCss();

	/**
	 * <p>
	 * inputCheckImage
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/input/check.png")
	DataResource inputCheckImage();

	/**
	 * <p>
	 * getPanelCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.PanelCss}
	 *         object.
	 */
	@Source("css/panel.css")
	PanelCss getPanelCss();

	/**
	 * <p>
	 * getLayoutCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.LayoutCss}
	 *         object.
	 */
	@Source("css/layout.css")
	LayoutCss getLayoutCss();

	/**
	 * <p>
	 * getPullToRefreshCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss}
	 *         object.
	 */
	@Source("css/pulltorefresh.css")
	PullToRefreshCss getPullToRefreshCss();

	@Source("resources/toolbar/action.png")
	DataResource toolbarActionImage();

	@Source("resources/toolbar/arrowdown.png")
	DataResource toolbarArrowDownImage();

	@Source("resources/toolbar/arrowleft.png")
	DataResource toolbarArrowLeftImage();

	@Source("resources/toolbar/arrowright.png")
	DataResource toolbarArrowRightImage();

	@Source("resources/toolbar/arrowup.png")
	DataResource toolbarArrowUpImage();

	@Source("resources/toolbar/bookmarks.png")
	DataResource toolbarBookmarksImage();

	@Source("resources/toolbar/camera.png")
	DataResource toolbarCameraImage();

	@Source("resources/toolbar/compose.png")
	DataResource toolbarComposeImage();

	@Source("resources/toolbar/fastforward.png")
	DataResource toolbarFastForwardImage();

	@Source("resources/toolbar/info.png")
	DataResource toolbarInfoImage();

	@Source("resources/toolbar/locate.png")
	DataResource toolbarLocateImage();

	@Source("resources/toolbar/new.png")
	DataResource toolbarNewImage();

	@Source("resources/toolbar/nextslide.png")
	DataResource toolbarNextSlideImage();

	@Source("resources/toolbar/organize.png")
	DataResource toolbarOrganizeImage();

	@Source("resources/toolbar/pause.png")
	DataResource toolbarPauseImage();

	@Source("resources/toolbar/play.png")
	DataResource toolbarPlayImage();

	@Source("resources/toolbar/plus.png")
	DataResource toolbarPlusImage();

	@Source("resources/toolbar/pressed.png")
	DataResource toolbarPressImage();

	@Source("resources/toolbar/previousslide.png")
	DataResource toolbarPreviousSlideImage();

	@Source("resources/toolbar/refresh.png")
	DataResource toolbarRefreshImage();

	@Source("resources/toolbar/reply.png")
	DataResource toolbarReplyImage();

	@Source("resources/toolbar/rewind.png")
	DataResource toolbarRewindImage();

	@Source("resources/toolbar/search.png")
	DataResource toolbarSearchImage();

	@Source("resources/toolbar/stop.png")
	DataResource toolbarStopImage();

	@Source("resources/toolbar/trash.png")
	DataResource toolbarTrashImage();

	// tabbar
	/**
	 * <p>
	 * getTabBarCss
	 * </p>
	 * 
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.TabBarCss}
	 *         object.
	 */
	@Source("css/tabbar.css")
	TabBarCss getTabBarCss();

	/**
	 * <p>
	 * getBookmarkTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.BookmarkTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/bookmark_button.css" })
	BookmarkTabBarButtonCss getBookmarkTabBarButtonCss();

	/**
	 * <p>
	 * getContactsTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.ContactsTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/contact_button.css" })
	ContactsTabBarButtonCss getContactsTabBarButtonCss();

	/**
	 * <p>
	 * getDownloadsTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.DownloadsTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/download_button.css" })
	DownloadsTabBarButtonCss getDownloadsTabBarButtonCss();

	/**
	 * <p>
	 * getFavoritesTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.FavoritesTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/favorites_button.css" })
	FavoritesTabBarButtonCss getFavoritesTabBarButtonCss();

	/**
	 * <p>
	 * getFeaturedTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.FeaturedTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/featured_button.css" })
	FeaturedTabBarButtonCss getFeaturedTabBarButtonCss();

	/**
	 * <p>
	 * getHistoryTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.HistoryTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/history_button.css" })
	HistoryTabBarButtonCss getHistoryTabBarButtonCss();

	/**
	 * <p>
	 * getMoreTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.MoreTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/more_button.css" })
	MoreTabBarButtonCss getMoreTabBarButtonCss();

	/**
	 * <p>
	 * getMostRecentTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.MostRecentTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/most_recent_button.css" })
	MostRecentTabBarButtonCss getMostRecentTabBarButtonCss();

	/**
	 * <p>
	 * getMostViewedTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.MostViewedTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/most_viewed_button.css" })
	MostViewedTabBarButtonCss getMostViewedTabBarButtonCss();

	/**
	 * <p>
	 * getSearchTabBarButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.SearchTabBarButtonCss}
	 *         object.
	 */
	@Source({ "tabbar/css/button_base.css", "tabbar/css/search_button.css" })
	SearchTabBarButtonCss getSearchTabBarButtonCss();

	@Source("resources/tabbar/bookmarks.png")
	DataResource tabBarBookMarkImage();

	@Source("resources/tabbar/contacts.png")
	DataResource tabBarContactsImage();

	@Source("resources/tabbar/downloads.png")
	DataResource tabBarDownloadsImage();

	@Source("resources/tabbar/favorites.png")
	DataResource tabBarFavoritesImage();

	@Source("resources/tabbar/featured.png")
	DataResource tabBarFeaturedImage();

	@Source("resources/tabbar/history.png")
	DataResource tabBarHistoryImage();

	@Source("resources/tabbar/more.png")
	DataResource tabBarMoreImage();

	@Source("resources/tabbar/mostrecent.png")
	DataResource tabBarMostRecentImage();

	@Source("resources/tabbar/mostviewed.png")
	DataResource tabBarMostViewedImage();

	@Source("resources/tabbar/search.png")
	DataResource tabBarSearchImage();

	/**
	 * <p>
	 * spinnerImage
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/spinner.png")
	DataResource spinnerImage();

	@Source("resources/spinner_white.png")
	DataResource spinnerWhiteImage();

	/**
	 * <p>
	 * errorImage
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/error.png")
	DataResource errorImage();

	/**
	 * <p>
	 * android_check_checked
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/input/check_android_checked.png")
	DataResource android_check_checked();

	/**
	 * <p>
	 * android_check_not_checked
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.resources.client.DataResource} object.
	 */
	@Source("resources/input/check_android_not_checked.png")
	DataResource android_check_not_checked();

	// buttonbar buttons
	/**
	 * <p>
	 * getActionButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.ActionButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/action_button.css" })
	public ActionButtonCss getActionButtonCss();

	/**
	 * <p>
	 * getLeftButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowLeftButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/arrow_left_button.css" })
	public ArrowLeftButtonCss getLeftButtonCss();

	/**
	 * <p>
	 * getArrowRightButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowRightButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/arrow_right_button.css" })
	public ArrowRightButtonCss getArrowRightButtonCss();

	/**
	 * <p>
	 * getArrowUpButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowUpButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/arrow_up_button.css" })
	public ArrowUpButtonCss getArrowUpButtonCss();

	/**
	 * <p>
	 * getArrowDownButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowDownButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/arrow_down_button.css" })
	public ArrowDownButtonCss getArrowDownButtonCss();

	/**
	 * <p>
	 * getBookmarkButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.BookmarkButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/bookmark_button.css" })
	public BookmarkButtonCss getBookmarkButtonCss();

	/**
	 * <p>
	 * getCameraButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.CameraButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/camera_button.css" })
	public CameraButtonCss getCameraButtonCss();

	/**
	 * <p>
	 * getComposeButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.ComposeButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/compose_button.css" })
	public ComposeButtonCss getComposeButtonCss();

	/**
	 * <p>
	 * getFastForwardButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.FastForwardButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/fast_forward_button.css" })
	public FastForwardButtonCss getFastForwardButtonCss();

	/**
	 * <p>
	 * getInfoButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.InfoButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/info_button.css" })
	public InfoButtonCss getInfoButtonCss();

	/**
	 * <p>
	 * getLocateButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.LocateButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/locate_button.css" })
	public LocateButtonCss getLocateButtonCss();

	/**
	 * <p>
	 * getNewIconButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.NewIconButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/new_icon_button.css" })
	public NewIconButtonCss getNewIconButtonCss();

	/**
	 * <p>
	 * getNextSlideButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.NextSlideButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/next_slide_button.css" })
	public NextSlideButtonCss getNextSlideButtonCss();

	/**
	 * <p>
	 * getOrganizeButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.OrganizeButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/organize_button.css" })
	public OrganizeButtonCss getOrganizeButtonCss();

	/**
	 * <p>
	 * getPauseButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.PauseButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/pause_button.css" })
	public PauseButtonCss getPauseButtonCss();

	/**
	 * <p>
	 * getPlayButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.PlayButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/play_button.css" })
	public PlayButtonCss getPlayButtonCss();

	/**
	 * <p>
	 * getPlusButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.PlusButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/plus_button.css" })
	public PlusButtonCss getPlusButtonCss();

	/**
	 * <p>
	 * getPreviousSlideButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.PreviousSlideButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/previous_slide_button.css" })
	public PreviousSlideButtonCss getPreviousSlideButtonCss();

	/**
	 * <p>
	 * getRefreshButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.RefreshButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/refresh_button.css" })
	public RefreshButtonCss getRefreshButtonCss();

	/**
	 * <p>
	 * getReplyButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.ReplyButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/reply_button.css" })
	public ReplyButtonCss getReplyButtonCss();

	/**
	 * <p>
	 * getRewindButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.RewindButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/rewind_button.css" })
	public RewindButtonCss getRewindButtonCss();

	/**
	 * <p>
	 * getSearchButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.SearchButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/search_button.css" })
	public SearchButtonCss getSearchButtonCss();

	/**
	 * <p>
	 * getStopButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.StopButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/stop_button.css" })
	public StopButtonCss getStopButtonCss();

	/**
	 * <p>
	 * getTrashButtonCss
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.TrashButtonCss}
	 *         object.
	 */
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/trash_button.css" })
	public TrashButtonCss getTrashButtonCss();
}
