package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.user.client.ui.IntegerBox;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.widget.base.MValueBoxBase;


public class MIntegerBox extends MValueBoxBase<Integer> {

	public MIntegerBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());
	}

	public MIntegerBox(InputCss css) {
		super(css, new IntegerBox());
		box.getElement().setPropertyString("type", "number");
		addStyleName(css.textBox());

	}

}
