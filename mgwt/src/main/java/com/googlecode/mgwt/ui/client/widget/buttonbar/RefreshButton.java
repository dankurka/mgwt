package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.RefreshButtonCss;

public class RefreshButton extends ButtonBarButtonBase {

	public RefreshButton() {
		this(MGWTStyle.getDefaultClientBundle().getRefreshButtonCss());
	}

	public RefreshButton(RefreshButtonCss refreshButtonCss) {
		super(refreshButtonCss);
		addStyleName(refreshButtonCss.refresh());
	}

}
