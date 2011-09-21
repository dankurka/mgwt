package de.kurka.gwt.mobile.ui.client.widget;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;

public class MNumberTextBox extends MTextBox {

	public MNumberTextBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());

	}

	public MNumberTextBox(InputCss css) {
		super(css);
		box.getElement().setPropertyString("type", "number");
	}

}
