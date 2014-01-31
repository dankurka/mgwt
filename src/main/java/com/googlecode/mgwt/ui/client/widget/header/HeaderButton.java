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
package com.googlecode.mgwt.ui.client.widget.header;

import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiField;

import com.googlecode.mgwt.ui.client.widget.button.ButtonBase;

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

  @UiField
  protected Element text;
  private HeaderAppearance appearance;

  public HeaderButton() {
    this(HeaderPanel.DEFAULT_APPEARANCE);
  }

  public HeaderButton(HeaderAppearance appearance) {
    super(appearance);
    this.appearance = appearance;
    setElement(appearance.uiBinder().createAndBindUi(this));
  }

  @Override
  public void setText(String text) {
    this.text.setInnerText(text);
  }

  @Override
  public String getText() {
    return text.getInnerText();
  }

  /**
   * Should the button be rendered as a back button
   * 
   * @param back true to render as a back button
   */
  public void setBackButton(boolean back) {
    removeStyles();
    if (back) {
      addStyleName(appearance.css().back());
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
      addStyleName(appearance.css().forward());
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
      addStyleName(appearance.css().round());
    }
  }

  protected void removeStyles() {
    removeStyleName(appearance.css().back());
    removeStyleName(appearance.css().round());
    removeStyleName(appearance.css().forward());
  }
}
