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
package com.googlecode.mgwt.ui.client.theme;

import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

import com.googlecode.mgwt.ui.client.theme.base.ButtonBarButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss;
import com.googlecode.mgwt.ui.client.theme.base.ButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.CarouselCss;
import com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;
import com.googlecode.mgwt.ui.client.theme.base.GroupingList;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.theme.base.LayoutCss;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.theme.base.MSearchBoxCss;
import com.googlecode.mgwt.ui.client.theme.base.MainCss;
import com.googlecode.mgwt.ui.client.theme.base.PanelCss;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;
import com.googlecode.mgwt.ui.client.theme.base.UtilCss;

public interface MGWTClientBundle {

	UtilCss getUtilCss();

	TextResource utilTextResource();

	HeaderCss getHeaderCss();

	ListCss getListCss();

	DataResource listArrow();

	MSearchBoxCss getSearchBoxCss();

	DataResource searchSearchImage();

	DataResource searchClearImage();

	DataResource searchClearTouchedImage();

	CheckBoxCss getCheckBoxCss();

	ButtonCss getButtonCss();

	ScrollPanelCss getScrollPanelCss();

	ButtonBarCss getButtonBarCss();

	DialogCss getDialogCss();

	MainCss getMainCss();

	InputCss getInputCss();

	DataResource inputCheckImage();

	PanelCss getPanelCss();

	LayoutCss getLayoutCss();

	PullToRefreshCss getPullToRefreshCss();

	TabBarCss getTabBarCss();

	GroupingList getGroupingList();

	public CarouselCss getCarouselCss();

	public ImageResource tabBarBookMarkImage();

	public ImageResource tabBarContactsImage();

	public ImageResource tabBarDownloadsImage();

	public ImageResource tabBarFavoritesImage();

	public ImageResource tabBarFeaturedImage();

	public ImageResource tabBarHistoryImage();

	public ImageResource tabBarMoreImage();

	public ImageResource tabBarMostRecentImage();

	public ImageResource tabBarMostViewedImage();

	public ImageResource tabBarSearchImage();

	DataResource spinnerImage();

	DataResource spinnerWhiteImage();

	DataResource errorImage();

	DataResource android_check_checked();

	DataResource android_check_not_checked();

	public ButtonBarButtonCss getButtonBarButtonCss();

	public ImageResource getButtonBarHighlightImage();

	public ImageResource getButtonBarActionImage();

	public ImageResource getButtonBarArrowDownImage();

	public ImageResource getButtonBarArrowLeftImage();

	public ImageResource getButtonBarArrowRightImage();

	public ImageResource getButtonBarArrowUpImage();

	public ImageResource getButtonBarBookmarkImage();

	public ImageResource getButtonBarCameraImage();

	public ImageResource getButtonBarComposeImage();

	public ImageResource getButtonBarFastForwardImage();

	public ImageResource getButtonBarInfoImage();

	public ImageResource getButtonBarLocateImage();

	public ImageResource getButtonBarMinusImage();

	public ImageResource getButtonBarNewImage();

	public ImageResource getButtonBarNextSlideImage();

	public ImageResource getButtonBarOrganizeImage();

	public ImageResource getButtonBarPauseImage();

	public ImageResource getButtonBarPlayImage();

	public ImageResource getButtonBarPlusImage();

	public ImageResource getButtonBarPreviousSlideImage();

	public ImageResource getButtonBarRefreshImage();

	public ImageResource getButtonBarReplyImage();

	public ImageResource getButtonBarRewindImage();

	public ImageResource getButtonBarSearchImage();

	public ImageResource getButtonBarStopImage();

	public ImageResource getButtonBarTrashImage();
}
