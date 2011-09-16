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

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.animation.AnimationEndEvent;
import de.kurka.gwt.mobile.dom.client.event.animation.AnimationEndHandler;
import de.kurka.gwt.mobile.mvp.client.AnimatableDisplay;
import de.kurka.gwt.mobile.mvp.client.Animation;

/**
 * @author Daniel Kurka
 * 
 */
public class OverlayPanel extends Composite implements HasWidgets {

	private AnimatableDisplay display;

	public OverlayPanel() {

		display = GWT.create(AnimatableDisplay.class);

		initWidget(display.asWidget());

		main = new FlowPanel();
		display.setFirstWidget(main);

		// TODO into css!
		// getElement().getStyle().setPosition(Position.ABSOLUTE);
		// getElement().getStyle().setWidth(100, Unit.PCT);
		// getElement().getStyle().setHeight(100, Unit.PCT);

	}

	private FlowPanel main;
	private FlowPanel container;
	private FlowPanel shadowContainer;

	private boolean show;

	private HandlerRegistration handler;

	private void onAnimationEnd() {

		if (!show) {

			RootPanel.get().remove(shadowContainer);
			RootPanel.get().remove(container);
		}

		removeAllStyles();
	}

	private void removeAllStyles() {

		shadowContainer.removeStyleName(Animation.ANIMATION_FADE);
		shadowContainer.removeStyleName("in");
		shadowContainer.removeStyleName("out");

		container.removeStyleName("in");
		container.removeStyleName("out");
		container.removeStyleName("reverse");
		container.removeStyleName(Animation.ANIMATION_POP);

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onAttach()
	 */
	@Override
	protected void onAttach() {
		super.onAttach();
		handler = container.addDomHandler(new AnimationEndHandler() {

			@Override
			public void onAnimationEnd(AnimationEndEvent event) {
				OverlayPanel.this.onAnimationEnd();

			}
		}, AnimationEndEvent.getType());

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onDetach()
	 */
	@Override
	protected void onDetach() {
		super.onDetach();
		handler.removeHandler();
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
		container.addStyleName(Animation.ANIMATION_POP);
		container.addStyleName("in");

		if (shadowContainer == null) {
			shadowContainer = new FlowPanel();
			shadowContainer.setStylePrimaryName("mgwt-PopupContainer");
			shadowContainer.addStyleDependentName("shadow");

		}

		shadowContainer.addStyleName(Animation.ANIMATION_FADE);
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
		shadowContainer.addStyleName(Animation.ANIMATION_FADE);

		container.addStyleName(Animation.ANIMATION_POP);
		container.addStyleName("out");
		container.addStyleName("reverse");

		show = false;
	}

	/**
	 * @return the show
	 */
	public boolean isShow() {
		return show;
	}

	@Override
	public void add(Widget w) {

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Widget w) {
		// TODO Auto-generated method stub
		return false;
	}
}
