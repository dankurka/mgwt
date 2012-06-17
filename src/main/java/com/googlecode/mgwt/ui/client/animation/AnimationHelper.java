/*
 * Copyright 2012 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.animation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;

/**
 * A simple helper class to make the direct use of {@link AnimatableDisplay}
 * easier.
 * 
 * For bigger apps in general you might want to be using an
 * {@link AnimatingActivityManager}
 * 
 * @author Daniel Kurka
 * 
 */
public class AnimationHelper extends Composite {

	protected final AnimatableDisplay display;

	protected boolean isFirst;

	protected boolean isAnimating;

	/**
	 * Construct an animation helper
	 */
	public AnimationHelper() {
		this((AnimatableDisplay) GWT.create(AnimatableDisplay.class));
	}

	/**
	 * Construct an animation helper with a given display
	 * 
	 * @param display the display to use
	 */
	public AnimationHelper(AnimatableDisplay display) {
		this.display = display;
		isFirst = true;
		isAnimating = false;
		initWidget(display.asWidget());
	}

	/**
	 * see: {@link #goTo(Widget, Animation, AnimationEndCallback)}
	 */
	public void goTo(IsWidget w, Animation animation) {
		goTo(w, animation, null);
	}

	/**
	 * see: {@link #goTo(Widget, Animation, AnimationEndCallback)}
	 */
	public void goTo(IsWidget w, Animation animation, AnimationEndCallback callback) {
		goTo(w.asWidget(), animation, callback);
	}

	/**
	 * see: {@link #goTo(Widget, Animation, AnimationEndCallback)}
	 */
	public void goTo(Widget w, Animation animation) {
		goTo(w, animation, null);
	}

	/**
	 * animate to a given widget. If this is called while an animation is
	 * running this is a noop.
	 * 
	 * @param w the widget to animate to
	 * @param animation the animation to use
	 * @param callback a callback that will be called once the animation is
	 *            finished
	 */
	public void goTo(Widget w, Animation animation, final AnimationEndCallback callback) {
		if (isAnimating) {
			return;
		}

		isAnimating = true;

		if (isFirst) {
			display.setFirstWidget(w);
		} else {
			display.setSecondWidget(w);
		}
		display.animate(animation, isFirst, new AnimationEndCallback() {

			@Override
			public void onAnimationEnd() {
				isAnimating = false;
				if (callback != null)
					callback.onAnimationEnd();
			}
		});

		isFirst = !isFirst;

	}

	/**
	 * Is there an animation running
	 * 
	 * @return true if there is an animation running
	 */
	public boolean isAnimating() {
		return isAnimating;
	}

}
