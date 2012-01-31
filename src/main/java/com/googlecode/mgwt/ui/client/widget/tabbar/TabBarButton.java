package com.googlecode.mgwt.ui.client.widget.tabbar;

import com.google.gwt.resources.client.ImageResource;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.TabBarButtonCss;

/**
 * <h1>A tab bar button</h1>
 * 
 * <h2>Styling</h2>
 * 
 * The DOM structure:
 * 
 * <pre>
 * &lt;div class="mgwt-TabBar-Button">
 * 	&lt;div class="mgwt-TabBar-Button-icon" />
 * 	&lt;div class="mgwt-TabBar-Button-text">Button Text&lt;/div>
 * &lt/div>
 * </pre>
 * 
 * The following styles are dynically added:
 * 
 * <ul>
 * <li>.mgwt-TabBar-Button-selected - if the tab is selected</li>
 * <li>.mgwt-TabBar-Button-active - applied while the button is pressed</li>
 * </ul>
 */
public class TabBarButton extends TabBarButtonBase {

	public TabBarButton(ImageResource imageResource) {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarButtonCss(), imageResource);
	}

	public TabBarButton(TabBarButtonCss css, ImageResource imageResource) {
		super(css, imageResource);

	}

}
