package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.user.client.ui.LongBox;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.widget.base.MValueBoxBase;


public class MLongBox extends MValueBoxBase<Long> {

	public MLongBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());
	}

	public MLongBox(InputCss css) {
		super(css, new LongBox());
		box.getElement().setPropertyString("type", "number");
		addStyleName(css.textBox());
	}

}
