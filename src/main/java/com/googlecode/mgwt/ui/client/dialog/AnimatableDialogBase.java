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
package com.googlecode.mgwt.ui.client.dialog;

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
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapToNativeTouchHandler;
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
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchDelegate;

/**
 * Baseclass for creating dialogs that are animated
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public abstract class AnimatableDialogBase implements HasWidgets, HasTouchHandlers, HasTapHandlers, Dialog {

	private final class InternalTouchHandler implements TouchHandler {
		private final com.google.gwt.user.client.Element shadow;
		private Element startTarget;

		private InternalTouchHandler(com.google.gwt.user.client.Element shadow) {
			this.shadow = shadow;
		}

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
	}

	protected FlowPanel container;
	protected HasWidgets panelToOverlay;
	protected final DialogCss css;

	private AnimatableDisplay display;
	private boolean centerContent;
	private boolean hideOnBackgroundClick;
	private boolean isVisible;
	private TouchDelegate touchDelegate;

	/**
	 * Create an instance of an animated dialog
	 *
	 * @param css a {@link com.googlecode.mgwt.ui.client.theme.base.DialogCss} object.
	 */
	public AnimatableDialogBase(DialogCss css) {
		this.css = css;
		css.ensureInjected();
		display = GWT.create(AnimatableDisplay.class);

		display.asWidget().addStyleName(css.animationContainerShadow());
		display.asWidget().addStyleName(css.z_index());

		touchDelegate = new TouchDelegate(display.asWidget());

		container = new FlowPanel();

		container.addStyleName(css.animationContainer());

		final com.google.gwt.user.client.Element shadow = container.getElement();

		addTouchHandler(new InternalTouchHandler(shadow));

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public void add(Widget w) {
		container.add(w);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchStartHandler(com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return touchDelegate.addTouchStartHandler(handler);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchMoveHandler(com.googlecode.mgwt.dom.client.event.touch.TouchMoveHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return touchDelegate.addTouchMoveHandler(handler);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchCancelHandler(com.googlecode.mgwt.dom.client.event.touch.TouchCancelHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return touchDelegate.addTouchCancelHandler(handler);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchEndHandler(com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return touchDelegate.addTouchEndHandler(handler);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.HasTouchHandlers#addTouchHandler(com.googlecode.mgwt.dom.client.event.touch.TouchHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTouchHandler(TouchHandler handler) {
		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(handler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(handler));
		return handlerRegistrationCollection;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler#addSimpleTouchHandler(com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchHandler)
	 */
	/** {@inheritDoc} */
	public HandlerRegistration addTapHandler(TapHandler handler) {
		TapToNativeTouchHandler touchHandler = new TapToNativeTouchHandler(handler);

		HandlerRegistrationCollection handlerRegistrationCollection = new HandlerRegistrationCollection();

		handlerRegistrationCollection.addHandlerRegistration(addTouchCancelHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchStartHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchEndHandler(touchHandler));
		handlerRegistrationCollection.addHandlerRegistration(addTouchMoveHandler(touchHandler));
		return handlerRegistrationCollection;
	}

	/**
	 * Show the dialog centered
	 */
	public void center() {
		centerContent = true;
		show();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#clear()
	 */
	/** {@inheritDoc} */
	@Override
	public void clear() {
		container.clear();
	}

	/**
	 * get the panel that the dialog overlays
	 *
	 * @return the panel that is overlayed by this dialog
	 */
	public HasWidgets getPanelToOverlay() {
		if (panelToOverlay == null) {
			panelToOverlay = RootPanel.get();
		}
		return panelToOverlay;
	}

	/**
	 * hide the dialog if it is visible
	 */
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

	/**
	 * Should the dialog hide itself if there is a tap outside the dialog
	 *
	 * @return true if the dialog automatically hides, otherwise false
	 */
	public boolean isHideOnBackgroundClick() {
		return hideOnBackgroundClick;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
	 */
	/** {@inheritDoc} */
	@Override
	public Iterator<Widget> iterator() {
		return container.iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public boolean remove(Widget w) {
		return container.remove(w);
	}

	/**
	 * Should the content of the dialog be centered
	 *
	 * @param centerContent true to center content
	 */
	public void setCenterContent(boolean centerContent) {
		this.centerContent = centerContent;
	}

	/**
	 * Should the dialog hide itself if there is a tap outside the dialog
	 *
	 * @param hideOnBackgroundClick true if the dialog automatically hides,
	 *            otherwise false
	 */
	public void setHideOnBackgroundClick(boolean hideOnBackgroundClick) {
		this.hideOnBackgroundClick = hideOnBackgroundClick;
	}

	/**
	 * set the panel that should be overlayed by the dialog
	 *
	 * @param panel the area to be overlayed
	 */
	public void setPanelToOverlay(HasWidgets panel) {
		this.panelToOverlay = panel;
	}

	/**
	 * should the dialog add a shadow over the area that it covers
	 *
	 * @param shadow true to add a shadow
	 */
	public void setShadow(boolean shadow) {
		if (shadow) {
			display.asWidget().addStyleName(css.animationContainerShadow());
		} else {
			display.asWidget().removeStyleName(css.animationContainerShadow());

		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.dialog.Dialog#show()
	 */
	/**
	 * <p>show</p>
	 */
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

	/**
	 * <p>getShowAnimation</p>
	 *
	 * @return a {@link com.googlecode.mgwt.mvp.client.Animation} object.
	 */
	protected abstract Animation getShowAnimation();

	/**
	 * <p>getHideAnimation</p>
	 *
	 * @return a {@link com.googlecode.mgwt.mvp.client.Animation} object.
	 */
	protected abstract Animation getHideAnimation();

	/**
	 * <p>maybeHide</p>
	 */
	protected void maybeHide() {
		if (hideOnBackgroundClick) {
			hide();
		}
	}

}
