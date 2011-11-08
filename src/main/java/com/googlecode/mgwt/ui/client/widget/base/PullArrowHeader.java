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
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.widget.ProgressIndicator;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader;
import com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State;

/**
 * A header for a pull panel that shows an arrow
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class PullArrowHeader extends Composite implements PullHeader {

	private FlowPanel main;

	private FlowPanel icon;

	private HTML textContainer;

	private ProgressIndicator indicator;

	private final PullToRefreshCss css;

	/**
	 * Construct a {@link PullArrowHeader} with a given css
	 * 
	 * @param css
	 *            the css to use
	 */
	public PullArrowHeader(PullToRefreshCss css) {

		this.css = css;
		css.ensureInjected();
		main = new FlowPanel();
		main.addStyleName(css.pullToRefresh());
		initWidget(main);

		icon = new FlowPanel();
		icon.addStyleName(css.arrow());
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
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#asWidget()
	 */
	/** {@inheritDoc} */
	@Override
	public Widget asWidget() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#scrollStart(com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State)
	 */
	/** {@inheritDoc} */
	@Override
	public void scrollStart(State state) {
		remoteStyles();

		icon.addStyleName(css.arrow());
		icon.setVisible(true);
		indicator.setVisible(false);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#onScroll(com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State, int)
	 */
	/** {@inheritDoc} */
	@Override
	public void onScroll(State state, int positionY) {
		int degree = getRotation(positionY);
		if (MGWT.getOsDetection().isAndroid()) {
			icon.getElement().setAttribute("style", "-webkit-transform: rotate(" + degree + "deg);");
		} else {
			icon.getElement().setAttribute("style", "-webkit-transform: rotate(" + degree + "deg) translateZ(0);");
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#onScrollEnd(com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State, int, int)
	 */
	/** {@inheritDoc} */
	@Override
	public void onScrollEnd(State state, int positionY, int duration) {
		icon.getElement().setAttribute("style", "");
		if (state == State.PULL_RELEASE) {
			showSpinner();
		} else {

		}

	}

	/**
	 * <p>
	 * showError
	 * </p>
	 */
	public void showError() {
		remoteStyles();
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
	protected void showArrow() {
		remoteStyles();
		icon.addStyleName(css.arrow());

		icon.setVisible(true);
		indicator.setVisible(false);
	}

	/**
	 * <p>
	 * showSpinner
	 * </p>
	 */
	protected void showSpinner() {
		remoteStyles();
		icon.setVisible(false);
		indicator.setVisible(true);

	}

	/**
	 * <p>
	 * getRotation
	 * </p>
	 * 
	 * @param y
	 *            a int.
	 * @return a int.
	 */
	protected int getRotation(int y) {
		int degree = (y - 30) * -10;
		if (degree < -90)
			degree = -90;
		if (degree > 90) {
			degree = 90;
		}
		return degree;

	}

	/**
	 * <p>
	 * remoteStyles
	 * </p>
	 */
	protected void remoteStyles() {
		icon.removeStyleName(css.arrow());
		icon.removeStyleName(css.error());
	}

}
