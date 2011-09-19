package de.kurka.mobile.contact.client.activities;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AddGroupDisplayGwtImpl extends Composite implements AddGroupDisplay {

	private static AddGroupDisplayGwtImplUiBinder uiBinder = GWT.create(AddGroupDisplayGwtImplUiBinder.class);

	interface AddGroupDisplayGwtImplUiBinder extends UiBinder<Widget, AddGroupDisplayGwtImpl> {
	}

	public AddGroupDisplayGwtImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
