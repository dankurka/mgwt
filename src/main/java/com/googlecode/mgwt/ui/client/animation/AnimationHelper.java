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
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;

public class AnimationHelper extends Composite {

	protected final AnimatableDisplay display;

	protected boolean isFirst;

	protected boolean isAnimating;

	public AnimationHelper() {
		display = GWT.create(AnimatableDisplay.class);
		isFirst = true;
		isAnimating = false;
		initWidget(display.asWidget());
	}

	public void goTo(IsWidget w, Animation animation) {
		goTo(w, animation, null);
	}

	public void goTo(IsWidget w, Animation animation, AnimationEndCallback callback) {
		goTo(w.asWidget(), animation, callback);
	}

	public void goTo(Widget w, Animation animation) {
		goTo(w, animation, null);
	}

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

}
