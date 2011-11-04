package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.DeleteButtonCss;

public class DeleteButton extends ButtonBarButtonBase {

	public DeleteButton() {
		this(MGWTStyle.getDefaultClientBundle().getDeleteButtonCss());
	}

	public DeleteButton(DeleteButtonCss deleteButtonCss) {
		super(deleteButtonCss);
		addStyleName(deleteButtonCss.delete());
	}

}
