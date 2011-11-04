package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowDownButtonCss;

public class ArrowDownButton extends ButtonBarButtonBase {

	public ArrowDownButton() {
		this(MGWTStyle.getDefaultClientBundle().getArrowDownButtonCss());
	}

	public ArrowDownButton(ArrowDownButtonCss arrowDownButtonCss) {
		super(arrowDownButtonCss);
		addStyleName(arrowDownButtonCss.arrowDown());
	}

}
