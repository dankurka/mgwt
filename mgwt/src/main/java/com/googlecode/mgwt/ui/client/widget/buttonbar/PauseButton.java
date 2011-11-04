package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.PauseButtonCss;

public class PauseButton extends ButtonBarButtonBase {

	public PauseButton() {
		this(MGWTStyle.getDefaultClientBundle().getPauseButtonCss());
	}

	public PauseButton(PauseButtonCss pauseButtonCss) {
		super(pauseButtonCss);
		addStyleName(pauseButtonCss.pause());
	}

}
