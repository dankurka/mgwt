package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.SearchButtonCss;

public class SearchButton extends ButtonBarButtonBase {

	public SearchButton() {
		this(MGWTStyle.getDefaultClientBundle().getSearchButtonCss());
	}

	public SearchButton(SearchButtonCss searchButtonCss) {
		super(searchButtonCss);
		addStyleName(searchButtonCss.search());
	}

}
