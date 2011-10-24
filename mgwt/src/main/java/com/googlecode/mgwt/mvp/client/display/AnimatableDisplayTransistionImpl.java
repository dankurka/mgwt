package com.googlecode.mgwt.mvp.client.display;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationEndCallback;
import com.googlecode.mgwt.mvp.client.resources.AnimationSelector;
import com.googlecode.mgwt.mvp.client.resources.TransistionCss;

public class AnimatableDisplayTransistionImpl implements AnimatableDisplay {
	protected FlowPanel main;

	protected SimplePanel first;

	protected SimplePanel second;

	protected boolean lastDir;

	protected final TransistionCss css;

	public AnimatableDisplayTransistionImpl() {
		this(AnimationSelector.getBundle().transitionCss());
	}

	public AnimatableDisplayTransistionImpl(TransistionCss css) {

		this.css = css;
		css.ensureInjected();

		main = new FlowPanel();

		main.setStylePrimaryName(this.css.display());

		first = new SimplePanel();
		first.addStyleName(this.css.displayContainer());

		second = new SimplePanel();
		second.addStyleName(this.css.displayContainer());

		listener = new TransistionEndListener();

		main.add(first);

		main.add(second);

	}

	protected class TransistionEndListener implements TransitionEndHandler {

		@Override
		public void onTransitionEnd(TransitionEndEvent event) {
			AnimatableDisplayTransistionImpl.this.onAnimationEnd();

		}

	}

	@Override
	public void setFirstWidget(IsWidget w) {
		first.setWidget(w);
	}

	@Override
	public void setSecondWidget(IsWidget w) {
		second.setWidget(w);
	}

	protected void removeAllStyles() {

		first.removeStyleName(this.css.in());
		first.removeStyleName(this.css.out());
		first.removeStyleName(this.css.reverse());
		first.removeStyleName(this.css.start());
		first.removeStyleName(this.css.end());

		first.removeStyleName(this.css.dissolve());
		first.removeStyleName(this.css.fade());
		first.removeStyleName(this.css.flip());
		first.removeStyleName(this.css.pop());
		first.removeStyleName(this.css.slide());
		first.removeStyleName(this.css.slideup());
		first.removeStyleName(this.css.swap());

		second.removeStyleName(this.css.in());
		second.removeStyleName(this.css.out());
		second.removeStyleName(this.css.reverse());
		second.removeStyleName(this.css.start());
		second.removeStyleName(this.css.end());

		second.removeStyleName(this.css.dissolve());
		second.removeStyleName(this.css.fade());
		second.removeStyleName(this.css.flip());
		second.removeStyleName(this.css.pop());
		second.removeStyleName(this.css.slide());
		second.removeStyleName(this.css.slideup());
		second.removeStyleName(this.css.swap());

	}

	protected boolean showFirst;
	protected HandlerRegistration animationEnd;

	protected TransistionEndListener listener;

	protected AnimationEndCallback lastCallback;

	/**
	 * 
	 */
	protected native void blurBeforeAnimation() /*-{
		var node = $doc.querySelector(":focus");

		if (node != null) {
			if (typeof (node.blur) == "function") {
				node.blur();
			}

		}
	}-*/;

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void animate(Animation animation, boolean currentIsFirst, AnimationEndCallback callback) {

		lastCallback = callback;
		blurBeforeAnimation();

		showFirst = currentIsFirst;

		if (animation == null) {
			if (showFirst) {
				first.getElement().getStyle().setDisplay(Display.BLOCK);

			} else {
				second.getElement().getStyle().setDisplay(Display.BLOCK);
			}
			onAnimationEnd();
			return;
		}

		String type = animation.getCssName();

		if (animationEnd != null) {
			animationEnd.removeHandler();
			animationEnd = null;
		}

		animationEnd = main.addDomHandler(listener, TransitionEndEvent.getType());

		first.addStyleName(css.start());
		second.addStyleName(css.start());

		first.addStyleName(type);
		second.addStyleName(type);

		lastDir = animation.isInverse();
		// backwards
		if (animation.isInverse()) {
			first.addStyleName(this.css.reverse());
			second.addStyleName(this.css.reverse());

		}
		if (currentIsFirst) {
			first.addStyleName(this.css.in());
			second.addStyleName(this.css.out());

		} else {
			first.addStyleName(this.css.out());
			second.addStyleName(this.css.in());

		}

		first.getElement().getStyle().setDisplay(Display.BLOCK);
		second.getElement().getStyle().setDisplay(Display.BLOCK);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				first.removeStyleName(css.start());
				second.removeStyleName(css.start());
				first.addStyleName(css.end());
				second.addStyleName(css.end());
			}
		});

	}

	protected void onAnimationEnd() {
		if (showFirst) {

			second.getElement().getStyle().setDisplay(Display.NONE);
			second.clear();

		} else {

			first.getElement().getStyle().setDisplay(Display.NONE);
			first.clear();

		}
		removeAllStyles();

		if (animationEnd != null) {
			animationEnd.removeHandler();
			animationEnd = null;
		}

		if (lastCallback != null) {
			lastCallback.onAnimationEnd();
			lastCallback = null;
		}
	}

}
