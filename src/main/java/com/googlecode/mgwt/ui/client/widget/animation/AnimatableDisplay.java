/*
 * Copyright 2010 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.animation;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Represents a region of the display that can be animated
 *
 * This interface handles animations together with
 * {@link AnimatingActivityManager}. Normal mgwt users should not call any
 * methods on this interface directly.
 *
 * @author Daniel Kurka
 */
public interface AnimatableDisplay extends IsWidget {

	/**
	 * Set the first Widget of the display
	 *
	 * @param w the widet to set
	 */
	public void setFirstWidget(IsWidget w);

	/**
	 * Set the second Widget of the display
	 *
	 * @param w the widet to set
	 */
	public void setSecondWidget(IsWidget w);

	/**
	 * Start an animation on the display.
	 *
	 * @param animation the animation that the display should execute
	 * @param animateToFirst - which widget should be visible at the end of the
	 *            animation
	 * @param callback a {@link com.googlecode.mgwt.mvp.client.AnimationEndCallback} object.
	 */
	public void animate(Animation animation, boolean animateToFirst, AnimationEndCallback callback);

}
