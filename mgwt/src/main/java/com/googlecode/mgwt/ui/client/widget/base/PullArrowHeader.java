package com.googlecode.mgwt.ui.client.widget.base;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullHeader;
import com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent.State;

public class PullArrowHeader extends Widget implements PullHeader {

	private Element main;

	private Element arrow;

	private Element textContainer;

	private final PullToRefreshCss css;

	public PullArrowHeader(PullToRefreshCss css) {

		this.css = css;
		css.ensureInjected();
		main = DOM.createDiv();
		main.addClassName(css.pullToRefresh());
		setElement(main);

		arrow = DOM.createDiv();
		arrow.addClassName(css.arrow());
		main.appendChild(arrow);

		textContainer = DOM.createDiv();
		textContainer.addClassName(css.text());
		main.appendChild(textContainer);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void scrollStart(State state) {
		arrow.removeClassName(css.spinner());
		arrow.addClassName(css.arrow());

	}

	@Override
	public void onScroll(State state, int positionY) {
		int degree = getRotation(positionY);
		arrow.setAttribute("style", "-webkit-transform: rotate(" + degree + "deg) translateZ(0);");

	}

	@Override
	public void onScrollEnd(State state, int positionY, int duration) {
		if (state == State.PULL_RELEASE) {
			showSpinner(true);
		} else {
			int degree = getRotation(positionY);
			arrow.setAttribute("style", "-webkit-transform: rotate(" + degree + "deg) translateZ(0);-webkit-transition: all " + duration + "ms linear;");

		}

	}

	public void showSpinner(boolean show) {
		if (show) {
			arrow.addClassName(css.spinner());
			arrow.removeClassName(css.arrow());

		} else {
			arrow.removeClassName(css.spinner());
			arrow.addClassName(css.arrow());
		}
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

	@Override
	public int getHeight() {
		//TODO calculate height to be able to accept different css
		return 40;
	}

	@Override
	public int getStateSwitchPosition() {
		//TODO calculate height to be able to accept different css
		return 40;
	}

	@Override
	public void setHTML(String html) {
		textContainer.setInnerHTML(html);

	}

}
