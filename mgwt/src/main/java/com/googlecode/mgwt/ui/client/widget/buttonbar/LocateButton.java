package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.LocateButtonCss;

public class LocateButton extends ButtonBarButtonBase {

	public LocateButton() {
		this(MGWTStyle.getDefaultClientBundle().getLocateButtonCss());
	}

	public LocateButton(LocateButtonCss locateButtonCss) {
		super(locateButtonCss);
		addStyleName(locateButtonCss.locate());
	}

}
