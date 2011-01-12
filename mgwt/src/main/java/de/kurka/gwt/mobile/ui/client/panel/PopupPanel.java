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
package de.kurka.gwt.mobile.ui.client.panel;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Daniel Kurka
 *
 */
public class PopupPanel extends FlowPanel {

	public PopupPanel() {
		setStylePrimaryName("mgwt-PopupPanel");
	}

	private FlowPanel container;
	private FlowPanel shadowContainer;

	private boolean show;

	private JavaScriptObject listener;

	private void onAnimationEnd() {
		Window.alert("test");
		if (show) {

		} else {
			RootPanel.get().remove(shadowContainer);
			RootPanel.get().remove(container);
		}
		container.getElement().getStyle().setOpacity(1.0);
		removeAllStyles();
	}

	/**
	 * 
	 */
	private void removeAllStyles() {

		shadowContainer.removeStyleName("fade");
		shadowContainer.removeStyleName("in");
		shadowContainer.removeStyleName("out");
		container.removeStyleName("slideup");
		container.removeStyleName("in");
		container.removeStyleName("out");
		container.removeStyleName("reverse");
		container.removeStyleName("fade");

	}

	private native JavaScriptObject addAnimationEndEvent(Element element)/*-{
		var instance = this;

		var func = function(event){

		instance.@de.kurka.gwt.mobile.ui.client.panel.PopupPanel::onAnimationEnd()();
		};

		element.addEventListener('webkitAnimationEnd', func, false);

		return func;
	}-*/;

	private native JavaScriptObject removeAnimationEndEvent(Element element, JavaScriptObject func)/*-{
		element.removeEventListener('webkitAnimationEnd', func, false);
	}-*/;

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onAttach()
	 */
	@Override
	protected void onAttach() {
		super.onAttach();
		listener = addAnimationEndEvent(container.getElement());
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onDetach()
	 */
	@Override
	protected void onDetach() {
		super.onDetach();
		removeAnimationEndEvent(container.getElement(), listener);
	}

	/**
	 * 
	 */
	public void show() {
		RootPanel rootPanel = RootPanel.get();

		if (container == null) {
			container = new FlowPanel();
			container.setStylePrimaryName("mgwt-PopupContainer");

		}
		container.addStyleName("slideup");
		container.addStyleName("in");

		if (shadowContainer == null) {
			shadowContainer = new FlowPanel();
			shadowContainer.setStylePrimaryName("mgwt-PopupContainer");
			shadowContainer.addStyleDependentName("shadow");

		}

		shadowContainer.addStyleName("fade");
		shadowContainer.addStyleName("in");

		shadowContainer.clear();

		container.clear();

		container.add(this);

		rootPanel.add(shadowContainer);
		rootPanel.add(container);

		show = true;

	}

	public void hide() {
		if (container == null) {
			return;
		}

		shadowContainer.addStyleName("out");
		shadowContainer.addStyleName("fade");

		container.addStyleName("slideup");
		container.addStyleName("fade");
		container.addStyleName("out");
		container.addStyleName("reverse");

		container.getElement().getStyle().setOpacity(0.0);

		show = false;
	}

	/**
	 * @return the show
	 */
	public boolean isShow() {
		return show;
	}
}
