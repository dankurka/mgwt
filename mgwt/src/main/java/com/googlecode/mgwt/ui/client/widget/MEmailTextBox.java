package com.googlecode.mgwt.ui.client.widget;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;

public class MEmailTextBox extends MTextBox {

	public MEmailTextBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());

	}

	public MEmailTextBox(InputCss css) {
		super(css);
		box.getElement().setPropertyString("type", "email");
	}

}
