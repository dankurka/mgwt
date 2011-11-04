package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ArrowRightButtonCss;

public class ArrowRightButton extends ButtonBarButtonBase {

	public ArrowRightButton() {
		this(MGWTStyle.getDefaultClientBundle().getArrowRightButtonCss());
	}

	public ArrowRightButton(ArrowRightButtonCss arrowRightButtonCss) {
		super(arrowRightButtonCss);
		addStyleName(arrowRightButtonCss.arrowRight());
	}

}
