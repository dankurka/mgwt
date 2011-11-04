package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.AddContactButtonCss;

public class AddContactButton extends ButtonBarButtonBase {

	public AddContactButton() {
		this(MGWTStyle.getDefaultClientBundle().getAddContactButtonCss());
	}

	public AddContactButton(AddContactButtonCss addContactButtonCss) {
		super(addContactButtonCss);
		addStyleName(addContactButtonCss.contactAdd());
	}

}
