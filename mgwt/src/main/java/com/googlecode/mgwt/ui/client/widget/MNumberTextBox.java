package com.googlecode.mgwt.ui.client.widget;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;

public class MNumberTextBox extends MTextBox {

	public MNumberTextBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());

	}

	public MNumberTextBox(InputCss css) {
		super(css);
		box.getElement().setPropertyString("type", "number");
	}

}
