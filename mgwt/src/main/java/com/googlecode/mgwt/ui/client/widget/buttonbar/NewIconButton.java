package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.NewIconButtonCss;

public class NewIconButton extends ButtonBarButtonBase {

	public NewIconButton() {
		this(MGWTStyle.getDefaultClientBundle().getNewIconButtonCss());
	}

	public NewIconButton(NewIconButtonCss newIconButtonCss) {
		super(newIconButtonCss);
		addStyleName(newIconButtonCss.newIcon());
	}

}
