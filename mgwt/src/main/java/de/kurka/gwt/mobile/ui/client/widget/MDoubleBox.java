package de.kurka.gwt.mobile.ui.client.widget;

import com.google.gwt.user.client.ui.DoubleBox;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;
import de.kurka.gwt.mobile.ui.client.widget.base.MValueBoxBase;

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
