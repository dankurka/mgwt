package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.PreviousSlideButtonCss;

public class PreviousSlideButton extends ButtonBarButtonBase {

	public PreviousSlideButton() {
		this(MGWTStyle.getDefaultClientBundle().getPreviousSlideButtonCss());
	}

	public PreviousSlideButton(PreviousSlideButtonCss previousSlideButtonCss) {
		super(previousSlideButtonCss);
		addStyleName(previousSlideButtonCss.previousSlide());
	}

}
