package com.googlecode.mgwt.ui.client;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundle;

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
 * You can change the theme like this:
 * 
 * <pre>
 * // create your bundle
 * MGWTClientBundle bundle = GWT.create(YourBundle.class);
 * // set your bundle as default
 * MGWTStyle.setDefaultBundle(bundle);
 * </pre>
 * 
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class MGWTStyle {

	private static MGWTClientBundle defaultClientBundle;

	/**
	 * <p>
	 * Getter for the field <code>defaultClientBundle</code>.
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundle}
	 *         object.
	 */
	public static final MGWTClientBundle getDefaultClientBundle() {
		if (defaultClientBundle == null) {
			defaultClientBundle = GWT.create(MGWTClientBundle.class);
			defaultClientBundle.getMainCss().ensureInjected();
		}
		return defaultClientBundle;
	}

	/**
	 * <p>
	 * setDefaultBundle
	 * </p>
	 * 
	 * @param bundle a
	 *            {@link com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundle}
	 *            object.
	 */
	public static final void setDefaultBundle(MGWTClientBundle bundle) {
		if (defaultClientBundle != null) {
			throw new IllegalStateException("can not change default bundle if theres already an instance...");
		}
		bundle.getMainCss().ensureInjected();
		defaultClientBundle = bundle;
	}
}
