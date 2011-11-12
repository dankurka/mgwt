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

/**
 * <p>MGWTStandardBundle interface.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public interface MGWTStandardBundle extends MGWTClientBundleBaseThemeRetina {

	/**
	 * <p>getProgressBarCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ProgressBarCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/progressbar.css", "com/googlecode/mgwt/ui/client/theme/mgwt/css/progressbar.css" })
	ProgressBarCss getProgressBarCss();

	/**
	 * <p>getProgressIndicatorCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ProgressIndicatorCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/progressindicator.css" })
	ProgressIndicatorCss getProgressIndicatorCss();

	/**
	 * <p>getHeaderCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.HeaderCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/header.css", "css/header.css" })
	HeaderCss getHeaderCss();

	/**
	 * <p>getSliderCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.SliderCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/slider.css", "css/slider.css" })
	SliderCss getSliderCss();

	/**
	 * <p>getListCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ListCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/list.css", "css/list.css" })
	ListCss getListCss();

	/**
	 * <p>getSearchBoxCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.MSearchBoxCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/searchbox.css", "css/searchbox.css" })
	MSearchBoxCss getSearchBoxCss();

	//TODO
	/**
	 * <p>getCheckBoxCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss} object.
	 */
	@Source("css/checkbox.css")
	CheckBoxCss getCheckBoxCss();

	/**
	 * <p>getButtonCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ButtonCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/buttons.css", "css/buttons.css" })
	ButtonCss getButtonCss();

	/**
	 * <p>getScrollPanelCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ScrollPanelCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/scrollpanel.css", "css/scrollpanel.css" })
	ScrollPanelCss getScrollPanelCss();

	/**
	 * <p>getButtonBarCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/buttonbar.css", "css/buttonbar.css" })
	ButtonBarCss getButtonBarCss();

	//TODO
	/**
	 * <p>getDialogCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.DialogCss} object.
	 */
	@Source("css/dialog.css")
	DialogCss getDialogCss();

	/**
	 * <p>getMainCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.MainCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/main.css", "css/main.css" })
	MainCss getMainCss();

	/**
	 * <p>getInputCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.InputCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/input.css", "css/input.css" })
	InputCss getInputCss();

	/**
	 * <p>getPanelCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.PanelCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/panel.css", "css/panel.css" })
	PanelCss getPanelCss();

	/**
	 * <p>getLayoutCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.LayoutCss} object.
	 */
	@Source("com/googlecode/mgwt/ui/client/theme/base/css/layout.css")
	LayoutCss getLayoutCss();

	//TODO
	/**
	 * <p>getPullToRefreshCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss} object.
	 */
	@Source("css/pulltorefresh.css")
	PullToRefreshCss getPullToRefreshCss();

	/**
	 * <p>getTabBarCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.ui.client.theme.base.TabBarCss} object.
	 */
	@Source({ "color.css", "com/googlecode/mgwt/ui/client/theme/base/css/tabbar.css", "css/tabbar.css" })
	TabBarCss getTabBarCss();

}
