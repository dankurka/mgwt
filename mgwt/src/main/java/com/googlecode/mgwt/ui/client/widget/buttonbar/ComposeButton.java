package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.ComposeButtonCss;

public class ComposeButton extends ButtonBarButtonBase {

	public ComposeButton() {
		this(MGWTStyle.getDefaultClientBundle().getComposeButtonCss());
	}

	public ComposeButton(ComposeButtonCss composeButtonCss) {
		super(composeButtonCss);
		addStyleName(composeButtonCss.compose());
	}

}
