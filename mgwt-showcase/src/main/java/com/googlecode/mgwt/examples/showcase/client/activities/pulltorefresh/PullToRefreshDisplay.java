package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import java.util.List;

import com.googlecode.mgwt.examples.showcase.client.DetailView;
import com.googlecode.mgwt.examples.showcase.client.activities.home.Topic;
import com.googlecode.mgwt.ui.client.widget.event.HasPullHandlers;

public interface PullToRefreshDisplay extends DetailView {
	public HasPullHandlers getReload();

	public void onLoadingSucceeded();

	public void onLoadingFailed();

	public void render(List<Topic> topics);

}
