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
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

/**
 * A Button that can be used inside a {@link HeaderPanel}
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class HeaderButton extends ButtonBase implements HasTapHandlers {

	private ParagraphElement pElement;
	protected final HeaderCss headerCss;

	/**
	 * Construct a HeaderButton
	 */
	public HeaderButton() {
		this(MGWTStyle.getDefaultClientBundle().getHeaderCss());
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
		Element pointSpan = DOM.createSpan();
		pointDiv.appendChild(pointSpan);
		getElement().appendChild(pointDiv);

		pElement = Document.get().createPElement();
		getElement().appendChild(pElement);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.base.ButtonBase#addTapHandler(com.googlecode.mgwt.dom.client.event.tap.TapHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTapHandler(TapHandler handler) {
		return super.addTapHandler(handler);
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
	 * <p>removeStyles</p>
	 */
	protected void removeStyles() {
		removeStyleName(headerCss.back());
		removeStyleName(headerCss.round());
		removeStyleName(headerCss.forward());
	}

}
