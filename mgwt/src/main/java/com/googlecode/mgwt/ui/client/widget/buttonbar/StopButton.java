package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.StopButtonCss;

public class StopButton extends ButtonBarButtonBase {

	public StopButton() {
		this(MGWTStyle.getDefaultClientBundle().getStopButtonCss());
	}

	public StopButton(StopButtonCss stopButtonCss) {
		super(stopButtonCss);
		addStyleName(stopButtonCss.stop());
	}

}
