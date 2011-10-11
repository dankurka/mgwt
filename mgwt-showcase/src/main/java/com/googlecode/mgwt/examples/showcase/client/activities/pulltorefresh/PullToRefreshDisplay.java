package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import java.util.List;

import com.google.gwt.user.client.ui.HasHTML;
import com.googlecode.mgwt.examples.showcase.client.DetailView;
import com.googlecode.mgwt.examples.showcase.client.activities.home.Topic;
import com.googlecode.mgwt.ui.client.widget.event.HasReloadHandlers;
import com.googlecode.mgwt.ui.client.widget.event.HasReloadStateChangeHandlers;

public interface PullToRefreshDisplay extends DetailView {
	public HasReloadHandlers getReload();

	public HasReloadStateChangeHandlers getReloadState();

	public void setLoading(boolean b);

	public void reload();

	public void render(List<Topic> topics);

	public HasHTML getTextHeader();

}
