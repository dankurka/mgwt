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
package com.googlecode.mgwt.ui.client.widget.animation.impl;


/**
 * Considered internal
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
//public class AnimatableDisplayWithoutAnimation implements AnimatableDisplay {
//
//	private AnimationCss css;
//	private FlowPanel main;
//	private SimplePanel first;
//	private SimplePanel second;
//
//	public AnimatableDisplayWithoutAnimation() {
//		this(AnimationSelector.getBundle().animationCss());
//	}
//
//	public AnimatableDisplayWithoutAnimation(AnimationCss css) {
//		this.css = css;
//		css.ensureInjected();
//
//		main = new FlowPanel();
//
//		main.setStylePrimaryName(this.css.display());
//
//		first = new SimplePanel();
//		first.addStyleName(this.css.displayContainer());
//
//		second = new SimplePanel();
//		second.addStyleName(this.css.displayContainer());
//
//	}
//
//	@Override
//	public void animate(Animation animation, boolean currentIsFirst, AnimationEndCallback callback) {
//
//		if (currentIsFirst) {
//			second.removeFromParent();
//			main.add(first);
//
//		} else {
//			first.removeFromParent();
//			main.add(second);
//		}
//		callback.onAnimationEnd();
//		return;
//
//	}
//
//	@Override
//	public Widget asWidget() {
//		return main;
//	}
//
//	@Override
//	public void setFirstWidget(IsWidget w) {
//		first.setWidget(w);
//		if (w != null) {
//			main.remove(second);
//			main.add(first);
//		} else {
//			main.remove(first);
//		}
//
//	}
//
//	@Override
//	public void setSecondWidget(IsWidget w) {
//		second.setWidget(w);
//		if (w != null) {
//			main.add(second);
//			main.remove(first);
//		} else {
//			second.remove(first);
//		}
//
//	}
//
//}
