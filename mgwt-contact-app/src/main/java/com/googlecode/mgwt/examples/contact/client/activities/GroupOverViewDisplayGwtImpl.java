package com.googlecode.mgwt.examples.contact.client.activities;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.examples.contact.client.GroupCell;
import com.googlecode.mgwt.examples.contact.client.module.Group;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;

public class GroupOverViewDisplayGwtImpl extends Composite implements GroupOverViewDisplay {

	private static GroupOverViewDisplayGwtImplUiBinder uiBinder = GWT.create(GroupOverViewDisplayGwtImplUiBinder.class);

	interface GroupOverViewDisplayGwtImplUiBinder extends UiBinder<Widget, GroupOverViewDisplayGwtImpl> {
	}

	@UiField(provided = true)
	CellList<Group> list;

	@UiField
	ButtonBase plusButton;

	@UiField
	ButtonBase editButton;

	private GroupOverViewPresenter presenter;

	private GroupCell groupCell;

	public GroupOverViewDisplayGwtImpl() {
		groupCell = new GroupCell();
		list = new CellList<Group>(groupCell);
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public void renderTopics(List<Group> topicList) {
		list.render(topicList);
	}

	@Override
	public HasTapHandlers getPlusButton() {
		return plusButton;
	}

	@UiHandler("plusButton")
	public void onSimpleTouch(TapEvent event) {
		if (presenter != null) {
			presenter.onPlusButton();
		}
	}

	@UiHandler("editButton")
	public void onEditButton(TapEvent event) {
		if (presenter != null) {
			presenter.onEditButton();
		}
	}

	@UiHandler("list")
	public void onCellSelected(CellSelectedEvent event) {
		int index = event.getIndex();
		if (presenter != null) {
			presenter.onListItemSelected(index);
		}
	}

	@Override
	public void setPresenter(GroupOverViewPresenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void setSelected(int index) {

		list.setSelectedIndex(index, true);

	}

	@Override
	public void setSelected(int oldIndex, boolean b) {
		list.setSelectedIndex(oldIndex, b);

	}

	@Override
	public void setEdit(boolean edit) {
		groupCell.setEdit(edit);

	}
}
