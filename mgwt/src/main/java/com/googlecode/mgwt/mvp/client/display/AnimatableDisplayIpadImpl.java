package com.googlecode.mgwt.mvp.client.display;

import com.google.gwt.dom.client.Style.Display;
import com.googlecode.mgwt.dom.client.event.animation.AnimationEndEvent;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;

public class AnimatableDisplayIpadImpl extends AnimatableDisplayBaseImpl {
	public AnimatableDisplayIpadImpl() {
		main.add(first);

		main.add(second);
	}

	@Override
	protected void onAnimationEnd() {

		if (showFirst) {

			second.getElement().getStyle().setDisplay(Display.NONE);
			second.clear();

			if (!lastDir) {
				second.getElement().getStyle().setZIndex(1);
			} else {
				first.getElement().getStyle().setZIndex(1);
			}

		} else {

			first.getElement().getStyle().setDisplay(Display.NONE);
			first.clear();

			if (!lastDir) {
				first.getElement().getStyle().setZIndex(1);
			} else {
				second.getElement().getStyle().setZIndex(1);
			}

		}
		removeAllStyles();

		if (animationEnd != null) {
			animationEnd.removeHandler();
			animationEnd = null;
		}

		if (lastCallback != null) {
			lastCallback.onAnimationEnd();
			lastCallback = null;
		}
	}

	@Override
	public void animate(Animation animation, boolean currentIsFirst, AnimationEndCallback callback) {
		lastCallback = callback;
		blurBeforeAnimation();

		showFirst = currentIsFirst;

		if (animation == null) {
			if (showFirst) {
				first.getElement().getStyle().setDisplay(Display.BLOCK);

			} else {
				second.getElement().getStyle().setDisplay(Display.BLOCK);
			}
			onAnimationEnd();
			return;
		}

		String type = animation.getCssName();

		if (animationEnd != null) {
			animationEnd.removeHandler();
			animationEnd = null;
		}

		animationEnd = main.addDomHandler(listener, AnimationEndEvent.getType());

		first.addStyleName(type);
		second.addStyleName(type);

		lastDir = animation.isInverse();
		// backwards
		if (animation.isInverse()) {
			first.addStyleName(this.css.reverse());
			second.addStyleName(this.css.reverse());

		}
		if (currentIsFirst) {
			first.addStyleName(this.css.in());
			second.addStyleName(this.css.out());

			if (!lastDir) {
				second.getElement().getStyle().setZIndex(-1);
			} else {
				first.getElement().getStyle().setZIndex(-1);
			}

		} else {
			first.addStyleName(this.css.out());
			second.addStyleName(this.css.in());

			if (!lastDir) {
				first.getElement().getStyle().setZIndex(-1);
			} else {
				second.getElement().getStyle().setZIndex(-1);
			}

		}

		first.getElement().getStyle().setDisplay(Display.BLOCK);
		second.getElement().getStyle().setDisplay(Display.BLOCK);

	}
}
