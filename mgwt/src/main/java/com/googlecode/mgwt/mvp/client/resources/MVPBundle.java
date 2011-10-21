package com.googlecode.mgwt.mvp.client.resources;

import com.google.gwt.resources.client.ClientBundle;

public interface MVPBundle extends ClientBundle {
	@Source("animation.css")
	public AnimationCss animationCss();

	@Source("transition.css")
	public TransistionCss transitionCss();
}
