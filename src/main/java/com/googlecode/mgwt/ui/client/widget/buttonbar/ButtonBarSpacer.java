package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.user.client.DOM;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class ButtonBarSpacer extends TouchWidget {

	private final ButtonBarCss css;

	public ButtonBarSpacer() {
		this(MGWTStyle.getDefaultClientBundle().getButtonBarCss());
	}

	public ButtonBarSpacer(ButtonBarCss css) {
		setElement(DOM.createDiv());
		this.css = css;

		this.css.ensureInjected();

		addStyleName(css.spacer());
	}
}
