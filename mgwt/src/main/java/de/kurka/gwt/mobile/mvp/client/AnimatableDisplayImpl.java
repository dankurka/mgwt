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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author kurt
 *
 */
public class AnimatableDisplayImpl implements AnimatableDisplay {

	private FlowPanel main;

	private FlowPanel first;

	private FlowPanel second;

	public AnimatableDisplayImpl() {
		main = new FlowPanel();
		//TODO css
		main.setSize("100%", "100%");
		main.getElement().getStyle().setPosition(Position.ABSOLUTE);
		main.getElement().getStyle().setLeft(0, Unit.PX);
		main.getElement().getStyle().setRight(0, Unit.PX);
		main.getElement().getStyle().setTop(0, Unit.PX);
		main.getElement().getStyle().setBottom(0, Unit.PX);

		first = new FlowPanel();
		//TODO move into css
		first.getElement().getStyle().setPosition(Position.ABSOLUTE);
		first.getElement().getStyle().setWidth(100, Unit.PCT);
		first.getElement().getStyle().setHeight(100, Unit.PCT);

		first.addStyleName("threedstuff");

		main.add(first);
		second = new FlowPanel();
		//TODO move into css
		second.getElement().getStyle().setPosition(Position.ABSOLUTE);
		second.getElement().getStyle().setWidth(100, Unit.PCT);
		second.getElement().getStyle().setHeight(100, Unit.PCT);

		second.addStyleName("threedstuff");

		main.add(second);
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

		if (showFirst) {
			//second.clear();
			second.getElement().getStyle().setDisplay(Display.NONE);
		} else {
			//first.clear();
			first.getElement().getStyle().setDisplay(Display.NONE);
		}
		removeAllStyles();

		if (addAnimationEndEvent != null) {
			removeAnimationEndEvent(first.getElement(), addAnimationEndEvent);
			addAnimationEndEvent = null;
		}

	}

	private native JavaScriptObject addAnimationEndEvent(Element element)/*-{
		var instance = this;

		var func = function(){
		instance.@de.kurka.gwt.mobile.mvp.client.AnimatableDisplayImpl::onAnimationEnd()();
		};

		element.addEventListener('webkitAnimationEnd', func, false);
		return element;
	}-*/;

	private native JavaScriptObject removeAnimationEndEvent(Element element, JavaScriptObject func)/*-{
		element.removeEventListener('webkitAnimationEnd', func);
	}-*/;

	private boolean showFirst;

	private JavaScriptObject addAnimationEndEvent;

	@Override
	public void animate(Animation animation, boolean currentIsFirst) {
		blurBeforeAnimation();

		String type = animation.getType();
		showFirst = currentIsFirst;

		if (addAnimationEndEvent != null) {
			removeAnimationEndEvent(first.getElement(), addAnimationEndEvent);
			addAnimationEndEvent = null;
		}

		//if (!Animation.ANIMATION_SLIDE.equals(type) && !Animation.ANIMATION_SLIDE_UP.equals(type)) {

		//}

		addAnimationEndEvent = addAnimationEndEvent(first.getElement());

		first.addStyleName(type);
		second.addStyleName(type);

		//backwards
		if (animation.isDirection()) {
			first.addStyleName("reverse");
			second.addStyleName("reverse");

		}
		if (currentIsFirst) {
			first.addStyleName("in");
			second.addStyleName("out");

		} else {
			first.addStyleName("out");
			second.addStyleName("in");
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

}
