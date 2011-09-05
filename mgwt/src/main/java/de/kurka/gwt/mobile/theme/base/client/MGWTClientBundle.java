package de.kurka.gwt.mobile.theme.base.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface MGWTClientBundle extends ClientBundle {
	public static final MGWTClientBundle INSTANCE = GWT.create(MGWTClientBundle.class);

	@Source("animation.css")
	public AnimationCss animationCss();
}
