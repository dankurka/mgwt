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
package com.googlecode.mgwt.ui.client.widget.base;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;

/**
 * A header for a pull panel that shows an arrow
 * 
 * @author Daniel Kurka
 * 
 */
public abstract class PullArrowBase extends Composite implements PullArrowWidget {

	private FlowPanel main;

	private FlowPanel icon;

	private HTML textContainer;

	private ProgressIndicator indicator;

	private final PullToRefreshCss css;

	public PullArrowBase() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getPullToRefreshCss());
	}

	public PullArrowBase(PullToRefreshCss css) {

		this.css = css;
		css.ensureInjected();
		main = new FlowPanel();
		main.addStyleName(css.pullToRefresh());
		initWidget(main);

		icon = new FlowPanel();
		icon.removeStyleName(css.arrow());
		resetRotation();

		main.add(icon);

		indicator = new ProgressIndicator();
		indicator.addStyleName(css.spinner());
		indicator.getElement().getStyle().setDisplay(Display.NONE);
		main.add(indicator);

		textContainer = new HTML();
		textContainer.addStyleName(css.text());
		main.add(textContainer);

		addDomHandler(new TransitionEndHandler() {

			@Override
			public void onTransitionEnd(TransitionEndEvent event) {
				event.preventDefault();
				event.stopPropagation();

			}
		}, TransitionEndEvent.getType());

		showArrow();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#asWidget()
	 */

	@Override
	public Widget asWidget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#onScroll(com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State, int)
	 */
	@Override
	public void onScroll(int positionY) {
		int degree = getRotation(positionY);
		CssUtil.rotate(icon.getElement(), degree);
	}

	public void showError() {
		removeStyles();
		icon.addStyleName(css.error());

		icon.setVisible(true);
		indicator.setVisible(false);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#getHeight()
	 */
	/** {@inheritDoc} */
	@Override
	public int getHeight() {
		// TODO
		return 70;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#getStateSwitchPosition()
	 */
	/** {@inheritDoc} */
	@Override
	public int getStateSwitchPosition() {
		// TODO
		return 50;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#setHTML(java.lang.String)
	 */
	/** {@inheritDoc} */
	@Override
	public void setHTML(final String html) {
		String htmlToSet = html;
		if (html == null) {
			htmlToSet = "";
		}

		textContainer.setHTML(htmlToSet);

	}

	/**
	 * <p>
	 * showArrow
	 * </p>
	 */
	public void showArrow() {
		removeStyles();
		icon.addStyleName(css.arrow());

		resetRotation();

		icon.setVisible(true);
		indicator.setVisible(false);
	}

	public void showLoadingIndicator() {
		icon.getElement().setAttribute("style", "");
		showSpinner();

	}

	/**
	 * <p>
	 * showSpinner
	 * </p>
	 */
	protected void showSpinner() {
		removeStyles();
		icon.setVisible(false);
		indicator.setVisible(true);

	}

	protected abstract int getRotation(int y);

	protected void resetRotation() {
		int rotation = getRotation(0);
		CssUtil.rotate(icon.getElement(), rotation);
	}

	protected void removeStyles() {
		icon.removeStyleName(css.arrow());
		icon.removeStyleName(css.error());
	}

}
