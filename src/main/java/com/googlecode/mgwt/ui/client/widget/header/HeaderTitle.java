/*
 * Copyright 2014 Daniel Kurka
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

import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class HeaderTitle extends Widget implements HasText {

  private HeaderAppearance appearance;

  public HeaderTitle() {
    this(HeaderPanel.DEFAULT_APPEARANCE);
  }

  public HeaderTitle(String text) {
    this(HeaderPanel.DEFAULT_APPEARANCE, text);
  }

  public HeaderTitle(HeaderAppearance appearance) {
    this(appearance, "");
  }

  public HeaderTitle(HeaderAppearance appearance, String text) {
    this.appearance = appearance;
    setElement(appearance.uiBinderTitle().createAndBindUi(this));
    setText(text);
  }

  @Override
  public String getText() {
    return getElement().getInnerText();
  }

  @Override
  public void setText(String text) {
    getElement().setInnerText(text);
  }

  @UiFactory
  public HeaderAppearance getAppearance() {
    return appearance;
  }
}
