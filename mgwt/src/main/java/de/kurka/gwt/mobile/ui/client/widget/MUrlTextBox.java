package de.kurka.gwt.mobile.ui.client.widget;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;

public class MUrlTextBox extends MTextBox {
	public MUrlTextBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());

	}

	public MUrlTextBox(InputCss css) {
		super(css);
		box.getElement().setPropertyString("type", "url");
	}
}
