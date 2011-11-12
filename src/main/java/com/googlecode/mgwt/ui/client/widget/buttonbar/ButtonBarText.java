package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasHTML;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class ButtonBarText extends TouchWidget implements HasHTML {
	private final ButtonBarCss css;

	public ButtonBarText() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getButtonBarCss(), "");
	}

	public ButtonBarText(String html) {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getButtonBarCss(), html);
	}

	public ButtonBarText(ButtonBarCss css, String html) {
		this.css = css;
		setElement(DOM.createDiv());

		this.css.ensureInjected();

		this.addStyleName(css.text());
		setHTML(html);
	}

	@Override
	public String getText() {
		return getElement().getInnerText();
	}

	@Override
	public void setText(String text) {
		getElement().setInnerText(text);

	}

	@Override
	public String getHTML() {
		return getElement().getInnerHTML();
	}

	@Override
	public void setHTML(String html) {
		getElement().setInnerHTML(html);

	}

}
