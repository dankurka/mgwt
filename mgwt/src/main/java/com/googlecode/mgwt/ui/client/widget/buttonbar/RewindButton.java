package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.RewindButtonCss;

public class RewindButton extends ButtonBarButtonBase {

	public RewindButton() {
		this(MGWTStyle.getDefaultClientBundle().getRewindButtonCss());
	}

	public RewindButton(RewindButtonCss rewindButtonCss) {
		super(rewindButtonCss);
		addStyleName(rewindButtonCss.rewind());
	}

}
