package de.kurka.gwt.mobile.ui.client.widget;

import com.google.gwt.user.client.ui.LongBox;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;
import de.kurka.gwt.mobile.ui.client.widget.base.MValueBoxBase;

public class MLongBox extends MValueBoxBase<Long> {

	public MLongBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());
	}

	public MLongBox(InputCss css) {
		super(css, new LongBox());
		addStyleName(css.textBox());
	}

}
