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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.button.ButtonBase;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;

/**
 * @author Daniel Kurka
 * 
 */
public class HeaderButton extends ButtonBase {

	private ParagraphElement pElement;
	protected final HeaderCss headerCss;

	public HeaderButton() {
		this(MGWTStyle.getDefaultClientBundle().getHeaderCss());
	}

	public HeaderButton(HeaderCss css) {
		super(css);
		this.headerCss = css;
		addStyleName(css.headerButton());

		Element pointDiv = DOM.createDiv();
		Element pointSpan = DOM.createSpan();
		pointDiv.appendChild(pointSpan);
		getElement().appendChild(pointDiv);

		pElement = Document.get().createPElement();
		getElement().appendChild(pElement);

	}

	@Override
	public void setText(String text) {
		pElement.setInnerText(text);
	}

	@Override
	public String getText() {

		return pElement.getInnerText();
	}

	public void setBackButton(boolean back) {
		removeStyles();
		if (back) {
			addStyleName(headerCss.back());
		}
	}

	public void setForwardButon(boolean forward) {
		removeStyles();
		if (forward) {
			addStyleName(headerCss.forward());
		}
	}

	public void setRoundButton(boolean round) {
		removeStyles();
		if (round) {
			addStyleName(headerCss.round());
		}
	}

	protected void removeStyles() {
		removeStyleName(headerCss.back());
		removeStyleName(headerCss.round());
		removeStyleName(headerCss.forward());
	}
}
