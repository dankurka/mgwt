package com.googlecode.mgwt.ui.client.theme.mgwt;

import com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss;
import com.googlecode.mgwt.ui.client.theme.base.ButtonCss;
import com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.theme.base.LayoutCss;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeRetina;
import com.googlecode.mgwt.ui.client.theme.base.MSearchBoxCss;
import com.googlecode.mgwt.ui.client.theme.base.MainCss;
import com.googlecode.mgwt.ui.client.theme.base.PanelCss;
import com.googlecode.mgwt.ui.client.theme.base.ProgressBarCss;
import com.googlecode.mgwt.ui.client.theme.base.ProgressIndicatorCss;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss;
import com.googlecode.mgwt.ui.client.theme.base.SliderCss;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;

public interface MGWTColorBundleRetina extends MGWTClientBundleBaseThemeRetina {
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/progressbar.css", "com/googlecode/mgwt/ui/client/theme/mgwt/css/progressbar.css" })
	ProgressBarCss getProgressBarCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/progressindicator.css" })
	ProgressIndicatorCss getProgressIndicatorCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/header.css", "css/header.css" })
	HeaderCss getHeaderCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/slider.css", "css/slider.css" })
	SliderCss getSliderCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/list.css", "css/list.css" })
	ListCss getListCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/searchbox.css", "css/searchbox.css" })
	MSearchBoxCss getSearchBoxCss();

	// TODO
	// still needs styling
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/checkbox.css", "css/checkbox.css" })
	CheckBoxCss getCheckBoxCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/buttons.css", "css/buttons.css" })
	ButtonCss getButtonCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/scrollpanel.css", "css/scrollpanel.css" })
	ScrollPanelCss getScrollPanelCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/buttonbar.css", "css/buttonbar.css" })
	ButtonBarCss getButtonBarCss();

	// TODO still needs styling...
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/dialog.css", "css/dialog.css" })
	DialogCss getDialogCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/main.css", "css/main.css" })
	MainCss getMainCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/input.css", "css/input.css" })
	InputCss getInputCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/panel.css", "css/panel.css" })
	PanelCss getPanelCss();

	@Source("com/googlecode/mgwt/ui/client/theme/base/css/layout.css")
	LayoutCss getLayoutCss();

	// TODO
	// still needs styling..
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/pulltorefresh.css", "css/pulltorefresh.css" })
	PullToRefreshCss getPullToRefreshCss();

	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/tabbar.css", "css/tabbar.css" })
	TabBarCss getTabBarCss();

	// TODO buttons for tabbar still need styling...
}
