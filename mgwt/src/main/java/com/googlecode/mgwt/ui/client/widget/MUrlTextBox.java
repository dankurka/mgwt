package com.googlecode.mgwt.ui.client.widget;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;

public class MUrlTextBox extends MTextBox {
	public MUrlTextBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());

	}

	public MUrlTextBox(InputCss css) {
		super(css);
		box.getElement().setPropertyString("type", "url");
	}
}
