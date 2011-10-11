package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import com.google.gwt.user.client.ui.HasHTML;
import com.googlecode.mgwt.examples.showcase.client.DetailViewGwtImpl;
import com.googlecode.mgwt.ui.client.widget.PullToRefresh;
import com.googlecode.mgwt.ui.client.widget.event.HasReloadHandlers;
import com.googlecode.mgwt.ui.client.widget.event.HasReloadStateChangeHandlers;

public class PullToRefreshDisplayGwtImpl extends DetailViewGwtImpl implements PullToRefreshDisplay {

	private PullToRefresh pullToRefresh;

	public PullToRefreshDisplayGwtImpl() {
		main.remove(scrollPanel);

		pullToRefresh = new PullToRefresh();

		main.add(pullToRefresh);
	}

	@Override
	public HasReloadHandlers getReload() {
		return pullToRefresh;
	}

	@Override
	public HasHTML getHeader() {
		return pullToRefresh.getHeader();
	}

	@Override
	public HasHTML getPullText() {
		return pullToRefresh.getText();
	}

	@Override
	public HasReloadStateChangeHandlers getReloadState() {
		return pullToRefresh;
	}

	@Override
	public void setLoading(boolean b) {
		pullToRefresh.setLoading(b);

	}
}
