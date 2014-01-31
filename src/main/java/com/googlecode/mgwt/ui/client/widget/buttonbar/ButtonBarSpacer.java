package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class ButtonBarSpacer extends TouchWidget {


	protected ButtonBarAppearance appearance;

  public ButtonBarSpacer() {
		this(ButtonBar.DEFAULT_APPEARANCE);
	}

	public ButtonBarSpacer(ButtonBarAppearance appearance) {
	  this.appearance = appearance;
    setElement(appearance.barSpacer().createAndBindUi(this));
//		setElement(Document.get().createDivElement());
//		this.css = css;
//
//		this.css.ensureInjected();
//
//		addStyleName(css.spacer());
	}
}
