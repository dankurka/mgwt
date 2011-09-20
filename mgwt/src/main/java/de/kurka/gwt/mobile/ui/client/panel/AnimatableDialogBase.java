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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.mvp.client.AnimatableDisplay;
import de.kurka.gwt.mobile.mvp.client.Animation;
import de.kurka.gwt.mobile.mvp.client.AnimationEndCallback;
import de.kurka.gwt.mobile.ui.client.theme.base.DialogCss;

public abstract class AnimatableDialogBase implements HasWidgets {
	private AnimatableDisplay display;
	protected FlowPanel container;

	private boolean centerContent;
	protected final DialogCss css;

	public AnimatableDialogBase(DialogCss css) {
		this.css = css;
		css.ensureInjected();
		display = GWT.create(AnimatableDisplay.class);

		display.asWidget().addStyleName(css.animationContainerShadow());

		container = new FlowPanel();

		container.addStyleName(css.animationContainer());

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
}
