package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.NextSlideButtonCss;

public class NextSlideButton extends ButtonBarButtonBase {

	public NextSlideButton() {
		this(MGWTStyle.getDefaultClientBundle().getNextSlideButtonCss());
	}

	public NextSlideButton(NextSlideButtonCss nextSlideButtonCss) {
		super(nextSlideButtonCss);
		addStyleName(nextSlideButtonCss.nextSlide());
	}

}
