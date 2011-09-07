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
package de.kurka.gwt.mobile.ui.client.button;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

import de.kurka.gwt.mobile.ui.client.theme.base.HeaderButtonCss;

/**
 * @author Daniel Kurka
 *
 */
public abstract class HeaderButton extends ButtonBase {

	private ParagraphElement pElement;

	public HeaderButton(HeaderButtonCss css) {
		super(css);

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
}
