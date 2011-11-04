package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.PlayButtonCss;

public class PlayButton extends ButtonBarButtonBase {

	public PlayButton() {
		this(MGWTStyle.getDefaultClientBundle().getPlayButtonCss());
	}

	public PlayButton(PlayButtonCss playButtonCss) {
		super(playButtonCss);
		addStyleName(playButtonCss.play());
	}

}
