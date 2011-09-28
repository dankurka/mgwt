package com.googlecode.mgwt.examples.contact.client.activities;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.examples.contact.client.BasicCell;
import com.googlecode.mgwt.examples.contact.client.Topic;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.HeaderRoundButton;


public class OverviewDisplayGwtImpl extends Composite implements OverviewDisplay {

	private static OverviewDisplayGwtImplUiBinder uiBinder = GWT.create(OverviewDisplayGwtImplUiBinder.class);

	interface OverviewDisplayGwtImplUiBinder extends UiBinder<Widget, OverviewDisplayGwtImpl> {
	}

	@UiField(provided = true)
	CellList<Topic> list;

	@UiField
	HeaderRoundButton plusButton;

	private OverviewPresenter presenter;

	public OverviewDisplayGwtImpl() {
		list = new CellList<Topic>(new BasicCell<Topic>() {

			@Override
			public String getDisplayString(Topic model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(Topic model) {
				return true;
			}
		}

		);

		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public void renderTopics(List<Topic> topicList) {
		list.render(topicList);
	}

	@Override
	public HasSimpleTouchHandler getPlusButton() {
		return plusButton;
	}

	@UiHandler("plusButton")
	public void onSimpleTouch(SimpleTouchEvent event) {
		if (presenter != null) {
			presenter.onPlusButton();
		}
	}

	@Override
	public void setPresenter(OverviewPresenter presenter) {
		this.presenter = presenter;

	}
}
