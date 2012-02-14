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
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

/**
 * A Button that can be used inside a {@link HeaderPanel}
 * 
 * <h2>Styling</h2>
 * 
 * <pre>
 * &lt;div class="mgwt-HeaderButton">
 * 	&lt;div class="mgwt-HeaderButton-border-container">
 * 		&lt;span class="mgwt-HeaderButton-border-content">&lt;/span>
 * 	&lt;/div>
 * 	&lt;p class="mgwt-HeaderButton-text">button text&lt;/p>
 * &lt;/div>
 * </pre>
 * 
 * Those styles are applied to the main div:
 * <ul>
 * <li>.mgwt-HeaderButton-back if the button is a back button</li>
 * <li>.mgwt-HeaderButton-forward if the button is a forward button</li>
 * <li>.mgwt-HeaderButton-round if the button should be rendered with rounded
 * corners</li>
 * <li>.mgwt-HeaderButton-active if the button is pressed</li>
 * </ul>
 * 
 * @author Daniel Kurka
 */

public class HeaderButton extends ButtonBase {

	private ParagraphElement pElement;
	protected final HeaderCss headerCss;

	/**
	 * Construct a HeaderButton
	 */
	public HeaderButton() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getHeaderCss());
	}

	/**
	 * Construct a HeaderButton with a given css
	 * 
	 * @param css the css to use
	 */
	public HeaderButton(HeaderCss css) {
		super(css);
		this.headerCss = css;
		addStyleName(css.headerButton());

		Element pointDiv = DOM.createDiv();
		pointDiv.addClassName(css.headerButtonBorderContainer());
		Element pointSpan = DOM.createSpan();
		pointSpan.addClassName(css.headerButtonBorderContent());
		pointDiv.appendChild(pointSpan);
		getElement().appendChild(pointDiv);

		pElement = Document.get().createPElement();
		pElement.addClassName(css.headerButtonText());
		getElement().appendChild(pElement);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.ButtonBase#setText(java.lang.String)
	 */
	/** {@inheritDoc} */
	@Override
	public void setText(String text) {
		pElement.setInnerText(text);
	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.ButtonBase#getText()
	 */
	/** {@inheritDoc} */
	@Override
	public String getText() {
		return pElement.getInnerText();
	}

	/**
	 * Should the button be rendered as a back button
	 * 
	 * @param back true to render as a back button
	 */
	public void setBackButton(boolean back) {
		removeStyles();
		if (back) {
			addStyleName(headerCss.back());
		}
	}

	/**
	 * Should the button be rendered as a forward button
	 * 
	 * @param forward true to render as a forward button
	 */
	public void setForwardButton(boolean forward) {
		removeStyles();
		if (forward) {
			addStyleName(headerCss.forward());
		}
	}

	/**
	 * Should the button be rendered with rounded corners
	 * 
	 * @param round true to render as a round button
	 */
	public void setRoundButton(boolean round) {
		removeStyles();
		if (round) {
			addStyleName(headerCss.round());
		}
	}

	/**
	 * <p>
	 * removeStyles
	 * </p>
	 */
	protected void removeStyles() {
		removeStyleName(headerCss.back());
		removeStyleName(headerCss.round());
		removeStyleName(headerCss.forward());
	}

}
