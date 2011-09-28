package com.googlecode.mgwt.ui.client.widget;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;

public class MPhoneNumberTextBox extends MTextBox {

	public MPhoneNumberTextBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());

	}

	public MPhoneNumberTextBox(InputCss css) {
		super(css);
		box.getElement().setPropertyString("type", "phone");
	}

}