package de.kurka.gwt.mobile.ui.client.widget;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;

public class MEmailTextBox extends MTextBox {

	public MEmailTextBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());

	}

	public MEmailTextBox(InputCss css) {
		super(css);
		box.getElement().setPropertyString("type", "email");
	}

}
