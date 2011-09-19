package de.kurka.gwt.mobile.ui.client.widget;

import com.google.gwt.user.client.ui.IntegerBox;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;
import de.kurka.gwt.mobile.ui.client.widget.base.MValueBoxBase;

public class MIntegerBox extends MValueBoxBase<Integer> {

	public MIntegerBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());
	}

	public MIntegerBox(InputCss css) {
		super(css, new IntegerBox());
		addStyleName(css.textBox());
	}

}
