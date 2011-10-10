package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import com.googlecode.mgwt.examples.showcase.client.DetailViewGwtImpl;
import com.googlecode.mgwt.ui.client.widget.PullToRefresh;

public class PullToRefreshDisplayGwtImpl extends DetailViewGwtImpl implements PullToRefreshDisplay {

	public PullToRefreshDisplayGwtImpl() {
		main.remove(scrollPanel);

		PullToRefresh pullToRefresh = new PullToRefresh();

		main.add(pullToRefresh);
	}
}
