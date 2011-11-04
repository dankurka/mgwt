/*
 * Copyright 2011 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.mvp.client.display;

import com.google.gwt.dom.client.Style.Display;
import com.googlecode.mgwt.dom.client.event.animation.AnimationEndEvent;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;

/**
 * Considered internal
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class AnimatableDisplayIpadImpl extends AnimatableDisplayBaseImpl {
	/**
	 * <p>Constructor for AnimatableDisplayIpadImpl.</p>
	 */
	public AnimatableDisplayIpadImpl() {
		main.add(first);

		main.add(second);
	}

	/** {@inheritDoc} */
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

	/** {@inheritDoc} */
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
