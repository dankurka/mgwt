package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.InfoButtonCss;

public class InfoButton extends ButtonBarButtonBase {

	public InfoButton() {
		this(MGWTStyle.getDefaultClientBundle().getInfoButtonCss());
	}

	public InfoButton(InfoButtonCss infoButtonCss) {
		super(infoButtonCss);
		addStyleName(infoButtonCss.info());
	}

}
