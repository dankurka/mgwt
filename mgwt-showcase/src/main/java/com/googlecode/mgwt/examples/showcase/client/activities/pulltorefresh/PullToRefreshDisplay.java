package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import com.google.gwt.user.client.ui.HasHTML;
import com.googlecode.mgwt.examples.showcase.client.DetailView;
import com.googlecode.mgwt.ui.client.widget.event.HasReloadHandlers;
import com.googlecode.mgwt.ui.client.widget.event.HasReloadStateChangeHandlers;

public interface PullToRefreshDisplay extends DetailView {
	public HasReloadHandlers getReload();

	public HasReloadStateChangeHandlers getReloadState();

	public HasHTML getHeader();

	public HasHTML getPullText();

	public void setLoading(boolean b);
}
