package com.googlecode.mgwt.mvp.client.resources;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.mvp.client.AnimationNames;
import com.googlecode.mgwt.ui.client.MGWTUtil;

public class AnimationSelector {

	private static final MVPBundle BUNDLE = GWT.create(MVPBundle.class);

	public static AnimationNames getNames() {
		if (MGWTUtil.getFeatureDetection().isAndroid()) {
			return BUNDLE.transitionCss();
		} else {
			return BUNDLE.animationCss();
		}
	}

	public static MVPBundle getBundle() {
		return BUNDLE;
	}

}
