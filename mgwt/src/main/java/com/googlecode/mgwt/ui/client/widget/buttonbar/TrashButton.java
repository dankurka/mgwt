package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.TrashButtonCss;

public class TrashButton extends ButtonBarButtonBase {

	public TrashButton() {
		this(MGWTStyle.getDefaultClientBundle().getTrashButtonCss());
	}

	public TrashButton(TrashButtonCss trashButtonCss) {
		super(trashButtonCss);
		addStyleName(trashButtonCss.trash());
	}

}
