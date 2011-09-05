package de.kurka.gwt.mobile.mvp.client.display;

import de.kurka.gwt.mobile.mvp.client.Animation;
import de.kurka.gwt.mobile.mvp.client.AnimationEndCallback;

public class AnimatableDisplayAndroidImplWithoutAnimation extends AnimatableDisplayBaseImpl {
	public AnimatableDisplayAndroidImplWithoutAnimation() {

	}

	@Override
	protected void onAnimationEnd() {

	}

	@Override
	public void animate(Animation animation, boolean currentIsFirst, AnimationEndCallback callback) {

		lastCallback = callback;
		blurBeforeAnimation();

		showFirst = currentIsFirst;

		if (showFirst) {
			second.removeFromParent();
			main.add(first);

		} else {
			first.removeFromParent();
			main.add(second);
		}
		callback.onAnimationEnd();
		return;

	}

}
