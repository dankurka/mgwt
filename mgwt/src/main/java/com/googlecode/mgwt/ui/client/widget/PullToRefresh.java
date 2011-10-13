package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.widget.base.PullArrowHeader;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel;
import com.googlecode.mgwt.ui.client.widget.event.PullReleasedEvent;
import com.googlecode.mgwt.ui.client.widget.event.PullReleasedHandler;
import com.googlecode.mgwt.ui.client.widget.event.PullStateChangedEvent;
import com.googlecode.mgwt.ui.client.widget.event.PullStateChangedHandler;

/**
 * Experimental don`t use right now
 * 
 * @author Daniel Kurka
 * 
 */
public class PullToRefresh extends PullPanel {

	private SafeHtml noReloadHTML;
	private SafeHtml reloadHTML;

	public PullToRefresh() {
		super(new PullArrowHeader(MGWTStyle.getDefaultClientBundle().getPullToRefreshCss()));
		InternalPullListener listener = new InternalPullListener();

		addPullReleasedHandler(listener);
		addPullStateChangedHandler(listener);

		setNoReloadHTML(SafeHtmlUtils.fromTrustedString("<div>pull down</div>"));
		setReloadHTML(SafeHtmlUtils.fromTrustedString("<div>release to reload</div>"));

		updateText();
	}

	public void setNoReloadHTML(SafeHtml html) {
		if (html == null) {
			throw new IllegalArgumentException("html can not be null");
		}
		this.noReloadHTML = html;
		updateText();
	}

	public void setReloadHTML(SafeHtml html) {
		if (html == null) {
			throw new IllegalArgumentException("html can not be null");
		}
		this.reloadHTML = html;
		updateText();
	}

	public SafeHtml getNoReloadHTML() {
		return noReloadHTML;
	}

	public SafeHtml getReloadHTML() {
		return reloadHTML;
	}

	private void updateText() {
		switch (getState()) {
		case NO_ACTION:
			header.setHTML(noReloadHTML.asString());
			break;
		case PULL_RELEASE:
			header.setHTML(reloadHTML.asString());
			break;
		}
	}

	private class InternalPullListener implements PullStateChangedHandler, PullReleasedHandler {

		@Override
		public void onPullReleased(PullReleasedEvent event) {

		}

		@Override
		public void onPullStateChanged(PullStateChangedEvent event) {
			updateText();

		}
	}

}
