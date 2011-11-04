package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.PlusButtonCss;

public class PlusButton extends ButtonBarButtonBase {

	public PlusButton() {
		this(MGWTStyle.getDefaultClientBundle().getPlusButtonCss());
	}

	public PlusButton(PlusButtonCss css) {
		super(css);
		addStyleName(css.plus());
	}

}
