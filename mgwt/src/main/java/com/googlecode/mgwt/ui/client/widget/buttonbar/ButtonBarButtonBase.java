package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ButtonBarButtonBaseCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

public class ButtonBarButtonBase extends ButtonBase {

	public ButtonBarButtonBase(ButtonBarButtonBaseCss css) {
		super(css);
		addStyleName(css.barButton());
	}

}
