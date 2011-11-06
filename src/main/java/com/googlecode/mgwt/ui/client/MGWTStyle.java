package com.googlecode.mgwt.ui.client;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundle;

/**
 * <p>
 * MGWTStyle class.
 * </p>
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
		defaultClientBundle = bundle;
	}
}
