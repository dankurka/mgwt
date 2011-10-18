package com.googlecode.mgwt.ui.client.theme.mgwt;

import com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss;
import com.googlecode.mgwt.ui.client.theme.base.ButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.theme.base.LayoutCss;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundle;
import com.googlecode.mgwt.ui.client.theme.base.MSearchBoxCss;
import com.googlecode.mgwt.ui.client.theme.base.MainCss;
import com.googlecode.mgwt.ui.client.theme.base.PanelCss;
import com.googlecode.mgwt.ui.client.theme.base.PopoverCss;
import com.googlecode.mgwt.ui.client.theme.base.ProgressBarCss;
import com.googlecode.mgwt.ui.client.theme.base.ProgressIndicatorCss;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss;
import com.googlecode.mgwt.ui.client.theme.base.SliderCss;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;

public interface MGWTStandardBundle extends MGWTClientBundle {

	@Source({ "css/color.css", "css/progressbar.css" })
	ProgressBarCss getProgressBarCss();

	@Source({ "css/color.css", "css/progressindicator.css" })
	ProgressIndicatorCss getProgressIndicatorCss();

	@Source({ "css/color.css", "css/header.css" })
	HeaderCss getHeaderCss();

	@Source({ "css/color.css", "css/slider.css" })
	SliderCss getSliderCss();

	@Source("css/popover.css")
	PopoverCss getPopoverCss();

	@Source({ "css/color.css", "css/list.css" })
	ListCss getListCss();

	@Source({ "css/color.css", "css/searchbox.css" })
	MSearchBoxCss getSearchBoxCss();

	@Source("css/checkbox.css")
	CheckBoxCss getCheckBoxCss();

	@Source({ "css/color.css", "css/buttons.css" })
	ButtonCss getButtonCss();

	@Source("css/scrollpanel.css")
	ScrollPanelCss getScrollPanelCss();

	@Source({ "css/color.css", "css/buttonbar.css" })
	ButtonBarCss getButtonBarCss();

	@Source("css/dialog.css")
	DialogCss getDialogCss();

	@Source({ "css/color.css", "css/main.css" })
	MainCss getMainCss();

	@Source({ "css/color.css", "css/input.css" })
	InputCss getInputCss();

	@Source({ "css/color.css", "css/panel.css" })
	PanelCss getPanelCss();

	@Source("css/layout.css")
	LayoutCss getLayoutCss();

	@Source("css/pulltorefresh.css")
	PullToRefreshCss getPullToRefreshCss();

	@Source({ "css/color.css", "css/tabbar.css" })
	TabBarCss getTabBarCss();

}
