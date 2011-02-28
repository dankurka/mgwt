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
package de.kurka.gwt.mobile.mvp.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.animation.AnimationEndEvent;
import de.kurka.gwt.mobile.dom.client.event.animation.AnimationEndHandler;
import de.kurka.gwt.mobile.dom.client.event.animation.HasAnimationEndEvent;
import de.kurka.gwt.mobile.ui.client.util.FeatureDetection;

/**
 * @author Daniel Kurka
 *
 */
public class AnimatableDisplayImpl implements AnimatableDisplay {

	private FlowPanel main;

	private FlowPanel first;

	private FlowPanel second;

	private boolean lastDir;

	public AnimatableDisplayImpl() {
		main = new FlowPanel();

		main.setStylePrimaryName("mgwt-AnimatableDisplay");

		first = new FlowPanel();
		first.addStyleName("mgwt-AnimatableDisplay-container");
		first.addStyleName("threedstuff");

		main.add(first);
		second = new FlowPanel();
		second.addStyleName("mgwt-AnimatableDisplay-container");
		second.addStyleName("threedstuff");

		main.add(second);

		listener = new AnimationEndListener();

	}

	private class AnimationEndListener implements AnimationEndHandler {

		@Override
		public void onAnimationEnd(AnimationEndEvent event) {
			AnimatableDisplayImpl.this.onAnimationEnd();

		}

	}

	@Override
	public void setFirstWidget(IsWidget w) {
		first.clear();

		first.add(w);

	}

	@Override
	public void setSecondWidget(IsWidget w) {
		second.clear();
		second.add(w);

	}

	private void removeAllStyles() {

		first.removeStyleName("in");
		first.removeStyleName("out");
		first.removeStyleName("reverse");

		first.removeStyleName(Animation.ANIMATION_DISSOLVE);
		first.removeStyleName(Animation.ANIMATION_FADE);
		first.removeStyleName(Animation.ANIMATION_FLIP);
		first.removeStyleName(Animation.ANIMATION_POP);
		first.removeStyleName(Animation.ANIMATION_SLIDE);
		first.removeStyleName(Animation.ANIMATION_SLIDE_UP);
		first.removeStyleName(Animation.ANIMATION_SWAP);

		second.removeStyleName("in");
		second.removeStyleName("out");
		second.removeStyleName("reverse");

		second.removeStyleName(Animation.ANIMATION_DISSOLVE);
		second.removeStyleName(Animation.ANIMATION_FADE);
		second.removeStyleName(Animation.ANIMATION_FLIP);
		second.removeStyleName(Animation.ANIMATION_POP);
		second.removeStyleName(Animation.ANIMATION_SLIDE);
		second.removeStyleName(Animation.ANIMATION_SLIDE_UP);
		second.removeStyleName(Animation.ANIMATION_SWAP);

	}

	private void onAnimationEnd() {
		if (FeatureDetection.isAndroid()) {
			Document.get().getBody().setAttribute("style", "");
			first.getElement().setAttribute("style", "");
			second.getElement().setAttribute("style", "");
		}

		if (showFirst) {

			second.getElement().getStyle().setDisplay(Display.NONE);
			if (FeatureDetection.isPad()) {
				if (!lastDir) {
					second.getElement().getStyle().setZIndex(1);
				} else {
					first.getElement().getStyle().setZIndex(1);
				}
			}
		} else {

			first.getElement().getStyle().setDisplay(Display.NONE);
			if (FeatureDetection.isPad()) {
				if (!lastDir) {
					first.getElement().getStyle().setZIndex(1);
				} else {
					second.getElement().getStyle().setZIndex(1);
				}

			}

		}
		removeAllStyles();

		if (animationEnd != null) {
			animationEnd.removeHandler();
			animationEnd = null;
		}

	}

	private boolean showFirst;
	private HandlerRegistration animationEnd;
	private AnimationEndListener listener;

	@Override
	public void animate(Animation animation, boolean currentIsFirst) {
		blurBeforeAnimation();

		String type = animation.getType();
		showFirst = currentIsFirst;

		if (animationEnd != null) {
			animationEnd.removeHandler();
			animationEnd = null;
		}

		animationEnd = first.addDomHandler(listener, AnimationEndEvent.getType());
		if (FeatureDetection.isAndroid()) {
			Document.get().getBody().setAttribute("style", "-webkit-perspective: 800; -webkit-transform-style: preserve-3d;");
			first.getElement().setAttribute("style", "-webkit-backface-visibility: hidden;");
			second.getElement().setAttribute("style", "-webkit-backface-visibility: hidden;");
		}
		first.addStyleName(type);
		second.addStyleName(type);

		lastDir = animation.isDirection();
		//backwards
		if (animation.isDirection()) {
			first.addStyleName("reverse");
			second.addStyleName("reverse");

		}
		if (currentIsFirst) {
			first.addStyleName("in");
			second.addStyleName("out");
			if (FeatureDetection.isPad()) {
				if (!lastDir) {
					second.getElement().getStyle().setZIndex(-1);
				} else {
					first.getElement().getStyle().setZIndex(-1);
				}

			}

		} else {
			first.addStyleName("out");
			second.addStyleName("in");
			if (FeatureDetection.isPad()) {
				if (!lastDir) {
					first.getElement().getStyle().setZIndex(-1);
				} else {
					second.getElement().getStyle().setZIndex(-1);
				}
			}
		}

		first.getElement().getStyle().setDisplay(Display.BLOCK);
		second.getElement().getStyle().setDisplay(Display.BLOCK);

	}

	/**
	 * 
	 */
	private native void blurBeforeAnimation() /*-{
		var node = $doc.querySelector(":focus");


		if(node != null)
		{
		if(typeof(node.blur) == "function"){
		node.blur();
		}

		}
	}-*/;

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return main;
	}

	/* (non-Javadoc)
	 * @see de.kurka.gwt.mobile.mvp.client.AnimatableDisplay#getAnimationHandler()
	 */
	@Override
	public HasAnimationEndEvent getAnimationHandler() {
		return new AnimationEndWrapper();
	}

	private class AnimationEndWrapper implements HasAnimationEndEvent {

		@Override
		public HandlerRegistration addAnimationEndHandler(AnimationEndHandler handler) {
			return main.addDomHandler(handler, AnimationEndEvent.getType());
		}

	}

}
