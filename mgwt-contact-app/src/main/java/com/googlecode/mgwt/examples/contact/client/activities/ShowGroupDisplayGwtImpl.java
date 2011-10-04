package com.googlecode.mgwt.examples.contact.client.activities;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.touch.simple.SimpleTouchEvent;
import com.googlecode.mgwt.examples.contact.client.module.Group;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public class ShowGroupDisplayGwtImpl extends Composite implements ShowGroupDisplay {

	private static ShowGroupDisplayGwtImplUiBinder uiBinder = GWT.create(ShowGroupDisplayGwtImplUiBinder.class);

	private Driver driver = GWT.create(Driver.class);

	@UiField
	MTextBox name;

	@UiField
	Button editButton;

	@UiField
	HasText center;

	private Presenter presenter;

	interface ShowGroupDisplayGwtImplUiBinder extends UiBinder<Widget, ShowGroupDisplayGwtImpl> {
	}

	interface Driver extends SimpleBeanEditorDriver<Group, ShowGroupDisplayGwtImpl> {
	}

	public ShowGroupDisplayGwtImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		driver.initialize(this);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;

	}

	@Override
	public void edit(Group group) {
		driver.edit(group);
		center.setText(group.getName());

	}

	@UiHandler("editButton")
	protected void onEditButon(SimpleTouchEvent event) {
		if (presenter != null) {
			Group group = driver.flush();
			presenter.onEditComplete(group);
		}
	}

}
