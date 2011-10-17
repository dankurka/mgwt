package com.googlecode.mgwt.examples.showcase.client.activities.pulltorefresh;

import java.util.List;

import com.googlecode.mgwt.examples.showcase.client.BasicCell;
import com.googlecode.mgwt.examples.showcase.client.DetailViewGwtImpl;
import com.googlecode.mgwt.examples.showcase.client.activities.home.Topic;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.PullToRefresh;
import com.googlecode.mgwt.ui.client.widget.event.HasPullHandlers;

public class PullToRefreshDisplayGwtImpl extends DetailViewGwtImpl implements PullToRefreshDisplay {

	private PullToRefresh pullToRefresh;
	private CellList<Topic> cellList;

	public PullToRefreshDisplayGwtImpl() {
		main.remove(scrollPanel);

		pullToRefresh = new PullToRefresh();

		main.add(pullToRefresh);

		cellList = new CellList<Topic>(new BasicCell<Topic>() {

			@Override
			public String getDisplayString(Topic model) {
				return model.getName();
			}
		});

		pullToRefresh.add(cellList);

	}

	@Override
	public HasPullHandlers getReload() {
		return pullToRefresh;
	}

	@Override
	public void render(List<Topic> topics) {
		cellList.render(topics);

	}

	@Override
	public void onLoadingSucceeded() {
		pullToRefresh.onLoadingSucceeded();

	}

	@Override
	public void onLoadingFailed() {
		pullToRefresh.onLoadingFailed();

	}

}
