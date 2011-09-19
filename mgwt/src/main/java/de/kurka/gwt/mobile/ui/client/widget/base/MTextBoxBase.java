package de.kurka.gwt.mobile.ui.client.widget.base;

import com.google.gwt.user.client.ui.ValueBoxBase;

import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;

public class MTextBoxBase extends MValueBoxBase<String> {

	protected MTextBoxBase(InputCss css, ValueBoxBase<String> box) {
		super(css, box);
	}

	@Override
	public String getValue() {
		String raw = super.getValue();
		return raw == null ? "" : raw;
	}

}
