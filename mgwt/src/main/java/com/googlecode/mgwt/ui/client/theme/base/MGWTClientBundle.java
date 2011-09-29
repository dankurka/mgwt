package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.TextResource;

public interface MGWTClientBundle extends ClientBundle {

	@Source("css/progressbar.css")
	ProgressBarCss getProgressBarCss();

	//This is a very nasty workaround because GWT CssResource does not support @media correctly!
	@Source("css/util_fake.css")
	UtilCss getUtilCss();

	//This is a very nasty workaround because GWT CssResource does not support @media correctly!
	@Source("css/util.css")
	TextResource utilTextResource();

	@Source("css/progressindicator.css")
	ProgressIndicatorCss getProgressIndicatorCss();

	@Source("css/header.css")
	HeaderCss getHeaderCss();

	@Source("css/slider.css")
	SliderCss getSliderCss();

	@Source("css/list.css")
	ListCss getListCss();

	@Source("resources/list/arrow.png")
	DataResource listArrow();

	@Source("resources/list/arrow_touched.png")
	DataResource listArrowTouched();

	@Source("css/searchbox.css")
	MSearchBoxCss getSearchBoxCss();

	@Source("resources/search/search.png")
	DataResource searchSearchImage();

	@Source("resources/search/clear.png")
	DataResource searchClearImage();

	@Source("resources/search/clear_touched.png")
	DataResource searchClearTouchedImage();

	@Source("css/checkbox.css")
	CheckBoxCss getCheckBoxCss();

	@Source("css/buttons.css")
	ButtonCss getButtonCss();

	@Source("css/scrollpanel.css")
	ScrollPanelCss getScrollPanelCss();

	@Source("css/buttonbar.css")
	ButtonBarCss getButtonBarCss();

	@Source("css/dialog.css")
	DialogCss getDialogCss();

	@Source("css/main.css")
	MainCss getMainCss();

	@Source("css/input.css")
	InputCss getInputCss();

	@Source("resources/input/check.png")
	DataResource inputCheckImage();

	@Source("css/panel.css")
	PanelCss getPanelCss();

	@Source("resources/buttons/UIButtonBarAction.png")
	DataResource buttonBarActionImage();

	@Source("resources/buttons/UIButtonBarArrowDown.png")
	DataResource buttonBarArrowDownImage();

	@Source("resources/buttons/UIButtonBarArrowUp.png")
	DataResource buttonBarArrowUpImage();

	@Source("resources/buttons/UIButtonBarArrowLeft.png")
	DataResource buttonBarArrowLeftImage();

	@Source("resources/buttons/UIButtonBarArrowRight.png")
	DataResource buttonBarArrowRightImage();

	@Source("resources/buttons/UIButtonBarBookmarks.png")
	DataResource buttonBarBookmarksImage();

	@Source("resources/buttons/UIButtonBarCamera.png")
	DataResource buttonBarCameraImage();

	@Source("resources/buttons/UIButtonBarCompose.png")
	DataResource buttonBarComposeImage();

	@Source("resources/buttons/UIButtonBarContactAdd.png")
	DataResource buttonBarContactAddImage();

	@Source("resources/buttons/UIButtonBarDelete.png")
	DataResource buttonBarDeleteImage();

	@Source("resources/buttons/UIButtonBarFastForward.png")
	DataResource buttonBarFastForwardImage();

	@Source("resources/buttons/UIButtonBarInfo.png")
	DataResource buttonBarInfoImage();

	@Source("resources/buttons/UIButtonBarLocate.png")
	DataResource buttonBarLocateImage();

	@Source("resources/buttons/UIButtonBarNew.png")
	DataResource buttonBarNewImage();

	@Source("resources/buttons/UIButtonBarNextSlide.png")
	DataResource buttonBarNextSlideImage();

	@Source("resources/buttons/UIButtonBarOrganize.png")
	DataResource buttonBarOrganizeImage();

	@Source("resources/buttons/UIButtonBarPause.png")
	DataResource buttonBarPauseImage();

	@Source("resources/buttons/UIButtonBarPlay.png")
	DataResource buttonBarPlayImage();

	@Source("resources/buttons/UIButtonBarPlus.png")
	DataResource buttonBarPlusImage();

	@Source("resources/buttons/UIButtonBarPressedIndicator.png")
	DataResource buttonBarPressedIndicatorImage();

	@Source("resources/buttons/UIButtonBarPreviousSlide.png")
	DataResource buttonBarPreviousSlideImage();

	@Source("resources/buttons/UIButtonBarRefresh.png")
	DataResource buttonBarRefreshImage();

	@Source("resources/buttons/UIButtonBarReply.png")
	DataResource buttonBarReplyImage();

	@Source("resources/buttons/UIButtonBarRewind.png")
	DataResource buttonBarRewindImage();

	@Source("resources/buttons/UIButtonBarSearch.png")
	DataResource buttonBarSearchImage();

	@Source("resources/buttons/UIButtonBarStop.png")
	DataResource buttonBarStopImage();

	@Source("resources/buttons/UIButtonBarTrash.png")
	DataResource buttonBarTrashImage();

	@Source("css/tabbar.css")
	TabBarCss getTabBarCss();

	@Source("resources/tabbar/UITabBarBackgroundBlack.png")
	DataResource tabBarBackgroundImage();

	@Source("resources/tabbar/UITabBarBookmarks.png")
	DataResource tabBarBookMarkImage();

	@Source("resources/tabbar/UITabBarBookmarksSelected.png")
	DataResource tabBarBookMarkSelectedImage();

	@Source("resources/tabbar/UITabBarContacts.png")
	DataResource tabBarContactsImage();

	@Source("resources/tabbar/UITabBarContactsSelected.png")
	DataResource tabBarContactsSelectedImage();

	@Source("resources/tabbar/UITabBarDownloads.png")
	DataResource tabBarDownloadsImage();

	@Source("resources/tabbar/UITabBarDownloadsSelected.png")
	DataResource tabBarDownloadsSelectedImage();

	@Source("resources/tabbar/UITabBarFavorites.png")
	DataResource tabBarFavoritesImage();

	@Source("resources/tabbar/UITabBarFavoritesSelected.png")
	DataResource tabBarFavoritesSelectedImage();

	@Source("resources/tabbar/UITabBarFeatured.png")
	DataResource tabBarFeaturedImage();

	@Source("resources/tabbar/UITabBarFeaturedSelected.png")
	DataResource tabBarFeaturedSelectedImage();

	@Source("resources/tabbar/UITabBarHistory.png")
	DataResource tabBarHistoryImage();

	@Source("resources/tabbar/UITabBarHistorySelected.png")
	DataResource tabBarHistorySelectedImage();

	@Source("resources/tabbar/UITabBarMore.png")
	DataResource tabBarMoreImage();

	@Source("resources/tabbar/UITabBarMoreSelected.png")
	DataResource tabBarMoreSelectedImage();

	@Source("resources/tabbar/UITabBarMostRecent.png")
	DataResource tabBarMostRecentImage();

	@Source("resources/tabbar/UITabBarMostRecentSelected.png")
	DataResource tabBarMostRecentSelectedImage();

	@Source("resources/tabbar/UITabBarMostViewed.png")
	DataResource tabBarMostViewedImage();

	@Source("resources/tabbar/UITabBarMostViewedSelected.png")
	DataResource tabBarMostViewedSelectedImage();

	@Source("resources/tabbar/UITabBarSearch.png")
	DataResource tabBarSearchImage();

	@Source("resources/tabbar/UITabBarSearchSelected.png")
	DataResource tabBarSearchSelectedImage();

}
