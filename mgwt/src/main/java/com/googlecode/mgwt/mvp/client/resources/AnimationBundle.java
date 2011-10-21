package com.googlecode.mgwt.mvp.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface AnimationBundle extends ClientBundle {

	@Source("animation.css")
	public AnimationCss animationCss();

	public final AnimationBundle INSTANCE = GWT.create(AnimationBundle.class);
}
