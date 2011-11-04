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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTUtil;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader;
import com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State;

/**
 * A header for a pull panel that shows an arrow
 * 
 * @author Daniel Kurka
 * 
 */
public class PullArrowHeader extends Widget implements PullHeader {

	private Element main;

	private Element icon;

	private Element textContainer;

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
		main = DOM.createDiv();
		main.addClassName(css.pullToRefresh());
		setElement(main);

		icon = DOM.createDiv();
		icon.addClassName(css.arrow());
		main.appendChild(icon);

		textContainer = DOM.createDiv();
		textContainer.addClassName(css.text());
		main.appendChild(textContainer);
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
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#scrollStart(com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State)
	 */
	@Override
	public void scrollStart(State state) {
		remoteStyles();

		icon.addClassName(css.arrow());

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#onScroll(com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State, int)
	 */
	@Override
	public void onScroll(State state, int positionY) {
		int degree = getRotation(positionY);
		if (MGWTUtil.getOsDetection().isAndroid()) {
			icon.setAttribute("style", "-webkit-transform: rotate(" + degree + "deg);");
		} else {
			icon.setAttribute("style", "-webkit-transform: rotate(" + degree + "deg) translateZ(0);");
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#onScrollEnd(com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State, int, int)
	 */
	@Override
	public void onScrollEnd(State state, int positionY, int duration) {
		if (state == State.PULL_RELEASE) {
			showSpinner();
		}

	}

	public void showError() {
		remoteStyles();
		icon.addClassName(css.error());
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#getHeight()
	 */
	@Override
	public int getHeight() {
		//TODO
		return 40;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#getStateSwitchPosition()
	 */
	@Override
	public int getStateSwitchPosition() {
		//TODO
		return 40;
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader#setHTML(java.lang.String)
	 */
	@Override
	public void setHTML(String html) {
		textContainer.setInnerHTML(html);

	}

	protected void showArrow() {
		remoteStyles();
		icon.addClassName(css.arrow());
	}

	protected void showSpinner() {
		remoteStyles();

		icon.addClassName(css.spinner());

	}

	protected int getRotation(int y) {
		int degree = (y - 30) * -10;
		if (degree < -90)
			degree = -90;
		if (degree > 90) {
			degree = 90;
		}
		return degree;

	}

	protected void remoteStyles() {
		icon.removeClassName(css.arrow());
		icon.removeClassName(css.spinner());
		icon.removeClassName(css.error());
	}

}
