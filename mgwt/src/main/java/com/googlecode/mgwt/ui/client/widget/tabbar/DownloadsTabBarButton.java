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
package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.DownloadsTabBarButtonCss;

/**
 * <p>DownloadsTabBarButton class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class DownloadsTabBarButton extends TabBarButtonBase {

	/**
	 * <p>Constructor for DownloadsTabBarButton.</p>
	 */
	public DownloadsTabBarButton() {
		this(MGWTStyle.getDefaultClientBundle().getDownloadsTabBarButtonCss());
	}

	/**
	 * <p>Constructor for DownloadsTabBarButton.</p>
	 *
	 * @param downloadsTabBarButtonCss a {@link com.googlecode.mgwt.ui.client.theme.base.tabbar.DownloadsTabBarButtonCss} object.
	 */
	public DownloadsTabBarButton(DownloadsTabBarButtonCss downloadsTabBarButtonCss) {
		super(downloadsTabBarButtonCss);
		addStyleName(downloadsTabBarButtonCss.downloads());
		setText("Downloads");
	}

}
