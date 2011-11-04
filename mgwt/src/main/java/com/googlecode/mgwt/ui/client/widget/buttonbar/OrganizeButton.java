package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.OrganizeButtonCss;

public class OrganizeButton extends ButtonBarButtonBase {

	public OrganizeButton() {
		this(MGWTStyle.getDefaultClientBundle().getOrganizeButtonCss());
	}

	public OrganizeButton(OrganizeButtonCss organizeButtonCss) {
		super(organizeButtonCss);
		addStyleName(organizeButtonCss.organize());
	}

}
