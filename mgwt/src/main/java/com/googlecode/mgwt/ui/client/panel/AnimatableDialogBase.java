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
package com.googlecode.mgwt.ui.client.panel;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.mouse.HandlerRegistrationCollection;
import com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchToNativeTouchHandler;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidgetImpl;

public abstract class AnimatableDialogBase implements HasWidgets, HasTouchHandlers {
	private AnimatableDisplay display;
	protected FlowPanel container;

	private boolean centerContent;
	protected final DialogCss css;
	private boolean hideOnBackgroundClick;

	private boolean isVisible;

	public AnimatableDialogBase(DialogCss css) {
		this.css = css;
		css.ensureInjected();
		display = GWT.create(AnimatableDisplay.class);

		display.asWidget().addStyleName(css.animationContainerShadow());
		display.asWidget().addStyleName(css.z_index());

		container = new FlowPanel();

		container.addStyleName(css.animationContainer());

		final com.google.gwt.user.client.Element shadow = container.getElement();

		addTouchHandler(new TouchHandler() {

			private Element startTarget;

			@Override
			public void onTouchCanceled(TouchCancelEvent event) {
				startTarget = null;

			}

			@Override
			public void onTouchEnd(TouchEndEvent event) {
				EventTarget eventTarget = event.getNativeEvent().getEventTarget();
				if (eventTarget != null) {
					// no textnode or element node
					if (Node.is(eventTarget)) {
						if (Element.is(eventTarget)) {
							Element endTarget = eventTarget.cast();

							if (endTarget == shadow && startTarget == shadow) {
								maybeHide();
							}

						}
					}
				}
				startTarget = null;

			}

			@Override
			public void onTouchMove(TouchMoveEvent event) {

			}

			@Override
			public void onTouchStart(TouchStartEvent event) {

				EventTarget eventTarget = event.getNativeEvent().getEventTarget();
				if (eventTarget != null) {
					// no textnode or element node
					if (Node.is(eventTarget)) {
						if (Element.is(eventTarget)) {
							startTarget = eventTarget.cast();

						}
					}
				}

			}
		});

	}

	protected void maybeHide() {
		if (hideOnBackgroundClick) {
			hide();
		}

	}

	public void center() {
		centerContent = true;
		show();
	}

	public void setCenterContent(boolean centerContent) {
		this.centerContent = centerContent;
	}

	protected HasWidgets panelToOverlay;

	public void setPanelToOverlay(HasWidgets panel) {
		this.panelToOverlay = panel;
	}

	public HasWidgets getPanelToOverlay() {
		if (panelToOverlay == null) {
			panelToOverlay = RootPanel.get();
		}
		return panelToOverlay;
	}

	public void show() {
		if (isVisible) {
			return;
		}
		isVisible = true;

		// add overlay to DOM
		HasWidgets panel = getPanelToOverlay();
		panel.add(display.asWidget());

		if (centerContent) {
			container.addStyleName(css.animationContainerCenter());

		}

		display.setFirstWidget(container);

		// and animiate
		Animation animation = getShowAnimation();

		display.animate(animation, true, new AnimationEndCallback() {

			@Override
			public void onAnimationEnd() {

			}
		});

	}

	public void hide() {
		if (!isVisible)
			return;
		isVisible = false;
		Animation animation = getHideAnimation();

		display.animate(animation, false, new AnimationEndCallback() {

			@Override
			public void onAnimationEnd() {
				HasWidgets panel = getPanelToOverlay();
				panel.remove(display.asWidget());

			}
		});

	}

	@Override
	public void add(Widget w) {
		container.add(w);

	}

	@Override
	public void clear() {
		container.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		return container.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return container.remove(w);
	}

	protected abstract Animation getShowAnimation();

	protected abstract Animation getHideAnimation();

	private static final TouchWidgetImpl impl = GWT.create(TouchWidgetImpl.class);

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return impl.addTouchStartHandler(display.asWidget(), handler);

	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return impl.addTouchMoveHandler(display.asWidget(), handler);

	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return impl.addTouchCancelHandler(display.asWidget(), handler);

	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return impl.addTouchEndHandler(display.asWidget(), handler);

	}

	@Override
	public HandlerRegistration addTouchHandler(TouchHandler handler) {
		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(handler));
		return handlerRegistrationCollection;
	}

	public HandlerRegistration addSimpleTouchHandler(SimpleTouchHandler handler) {
		SimpleTouchToNativeTouchHandler touchHandler = new SimpleTouchToNativeTouchHandler(handler);

		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(touchHandler));
		return handlerRegistrationCollection;
	}

	public void setHideOnBackgroundClick(boolean hideOnBackgroundClick) {
		this.hideOnBackgroundClick = hideOnBackgroundClick;
	}

	public boolean isHideOnBackgroundClick() {
		return hideOnBackgroundClick;
	}

	public void setShadow(boolean shadow) {
		if (shadow) {
			display.asWidget().addStyleName(css.animationContainerShadow());
		} else {
			display.asWidget().removeStyleName(css.animationContainerShadow());

		}

	}

}
