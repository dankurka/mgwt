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
package de.kurka.gwt.mobile.mvp.client.display;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.animation.AnimationEndEvent;
import de.kurka.gwt.mobile.dom.client.event.animation.AnimationEndHandler;
import de.kurka.gwt.mobile.mvp.client.AnimatableDisplay;
import de.kurka.gwt.mobile.mvp.client.AnimationEndCallback;
import de.kurka.gwt.mobile.mvp.client.resources.AnimationCss;
import de.kurka.gwt.mobile.mvp.client.resources.MVPClientBundle;

/**
 * @author Daniel Kurka
 * 
 */
public abstract class AnimatableDisplayBaseImpl implements AnimatableDisplay {

	protected FlowPanel main;

	protected SimplePanel first;

	protected SimplePanel second;

	protected boolean lastDir;

	protected final AnimationCss css;

	public AnimatableDisplayBaseImpl() {
		this(MVPClientBundle.INSTANCE.animationCss());
	}

	public AnimatableDisplayBaseImpl(AnimationCss css) {

		this.css = css;
		css.ensureInjected();

		Window.alert(css.getText());

		main = new FlowPanel();

		main.setStylePrimaryName(this.css.display());

		first = new SimplePanel();
		first.addStyleName(this.css.displayContainer());

		second = new SimplePanel();
		second.addStyleName(this.css.displayContainer());

		listener = new AnimationEndListener();

	}

	protected class AnimationEndListener implements AnimationEndHandler {

		@Override
		public void onAnimationEnd(AnimationEndEvent event) {
			AnimatableDisplayBaseImpl.this.onAnimationEnd();

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

		second.removeStyleName(this.css.dissolve());
		second.removeStyleName(this.css.fade());
		second.removeStyleName(this.css.flip());
		second.removeStyleName(this.css.pop());
		second.removeStyleName(this.css.slide());
		second.removeStyleName(this.css.slideup());
		second.removeStyleName(this.css.swap());

	}

	protected abstract void onAnimationEnd();

	protected boolean showFirst;
	protected HandlerRegistration animationEnd;

	protected AnimationEndListener listener;

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

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return main;
	}

}
