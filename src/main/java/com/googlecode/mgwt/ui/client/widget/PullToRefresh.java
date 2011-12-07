package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.PullToRefreshCss;
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
 * @version $Id: $
 */
public class PullToRefresh extends PullPanel {

	private SafeHtml noReloadHTML;
	private SafeHtml reloadHTML;
	private SafeHtml loadingHTML;

	private SafeHtml successHTML;

	private SafeHtml loadingFailedHTML;

	public PullToRefresh() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getPullToRefreshCss());
	}

	public PullToRefresh(PullToRefreshCss css) {
		super(new PullArrowHeader(css));
		InternalPullListener listener = new InternalPullListener();

		addPullReleasedHandler(listener);
		addPullStateChangedHandler(listener);

		setNoReloadHTML(SafeHtmlUtils.fromTrustedString("<div>pull down</div>"));
		setReloadHTML(SafeHtmlUtils.fromTrustedString("<div>release to reload</div>"));

		setLoadingFailedHTML(SafeHtmlUtils.fromTrustedString("<div>failed to load</div>"));
		setLoadingHTML(SafeHtmlUtils.fromTrustedString("<div>loading</div>"));

		setSuccessHTML(SafeHtmlUtils.fromTrustedString("<div> loading done</div>"));

		updateText();
	}

	/**
	 * <p>
	 * Setter for the field <code>noReloadHTML</code>.
	 * </p>
	 * 
	 * @param html
	 *            a {@link com.google.gwt.safehtml.shared.SafeHtml} object.
	 */
	public void setNoReloadHTML(SafeHtml html) {
		if (html == null) {
			throw new IllegalArgumentException("html can not be null");
		}
		this.noReloadHTML = html;
		updateText();
	}

	public void setSuccessHTML(SafeHtml successHTML) {
		this.successHTML = successHTML;
	}

	/**
	 * <p>
	 * Setter for the field <code>reloadHTML</code>.
	 * </p>
	 * 
	 * @param html
	 *            a {@link com.google.gwt.safehtml.shared.SafeHtml} object.
	 */
	public void setReloadHTML(SafeHtml html) {
		if (html == null) {
			throw new IllegalArgumentException("html can not be null");
		}
		this.reloadHTML = html;
		updateText();
	}

	/**
	 * <p>
	 * Getter for the field <code>noReloadHTML</code>.
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.safehtml.shared.SafeHtml} object.
	 */
	public SafeHtml getNoReloadHTML() {
		return noReloadHTML;
	}

	/**
	 * <p>
	 * Getter for the field <code>reloadHTML</code>.
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.safehtml.shared.SafeHtml} object.
	 */
	public SafeHtml getReloadHTML() {
		return reloadHTML;
	}

	/**
	 * <p>
	 * Getter for the field <code>loadingHTML</code>.
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.safehtml.shared.SafeHtml} object.
	 */
	public SafeHtml getLoadingHTML() {
		return loadingHTML;
	}

	/**
	 * <p>
	 * Setter for the field <code>loadingHTML</code>.
	 * </p>
	 * 
	 * @param loadingHTML
	 *            a {@link com.google.gwt.safehtml.shared.SafeHtml} object.
	 */
	public void setLoadingHTML(SafeHtml loadingHTML) {
		this.loadingHTML = loadingHTML;
	}

	/**
	 * <p>
	 * Getter for the field <code>loadingFailedHTML</code>.
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.safehtml.shared.SafeHtml} object.
	 */
	public SafeHtml getLoadingFailedHTML() {
		return loadingFailedHTML;
	}

	/**
	 * <p>
	 * Setter for the field <code>loadingFailedHTML</code>.
	 * </p>
	 * 
	 * @param loadingFailedHTML
	 *            a {@link com.google.gwt.safehtml.shared.SafeHtml} object.
	 */
	public void setLoadingFailedHTML(SafeHtml loadingFailedHTML) {
		this.loadingFailedHTML = loadingFailedHTML;
	}

	/**
	 * <p>
	 * updateText
	 * </p>
	 */
	protected void updateText() {
		switch (getState()) {
		case NO_ACTION:
			header.setHTML(noReloadHTML.asString());
			break;
		case PULL_RELEASE:
			header.setHTML(reloadHTML.asString());
			break;
		default:
			throw new RuntimeException("how did we get here?");
		}

	}

	/**
	 * <p>
	 * onLoadingSucceeded
	 * </p>
	 */
	public void onLoadingSucceeded() {
		if (isAutoHideHeader()) {
			showHeader(false);

		}
		header.setHTML(successHTML.asString());
		getHeader().showSuccess();
		refresh();
	}

	/**
	 * <p>
	 * onLoadingFailed
	 * </p>
	 */
	public void onLoadingFailed() {
		showHeader(true);
		getHeader().showError();
		header.setHTML(loadingFailedHTML.asString());
		refresh();

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

	/**
	 * <p>
	 * getHeader
	 * </p>
	 * 
	 * @return a
	 *         {@link com.googlecode.mgwt.ui.client.widget.base.PullArrowHeader}
	 *         object.
	 */
	protected PullArrowHeader getHeader() {
		return (PullArrowHeader) header;
	}

}
