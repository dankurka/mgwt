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
import com.google.gwt.resources.client.TextResource;
import com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss;
import com.googlecode.mgwt.ui.client.theme.base.ButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.theme.base.LayoutCss;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.theme.base.MSearchBoxCss;
import com.googlecode.mgwt.ui.client.theme.base.MainCss;
import com.googlecode.mgwt.ui.client.theme.base.PanelCss;
import com.googlecode.mgwt.ui.client.theme.base.ProgressBarCss;
import com.googlecode.mgwt.ui.client.theme.base.ProgressIndicatorCss;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss;
import com.googlecode.mgwt.ui.client.theme.base.SliderCss;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;
import com.googlecode.mgwt.ui.client.theme.base.UtilCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ActionButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.AddContactButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowDownButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowLeftButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowRightButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowUpButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.BookmarkButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.CameraButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ComposeButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.DeleteButtonCss;
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

public interface MGWTClientBundle {

	ProgressBarCss getProgressBarCss();

	UtilCss getUtilCss();

	TextResource utilTextResource();

	ProgressIndicatorCss getProgressIndicatorCss();

	HeaderCss getHeaderCss();

	SliderCss getSliderCss();

	ListCss getListCss();

	DataResource listArrow();

	DataResource listArrowTouched();

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

	DataResource buttonBarActionImage();

	DataResource buttonBarArrowDownImage();

	DataResource buttonBarArrowUpImage();

	DataResource buttonBarArrowLeftImage();

	DataResource buttonBarArrowRightImage();

	DataResource buttonBarBookmarksImage();

	DataResource buttonBarCameraImage();

	DataResource buttonBarComposeImage();

	DataResource buttonBarContactAddImage();

	DataResource buttonBarDeleteImage();

	DataResource buttonBarFastForwardImage();

	DataResource buttonBarInfoImage();

	DataResource buttonBarLocateImage();

	DataResource buttonBarNewImage();

	DataResource buttonBarNextSlideImage();

	DataResource buttonBarOrganizeImage();

	DataResource buttonBarPauseImage();

	DataResource buttonBarPlayImage();

	DataResource buttonBarPlusImage();

	DataResource buttonBarPressedIndicatorImage();

	DataResource buttonBarPreviousSlideImage();

	DataResource buttonBarRefreshImage();

	DataResource buttonBarReplyImage();

	DataResource buttonBarRewindImage();

	DataResource buttonBarSearchImage();

	DataResource buttonBarStopImage();

	DataResource buttonBarTrashImage();

	TabBarCss getTabBarCss();

	BookmarkTabBarButtonCss getBookmarkTabBarButtonCss();

	ContactsTabBarButtonCss getContactsTabBarButtonCss();

	DownloadsTabBarButtonCss getDownloadsTabBarButtonCss();

	FavoritesTabBarButtonCss getFavoritesTabBarButtonCss();

	FeaturedTabBarButtonCss getFeaturedTabBarButtonCss();

	HistoryTabBarButtonCss getHistoryTabBarButtonCss();

	MoreTabBarButtonCss getMoreTabBarButtonCss();

	MostRecentTabBarButtonCss getMostRecentTabBarButtonCss();

	MostViewedTabBarButtonCss getMostViewedTabBarButtonCss();

	SearchTabBarButtonCss getSearchTabBarButtonCss();

	DataResource tabBarBookMarkImage();

	DataResource tabBarBookMarkSelectedImage();

	DataResource tabBarContactsImage();

	DataResource tabBarContactsSelectedImage();

	DataResource tabBarDownloadsImage();

	DataResource tabBarDownloadsSelectedImage();

	DataResource tabBarFavoritesImage();

	DataResource tabBarFavoritesSelectedImage();

	DataResource tabBarFeaturedImage();

	DataResource tabBarFeaturedSelectedImage();

	DataResource tabBarHistoryImage();

	DataResource tabBarHistorySelectedImage();

	DataResource tabBarMoreImage();

	DataResource tabBarMoreSelectedImage();

	DataResource tabBarMostRecentImage();

	DataResource tabBarMostRecentSelectedImage();

	DataResource tabBarMostViewedImage();

	DataResource tabBarMostViewedSelectedImage();

	DataResource tabBarSearchImage();

	DataResource tabBarSearchSelectedImage();

	DataResource spinnerImage();

	DataResource spinnerWhiteImage();

	DataResource errorImage();

	DataResource android_check_checked();

	DataResource android_check_not_checked();

	public ActionButtonCss getActionButtonCss();

	public ArrowLeftButtonCss getLeftButtonCss();

	public ArrowRightButtonCss getArrowRightButtonCss();

	public ArrowUpButtonCss getArrowUpButtonCss();

	public ArrowDownButtonCss getArrowDownButtonCss();

	public AddContactButtonCss getAddContactButtonCss();

	public BookmarkButtonCss getBookmarkButtonCss();

	public CameraButtonCss getCameraButtonCss();

	public ComposeButtonCss getComposeButtonCss();

	public DeleteButtonCss getDeleteButtonCss();

	public FastForwardButtonCss getFastForwardButtonCss();

	public InfoButtonCss getInfoButtonCss();

	public LocateButtonCss getLocateButtonCss();

	public NewIconButtonCss getNewIconButtonCss();

	public NextSlideButtonCss getNextSlideButtonCss();

	public OrganizeButtonCss getOrganizeButtonCss();

	public PauseButtonCss getPauseButtonCss();

	public PlayButtonCss getPlayButtonCss();

	public PlusButtonCss getPlusButtonCss();

	public PreviousSlideButtonCss getPreviousSlideButtonCss();

	public RefreshButtonCss getRefreshButtonCss();

	public ReplyButtonCss getReplyButtonCss();

	public RewindButtonCss getRewindButtonCss();

	public SearchButtonCss getSearchButtonCss();

	public StopButtonCss getStopButtonCss();

	public TrashButtonCss getTrashButtonCss();
}
