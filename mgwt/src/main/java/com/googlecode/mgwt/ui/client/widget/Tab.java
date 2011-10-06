package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Experimental to get TabPanel work with UIBinder
 * 
 * at the moment theres no support for custom parsers:
 * http://code.google.com/p/google-web-toolkit/issues/detail?id=4461
 * 
 * @author Daniel Kurka
 * 
 */
public class Tab extends Widget {
	private Widget widget;
	private TabBarButton button;

	public Tab() {
		setElement(DOM.createDiv());
	}

	@UiChild(limit = 1, tagname = "button")
	public void setButton(TabBarButton button) {
		this.button = button;

	}

	@UiChild(limit = 1, tagname = "widget")
	public void setWidget(Widget w) {
		this.widget = w;

	}

	public Widget getWidget() {
		return widget;
	}

	public TabBarButton getButton() {
		return button;
	}
}
