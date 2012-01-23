package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.tabbar.TabBarButtonCss;

public class TabBarButton extends TabBarButtonBase {

	public TabBarButton(ImageResource imageResource) {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarButtonCss(), imageResource);
	}

	public TabBarButton(TabBarButtonCss css, ImageResource imageResource) {
		super(css, imageResource);

	}

}
