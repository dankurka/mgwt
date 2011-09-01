package de.kurka.gwt.mobile.ui.client;

import com.google.gwt.core.client.GWT;

public class MGWTUtil {
	private static final FeatureDetection FEATURE_DETECTION = GWT.create(FeatureDetection.class);
	
	public static FeatureDetection getFeatureDetection() {
		return FEATURE_DETECTION;
	}
}
