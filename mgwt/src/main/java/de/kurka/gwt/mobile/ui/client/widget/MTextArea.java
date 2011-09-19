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
package de.kurka.gwt.mobile.ui.client.widget;

import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.user.client.ui.TextArea;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.InputCss;
import de.kurka.gwt.mobile.ui.client.widget.base.MTextBoxBase;

/**
 * @author Daniel Kurka
 * 
 */
public class MTextArea extends MTextBoxBase {

	public MTextArea() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());
	}

	public MTextArea(InputCss css) {
		super(css, new TextArea());
		addStyleName(css.textArea());
	}

	public int getCharacterWidth() {
		return getTextAreaElement().getCols();
	}

	public int getVisibleLines() {
		return getTextAreaElement().getRows();
	}

	public void setCharacterWidth(int width) {
		getTextAreaElement().setCols(width);
	}

	public void setVisibleLines(int lines) {
		getTextAreaElement().setRows(lines);
	}

	private TextAreaElement getTextAreaElement() {
		return box.getElement().cast();
	}

}
