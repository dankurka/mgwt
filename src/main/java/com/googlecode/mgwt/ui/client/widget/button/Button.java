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
package com.googlecode.mgwt.ui.client.widget.button;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.GwtEvent;

/**
 * <h1>A simple button class</h1> This is a simple class for rendering button in mgwt.
 * 
 * <h2>Styling notes:</h2> The button consists of a simple div element like this:
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
 * <li>.mgwt-Button-confirm - Button should be rendered as a confirm button (e.g. green on iOS)</li>
 * <li>.mgwt-Button-important - Button should be rendered as important (e.g. red on iOS)</li>
 * <li>.mgwt-Button-round - Button should be rendered with rounded corners</li>
 * </ul>
 * 
 * 
 * 
 * @author Daniel Kurka
 */
public class Button extends ButtonBase {

  private static final ButtonAppearance DEFAULT_BUTTON_APPEARANCE = GWT
      .create(ButtonAppearance.class);

  private boolean round;
  private boolean small;
  private boolean confirm;

  private final ButtonAppearance appearance;

  public Button() {
    this(DEFAULT_BUTTON_APPEARANCE, "");
  }

  public Button(String text) {
    this(DEFAULT_BUTTON_APPEARANCE, text);
  }

  public Button(ButtonAppearance appearance, String text) {
    super(appearance);
    this.appearance = appearance;
    setElement(appearance.uiBinder().createAndBindUi(this));
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
   * @param round true if the button should have rounded corners, otherwise false
   */
  public void setRound(boolean round) {
    if (round) {
      addStyleName(appearance.css().round());
    } else {
      removeStyleName(appearance.css().round());
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
      addStyleName(appearance.css().small());
    } else {
      removeStyleName(appearance.css().small());
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
  private boolean disabled;

  /**
   * Should the button be rendered as important
   * 
   * @return true if the button should be rendered as important, otherwise false
   */
  public boolean isImportant() {
    return important;
  }

  /**
   * Should the button be rendered as important
   * 
   * @param important true if the button should be rendered as important, otherwise false
   */
  public void setImportant(boolean important) {
    if (important) {
      addStyleName(appearance.css().important());
    } else {
      removeStyleName(appearance.css().important());
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
   * @param confirm true if the button should be rendered as a confirm button, otherwise false
   */
  public void setConfirm(boolean confirm) {
    if (confirm) {
      addStyleName(appearance.css().confirm());
    } else {
      removeStyleName(appearance.css().confirm());
    }
    this.confirm = confirm;
  }

  /**
   * Should the button be disabled. By default, the button will be grayed out.
   * 
   * @param disabled true if the button should be disabled, otherwise false
   */
  public void setDisabled(boolean disabled) {
    if (disabled) {
      addStyleName(appearance.css().disabled());
    } else {
      removeStyleName(appearance.css().disabled());
    }
    this.disabled = disabled;
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    if (!disabled) {
      // only fire events if the button is currently enabled
      super.fireEvent(event);
    }
  }
}
