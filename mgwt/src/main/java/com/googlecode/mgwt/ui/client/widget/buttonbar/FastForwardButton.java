package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.FastForwardButtonCss;

public class FastForwardButton extends ButtonBarButtonBase {

	public FastForwardButton() {
		this(MGWTStyle.getDefaultClientBundle().getFastForwardButtonCss());
	}

	public FastForwardButton(FastForwardButtonCss fastForwardButtonCss) {
		super(fastForwardButtonCss);
		addStyleName(fastForwardButtonCss.fastForward());
	}

}
