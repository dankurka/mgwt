package de.kurka.gwt.mobile.ui.client.widget;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;

public class MPhoneNumberTextBox extends MTextBox {

	public MPhoneNumberTextBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());

	}

	public MPhoneNumberTextBox(InputCss css) {
		super(css);
		box.getElement().setPropertyString("type", "phone");
	}

}