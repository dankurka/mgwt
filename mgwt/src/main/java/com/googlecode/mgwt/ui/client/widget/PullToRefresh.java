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
 * May change in future releases
 * 
 * @author Daniel Kurka
 * 
 */

public class PullToRefresh extends PullPanel {

	private SafeHtml noReloadHTML;
	private SafeHtml reloadHTML;
	private SafeHtml loadingHTML;

	private SafeHtml loadingFailedHTML;

	public PullToRefresh() {
		super(new PullArrowHeader(MGWTStyle.getDefaultClientBundle().getPullToRefreshCss()));
		InternalPullListener listener = new InternalPullListener();

		addPullReleasedHandler(listener);
		addPullStateChangedHandler(listener);

		setNoReloadHTML(SafeHtmlUtils.fromTrustedString("<div>pull down</div>"));
		setReloadHTML(SafeHtmlUtils.fromTrustedString("<div>release to reload</div>"));

		setLoadingFailedHTML(SafeHtmlUtils.fromTrustedString("<div>failed to load</div>"));
		setLoadingHTML(SafeHtmlUtils.fromTrustedString("<div>loading</div>"));

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

	public SafeHtml getLoadingHTML() {
		return loadingHTML;
	}

	public void setLoadingHTML(SafeHtml loadingHTML) {
		this.loadingHTML = loadingHTML;
	}

	public SafeHtml getLoadingFailedHTML() {
		return loadingFailedHTML;
	}

	public void setLoadingFailedHTML(SafeHtml loadingFailedHTML) {
		this.loadingFailedHTML = loadingFailedHTML;
	}

	protected void updateText() {
		switch (getState()) {
		case NO_ACTION:
			header.setHTML(noReloadHTML.asString());
			break;
		case PULL_RELEASE:
			header.setHTML(reloadHTML.asString());
			break;
		}
	}

	public void onLoadingSucceeded() {
		showHeader(false);
		refresh();
	}

	public void onLoadingFailed() {
		showHeader(true);
		getHeader().showError();
		header.setHTML(loadingFailedHTML.asString());
	}

	private class InternalPullListener implements PullStateChangedHandler, PullReleasedHandler {

		@Override
		public void onPullReleased(PullReleasedEvent event) {
			header.setHTML(loadingHTML.asString());

		}

		@Override
		public void onPullStateChanged(PullStateChangedEvent event) {
			updateText();

		}
	}

	protected PullArrowHeader getHeader() {
		return (PullArrowHeader) header;
	}

}
