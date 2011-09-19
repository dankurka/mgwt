package de.kurka.mobile.contact.client.activities;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.widget.CellList;
import de.kurka.gwt.mobile.ui.client.widget.HeaderRoundButton;
import de.kurka.mobile.contact.client.BasicCell;
import de.kurka.mobile.contact.client.Topic;

public class OverviewDisplayGwtImpl extends Composite implements OverviewDisplay {

	private static OverviewDisplayGwtImplUiBinder uiBinder = GWT.create(OverviewDisplayGwtImplUiBinder.class);

	interface OverviewDisplayGwtImplUiBinder extends UiBinder<Widget, OverviewDisplayGwtImpl> {
	}

	@UiField(provided = true)
	CellList<Topic> list;

	@UiField
	HeaderRoundButton plusButton;

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
}
