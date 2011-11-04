package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.TextResource;
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

	@Source("css/layout.css")
	LayoutCss getLayoutCss();

	@Source("css/pulltorefresh.css")
	PullToRefreshCss getPullToRefreshCss();

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

	@Source("resources/spinner.png")
	DataResource spinnerImage();

	@Source("resources/error.png")
	DataResource errorImage();

	@Source("resources/input/check_android_checked.png")
	DataResource android_check_checked();

	@Source("resources/input/check_android_not_checked.png")
	DataResource android_check_not_checked();

	//buttonbar buttons
	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/action_button.css" })
	public ActionButtonCss getActionButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/arrow_left_button.css" })
	public ArrowLeftButtonCss getLeftButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/arrow_right_button.css" })
	public ArrowRightButtonCss getArrowRightButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/arrow_up_button.css" })
	public ArrowUpButtonCss getArrowUpButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/arrow_down_button.css" })
	public ArrowDownButtonCss getArrowDownButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/add_contact_button.css" })
	public AddContactButtonCss getAddContactButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/bookmark_button.css" })
	public BookmarkButtonCss getBookmarkButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/camera_button.css" })
	public CameraButtonCss getCameraButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/compose_button.css" })
	public ComposeButtonCss getComposeButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/delete_button.css" })
	public DeleteButtonCss getDeleteButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/fast_forward_button.css" })
	public FastForwardButtonCss getFastForwardButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/info_button.css" })
	public InfoButtonCss getInfoButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/locate_button.css" })
	public LocateButtonCss getLocateButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/new_icon_button.css" })
	public NewIconButtonCss getNewIconButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/next_slide_button.css" })
	public NextSlideButtonCss getNextSlideButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/organize_button.css" })
	public OrganizeButtonCss getOrganizeButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/pause_button.css" })
	public PauseButtonCss getPauseButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/play_button.css" })
	public PlayButtonCss getPlayButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/plus_button.css" })
	public PlusButtonCss getPlusButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/previous_slide_button.css" })
	public PreviousSlideButtonCss getPreviousSlideButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/refresh_button.css" })
	public RefreshButtonCss getRefreshButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/reply_button.css" })
	public ReplyButtonCss getReplyButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/rewind_button.css" })
	public RewindButtonCss getRewindButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/search_button.css" })
	public SearchButtonCss getSearchButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/stop_button.css" })
	public StopButtonCss getStopButtonCss();

	@Source({ "buttonbar/css/buttonbarbase.css", "buttonbar/css/trash_button.css" })
	public TrashButtonCss getTrashButtonCss();
}
