package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowLeftButtonCss;

public class ArrowLeftButton extends ButtonBarButtonBase {

	public ArrowLeftButton() {
		this(MGWTStyle.getDefaultClientBundle().getLeftButtonCss());
	}

	public ArrowLeftButton(ArrowLeftButtonCss arrowLeftCss) {
		super(arrowLeftCss);
		addStyleName(arrowLeftCss.arrowLeft());
	}

}
