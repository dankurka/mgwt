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
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import com.googlecode.mgwt.ui.client.theme.MGWTClientBundle;

/**
 * The iphone with retina client bundle
 * 
 * @author Daniel Kurka
 * 
 */
public interface MGWTClientBundleBaseThemeRetina extends ClientBundle, MGWTClientBundle {

	@Source({ "css/progressbar.css", "css/iphone/progressbar.css" })
	ProgressBarCss getProgressBarCss();

	// This is a very nasty workaround because GWT CssResource does not support
	// @media correctly!
	@Source("css/util_fake.css")
	UtilCss getUtilCss();

	// This is a very nasty workaround because GWT CssResource does not support
	// @media correctly!
	@Source("css/util.css")
	TextResource utilTextResource();

	@Source({ "css/progressindicator.css", "css/iphone/progressindicator.css" })
	ProgressIndicatorCss getProgressIndicatorCss();

	@Source({ "css/header.css", "css/iphone/header.css" })
	HeaderCss getHeaderCss();

	@Source({ "css/slider.css", "css/iphone/slider.css" })
	SliderCss getSliderCss();

	@Source({ "css/list.css", "css/iphone/list.css" })
	ListCss getListCss();

	@Source("resources/list/arrow.png")
	DataResource listArrow();

	@Source({ "css/searchbox.css", "css/iphone/searchbox.css" })
	MSearchBoxCss getSearchBoxCss();

	@Source("resources/search/glass.png")
	DataResource searchSearchImage();

	@Source("resources/search/search_clear.png")
	DataResource searchClearImage();

	@Source("resources/search/search_clear_touched.png")
	DataResource searchClearTouchedImage();

	@Source("css/checkbox.css")
	CheckBoxCss getCheckBoxCss();

	@Source({ "css/buttons.css", "css/iphone/buttons.css" })
	ButtonCss getButtonCss();

	@Source({ "css/scrollpanel.css", "css/iphone/scrollpanel.css" })
	ScrollPanelCss getScrollPanelCss();

	@Source({ "css/buttonbar.css", "css/iphone/buttonbar.css" })
	ButtonBarCss getButtonBarCss();

	@Source({ "css/dialog.css", "css/iphone/dialog.css" })
	DialogCss getDialogCss();

	@Source({ "css/main.css", "css/iphone/main.css" })
	MainCss getMainCss();

	@Source({ "css/input.css", "css/iphone/input.css" })
	InputCss getInputCss();

	@Source("resources/input/ios_check.png")
	DataResource inputCheckImage();

	@Source("css/panel.css")
	PanelCss getPanelCss();

	@Source("css/layout.css")
	LayoutCss getLayoutCss();

	@Source({ "css/pulltorefresh.css", "css/iphone/pulltorefresh.css" })
	PullToRefreshCss getPullToRefreshCss();

	@Source({ "css/tabbar.css", "css/iphone/tabbar.css" })
	TabBarCss getTabBarCss();

	@Source({ "css/tabbar_button.css" })
	TabBarButtonCss getTabBarButtonCss();

	@Source("resources/tabbar/bookmarks.png")
	ImageResource tabBarBookMarkImage();

	@Source("resources/tabbar/contacts.png")
	ImageResource tabBarContactsImage();

	@Source("resources/tabbar/downloads.png")
	ImageResource tabBarDownloadsImage();

	@Source("resources/tabbar/favorites.png")
	ImageResource tabBarFavoritesImage();

	@Source("resources/tabbar/featured.png")
	ImageResource tabBarFeaturedImage();

	@Source("resources/tabbar/history.png")
	ImageResource tabBarHistoryImage();

	@Source("resources/tabbar/more.png")
	ImageResource tabBarMoreImage();

	@Source("resources/tabbar/mostrecent.png")
	ImageResource tabBarMostRecentImage();

	@Source("resources/tabbar/mostviewed.png")
	ImageResource tabBarMostViewedImage();

	@Source("resources/tabbar/search.png")
	ImageResource tabBarSearchImage();

	@Source("resources/spinner.png")
	DataResource spinnerImage();

	@Source("resources/spinner_white.png")
	DataResource spinnerWhiteImage();

	@Source("resources/error.png")
	DataResource errorImage();

	@Source("resources/input/check_android_checked.png")
	DataResource android_check_checked();

	@Source("resources/input/check_android_not_checked.png")
	DataResource android_check_not_checked();

	@Source("css/buttonbarbutton.css")
	public ButtonBarButtonCss getButtonBarButtonCss();

	@Source("resources/toolbar/pressed.png")
	public ImageResource getButtonBarHighlightImage();

	@Source("resources/toolbar/action_x2.png")
	public ImageResource getButtonBarActionImage();

	@Source("resources/toolbar/arrowdown_x2.png")
	public ImageResource getButtonBarArrowDownImage();

	@Source("resources/toolbar/arrowleft_x2.png")
	public ImageResource getButtonBarArrowLeftImage();

	@Source("resources/toolbar/arrowright_x2.png")
	public ImageResource getButtonBarArrowRightImage();

	@Source("resources/toolbar/arrowup_x2.png")
	public ImageResource getButtonBarArrowUpImage();

	@Source("resources/toolbar/bookmarks_x2.png")
	public ImageResource getButtonBarBookmarkImage();

	@Source("resources/toolbar/camera_x2.png")
	public ImageResource getButtonBarCameraImage();

	@Source("resources/toolbar/compose_x2.png")
	public ImageResource getButtonBarComposeImage();

	@Source("resources/toolbar/fastforward_x2.png")
	public ImageResource getButtonBarFastForwardImage();

	@Source("resources/toolbar/info_x2.png")
	public ImageResource getButtonBarInfoImage();

	@Source("resources/toolbar/locate_x2.png")
	public ImageResource getButtonBarLocateImage();

	@Source("resources/toolbar/new_x2.png")
	public ImageResource getButtonBarNewImage();

	@Source("resources/toolbar/nextslide_x2.png")
	public ImageResource getButtonBarNextSlideImage();

	@Source("resources/toolbar/organize_x2.png")
	public ImageResource getButtonBarOrganizeImage();

	@Source("resources/toolbar/pause_x2.png")
	public ImageResource getButtonBarPauseImage();

	@Source("resources/toolbar/play_x2.png")
	public ImageResource getButtonBarPlayImage();

	@Source("resources/toolbar/plus_x2.png")
	public ImageResource getButtonBarPlusImage();

	@Source("resources/toolbar/previousslide_x2.png")
	public ImageResource getButtonBarPreviousSlideImage();

	@Source("resources/toolbar/refresh_x2.png")
	public ImageResource getButtonBarRefreshImage();

	@Source("resources/toolbar/reply_x2.png")
	public ImageResource getButtonBarReplyImage();

	@Source("resources/toolbar/rewind_x2.png")
	public ImageResource getButtonBarRewindImage();

	@Source("resources/toolbar/search_x2.png")
	public ImageResource getButtonBarSearchImage();

	@Source("resources/toolbar/stop_x2.png")
	public ImageResource getButtonBarStopImage();

	@Source("resources/toolbar/trash_x2.png")
	public ImageResource getButtonBarTrashImage();

}
