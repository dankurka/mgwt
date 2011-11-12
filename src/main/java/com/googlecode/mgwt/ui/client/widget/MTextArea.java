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

import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.user.client.ui.TextArea;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.widget.base.MTextBoxBase;

/**
 * A simple text area
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class MTextArea extends MTextBoxBase {

	/**
	 * Construct a text area
	 */
	public MTextArea() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getInputCss());
	}

	/**
	 * Construct a text area with a given css
	 *
	 * @param css the css to use
	 */
	public MTextArea(InputCss css) {
		super(css, new TextArea());
		addStyleName(css.textArea());
	}

	/**
	 * get the number of character per line
	 *
	 * @return the number of characters per line
	 */
	public int getCharacterWidth() {
		return getTextAreaElement().getCols();
	}

	/**
	 * get the number of visible lines
	 *
	 * @return the number of visible lines
	 */
	public int getVisibleLines() {
		return getTextAreaElement().getRows();
	}

	/**
	 * set the number of characters per line
	 *
	 * @param width the number of characters per line
	 */
	public void setCharacterWidth(int width) {
		getTextAreaElement().setCols(width);
	}

	/**
	 * Set the number of visible lines
	 *
	 * @param lines the number of visible lines
	 */
	public void setVisibleLines(int lines) {
		getTextAreaElement().setRows(lines);
	}

	private TextAreaElement getTextAreaElement() {
		return box.getElement().cast();
	}

}
