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
 * The desktop client bundle
 * 
 * @author Daniel Kurka
 * 
 */
public interface MGWTClientBundleBaseThemeIE9 extends ClientBundle, MGWTClientBundle {

	@Source({ "css/progressbar.css", "css/ie9/progressbar.css" })
	ProgressBarCss getProgressBarCss();

	// This is a very nasty workaround because GWT CssResource does not support
	// @media correctly!
	@Source("css/util_fake.css")
	UtilCss getUtilCss();

	// This is a very nasty workaround because GWT CssResource does not support
	// @media correctly!
	@Source("css/util.css")
	TextResource utilTextResource();

	@Source({ "css/progressindicator.css", "css/ipad/progressindicator.css" })
	ProgressIndicatorCss getProgressIndicatorCss();

	@Source({ "css/header.css", "css/ie9/header.css" })
	HeaderCss getHeaderCss();

	@Source({ "css/slider.css", "css/ipad/slider.css" })
	SliderCss getSliderCss();

	@Source({ "css/list.css", "css/ie9/list.css" })
	ListCss getListCss();

	@Source("resources/list/arrow.png")
	DataResource listArrow();

	@Source({ "css/searchbox.css", "css/ipad/searchbox.css" })
	MSearchBoxCss getSearchBoxCss();

	@Source("resources/search/glass.png")
	DataResource searchSearchImage();

	@Source("resources/search/search_clear.png")
	DataResource searchClearImage();

	@Source("resources/search/search_clear_touched.png")
	DataResource searchClearTouchedImage();

	@Source("css/checkbox.css")
	CheckBoxCss getCheckBoxCss();

	@Source({ "css/buttons.css", "css/ie9/buttons.css" })
	ButtonCss getButtonCss();

	@Source({ "css/scrollpanel.css", "css/ipad/scrollpanel.css" })
	ScrollPanelCss getScrollPanelCss();

	@Source({ "css/buttonbar.css", "css/ie9/buttonbar.css" })
	ButtonBarCss getButtonBarCss();

	@Source({ "css/dialog.css", "css/ipad/dialog.css" })
	DialogCss getDialogCss();

	@Source("css/main.css")
	MainCss getMainCss();

	@Source({ "css/input.css", "css/ipad/input.css" })
	InputCss getInputCss();

	@Source("resources/input/ios_check.png")
	DataResource inputCheckImage();

	@Source("css/panel.css")
	PanelCss getPanelCss();

	@Source("css/layout.css")
	LayoutCss getLayoutCss();

	@Source({ "css/pulltorefresh.css", "css/ipad/pulltorefresh.css" })
	PullToRefreshCss getPullToRefreshCss();

	@Source({ "css/tabbar.css", "css/ipad/tabbar.css" })
	TabBarCss getTabBarCss();

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

	@Source("resources/toolbar/action.png")
	public ImageResource getButtonBarActionImage();

	@Source("resources/toolbar/arrowdown.png")
	public ImageResource getButtonBarArrowDownImage();

	@Source("resources/toolbar/arrowleft.png")
	public ImageResource getButtonBarArrowLeftImage();

	@Source("resources/toolbar/arrowright.png")
	public ImageResource getButtonBarArrowRightImage();

	@Source("resources/toolbar/arrowup.png")
	public ImageResource getButtonBarArrowUpImage();

	@Source("resources/toolbar/bookmarks.png")
	public ImageResource getButtonBarBookmarkImage();

	@Source("resources/toolbar/camera.png")
	public ImageResource getButtonBarCameraImage();

	@Source("resources/toolbar/compose.png")
	public ImageResource getButtonBarComposeImage();

	@Source("resources/toolbar/fastforward.png")
	public ImageResource getButtonBarFastForwardImage();

	@Source("resources/toolbar/info.png")
	public ImageResource getButtonBarInfoImage();

	@Source("resources/toolbar/locate.png")
	public ImageResource getButtonBarLocateImage();
	
	@Source("resources/toolbar/minus.png")
	public ImageResource getButtonBarMinusImage();

	@Source("resources/toolbar/new.png")
	public ImageResource getButtonBarNewImage();

	@Source("resources/toolbar/nextslide.png")
	public ImageResource getButtonBarNextSlideImage();

	@Source("resources/toolbar/organize.png")
	public ImageResource getButtonBarOrganizeImage();

	@Source("resources/toolbar/pause.png")
	public ImageResource getButtonBarPauseImage();

	@Source("resources/toolbar/play.png")
	public ImageResource getButtonBarPlayImage();

	@Source("resources/toolbar/plus.png")
	public ImageResource getButtonBarPlusImage();

	@Source("resources/toolbar/previousslide.png")
	public ImageResource getButtonBarPreviousSlideImage();

	@Source("resources/toolbar/refresh.png")
	public ImageResource getButtonBarRefreshImage();

	@Source("resources/toolbar/reply.png")
	public ImageResource getButtonBarReplyImage();

	@Source("resources/toolbar/rewind.png")
	public ImageResource getButtonBarRewindImage();

	@Source("resources/toolbar/search.png")
	public ImageResource getButtonBarSearchImage();

	@Source("resources/toolbar/stop.png")
	public ImageResource getButtonBarStopImage();

	@Source("resources/toolbar/trash.png")
	public ImageResource getButtonBarTrashImage();

}
