package de.kurka.gwt.mobile.mvp.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface MVPClientBundle extends ClientBundle {
	public static final MVPClientBundle INSTANCE = GWT.create(MVPClientBundle.class);

	@Source("animation.css")
	public AnimationCss animationCss();
}
