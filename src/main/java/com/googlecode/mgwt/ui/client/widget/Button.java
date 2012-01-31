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
import com.googlecode.mgwt.ui.client.theme.base.ButtonCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

/**
 * A simple button class
 * 
 * <h2>Styling notes:</h2> The button consists of a simple div element like
 * this:
 * 
 * <pre>
 * &lt;div class="mgwt-Button">ButtonText&lt;/div>
 * </pre>
 * 
 * The following classes are added as needed:
 * 
 * <ul>
 * <li>.mgwt-Button-active - Button is pressed</li>
 * <li>.mgwt-Button-small - Button should be rendered small</li>
 * <li>.mgwt-Button-confirm - Button should be rendered as a confirm button
 * (e.g. green on iOS)</li>
 * <li>.mgwt-Button-important - Button should be rendered as important (e.g. red
 * on iOS)</li>
 * <li>.mgwt-Button-round - Button should be rendered with rounded corners</li>
 * </ul>
 * 
 * 
 * 
 * @author Daniel Kurka
 */
public class Button extends ButtonBase {

	private boolean round;
	private boolean small;
	private boolean confirm;
	protected final ButtonCss css;

	/**
	 * Construct a button
	 */
	public Button() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getButtonCss());
	}

	/**
	 * Construct a button with a given text
	 * 
	 * @param text the text to use in the button
	 */
	public Button(String text) {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getButtonCss(), text);

	}

	/**
	 * Construct a button with a given css
	 * 
	 * @param css the css to use for this button
	 */
	public Button(ButtonCss css) {
		this(css, "");
	}

	/**
	 * Construct a button with a given css and text
	 * 
	 * @param css the css to use
	 * @param text the text to use
	 */
	public Button(ButtonCss css, String text) {
		super(css);
		this.css = css;
		css.ensureInjected();
		addStyleName(css.button());
		setText(text);
	}

	/**
	 * Should the button have rounded corners
	 * 
	 * @return true if the button has rounded corners, otherwise false
	 */
	public boolean isRound() {
		return round;
	}

	/**
	 * Should the button have rounded corners
	 * 
	 * @param round true if the button should have rounded corners, otherwise
	 *            false
	 */
	public void setRound(boolean round) {
		if (round) {
			addStyleName(css.round());
		} else {
			removeStyleName(css.round());
		}
		this.round = round;
	}

	/**
	 * Should the button be rendered small
	 * 
	 * @param small true if the button should be rendered small, otherwise false
	 */
	public void setSmall(boolean small) {
		if (small) {
			addStyleName(css.small());
		} else {
			removeStyleName(css.small());
		}
		this.small = small;
	}

	/**
	 * Should the button be rendered small
	 * 
	 * @return true if the button should be rendered small, otherwise false
	 */
	public boolean isSmall() {
		return small;
	}

	private boolean important;

	/**
	 * Should the button be rendered as important
	 * 
	 * @return true if the button should be rendered as important, otherwise
	 *         false
	 */
	public boolean isImportant() {
		return important;
	}

	/**
	 * Should the button be rendered as important
	 * 
	 * @param important true if the button should be rendered as important,
	 *            otherwise false
	 */
	public void setImportant(boolean important) {
		if (important) {
			addStyleName(css.important());
		} else {
			removeStyleName(css.important());
		}
		this.important = important;
	}

	/**
	 * Should the button be rendered as a confirm button
	 * 
	 * @return a boolean.
	 */
	public boolean isConfirm() {
		return confirm;
	}

	/**
	 * Should the button be rendered as a confirm button
	 * 
	 * @param confirm true if the button should be rendered as a confirm button,
	 *            otherwise false
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
