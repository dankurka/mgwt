/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.button.ButtonBase;
import com.googlecode.mgwt.ui.client.theme.base.ButtonCss;

/**
 * @author Daniel Kurka
 * 
 */
public class Button extends ButtonBase {

	private boolean round;
	private boolean small;
	protected final ButtonCss css;

	public Button() {

		this(MGWTStyle.getDefaultClientBundle().getButtonCss());

	}

	/**
	 * @param text
	 */
	public Button(String text) {
		this(MGWTStyle.getDefaultClientBundle().getButtonCss(), text);

	}

	public Button(ButtonCss css) {
		this(css, "");
	}

	public Button(ButtonCss css, String text) {
		super(css);
		this.css = css;
		css.ensureInjected();
		addStyleName(css.button());
		setText(text);
	}

	public boolean isRound() {
		return round;
	}

	public void setRound(boolean round) {
		if (round) {
			addStyleName(css.round());
		} else {
			removeStyleName(css.round());
		}
		this.round = round;
	}

	public void setSmall(boolean small) {
		if (small) {
			addStyleName(css.small());
		} else {
			removeStyleName(css.small());
		}
		this.small = small;
	}

	/**
	 * @return the small
	 */
	public boolean isSmall() {
		return small;
	}

	private boolean important;

	/**
	 * @return the important
	 */
	public boolean isImportant() {
		return important;
	}

	/**
	 * @param important
	 *            the important to set
	 */
	public void setImportant(boolean important) {
		if (important) {
			addStyleName(css.important());
		} else {
			removeStyleName(css.important());
		}
		this.important = important;
	}

	private boolean confirm;

	/**
	 * @return the confirm
	 */
	public boolean isConfirm() {
		return confirm;
	}

	/**
	 * @param confirm
	 *            the confirm to set
	 */
	public void setConfirm(boolean confirm) {
		if (confirm) {
			addStyleName(css.confirm());
		} else {
			removeStyleName(css.confirm());
		}
		this.confirm = confirm;
	}
}
