package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.user.client.ui.DoubleBox;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.widget.base.MValueBoxBase;


public class MDoubleBox extends MValueBoxBase<Double> {

	public MDoubleBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());
	}

	public MDoubleBox(InputCss css) {
		super(css, new DoubleBox());
		box.getElement().setPropertyString("type", "number");
		addStyleName(css.textBox());
	}

}
