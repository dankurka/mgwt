package com.googlecode.mgwt.mvp.client.display;

import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;

public class AnimatableDisplayWithoutAnimation extends AnimatableDisplayBaseImpl {
	public AnimatableDisplayWithoutAnimation() {

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
