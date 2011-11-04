package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ActionButtonCss;

public class ActionButton extends ButtonBarButtonBase {

	public ActionButton() {
		this(MGWTStyle.getDefaultClientBundle().getActionButtonCss());
	}

	public ActionButton(ActionButtonCss css) {
		super(css);
		addStyleName(css.action());
	}

}
