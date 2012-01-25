package com.googlecode.mgwt.ui.client;

import com.googlecode.mgwt.ui.client.theme.MGWTTheme;
import com.googlecode.mgwt.ui.client.theme.MGWTThemeBaseThemeStandardImpl;

/**
 * The {@link MGWTStyle} class provides an easy access to the default theme of
 * every gwt app.
 * 
 * If widgets are created without a specific theme they ask MGWTStyle for the
 * default theme.
 * 
 * The default theme can be changed at start up time of an mgwt app <b>once</b>.
 * 
 * After startup the bundle can not be changed. If no bundle is set, MGWTStyle
 * creates the MGWT Default theme.
 * 
 * If you like to create your own theme consult the docs at: <a
 * href="http://code.google.com/p/mgwt/wiki/Styling"
 * >http://code.google.com/p/mgwt/wiki/Styling</a>
 * 
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class MGWTStyle {

	private static MGWTTheme theme;

	/**
	 * get the default bundle of this mgwt app
	 * 
	 * @return the default bundle
	 */
	public static final MGWTTheme getTheme() {
		if (theme == null) {
			theme = new MGWTThemeBaseThemeStandardImpl();
			theme.getMGWTClientBundle().getMainCss().ensureInjected();

		}
		return theme;
	}

	/**
	 * Set the default bundle of this mgwt app
	 * 
	 * <p>
	 * can only be called once at startup
	 * <p>
	 * 
	 * 
	 * @param bundle the default bundle to use
	 */
	public static final void setTheme(MGWTTheme newTheme) {
		if (theme != null) {
			throw new IllegalStateException("can not change default theme if theres already an instance...");
		}
		theme = newTheme;
		theme.getMGWTClientBundle().getMainCss().ensureInjected();
	}
}
