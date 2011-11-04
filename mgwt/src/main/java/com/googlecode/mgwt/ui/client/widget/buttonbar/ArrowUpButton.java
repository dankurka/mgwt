package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowUpButtonCss;

public class ArrowUpButton extends ButtonBarButtonBase {

	public ArrowUpButton() {
		this(MGWTStyle.getDefaultClientBundle().getArrowUpButtonCss());
	}

	public ArrowUpButton(ArrowUpButtonCss arrowUpButtonCss) {
		super(arrowUpButtonCss);
		addStyleName(arrowUpButtonCss.arrowUp());
	}

}
