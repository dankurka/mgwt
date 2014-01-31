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

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * A HeaderPanel is usually at the top of a page containing navigation elements
 * 
 * it can contain three child elements. A left Widget, a center widget and a
 * right widget.
 * 
 * <h2>Styling</h2>
 * 
 * The DOM structure looks like this:
 * 
 * <pre>
 * &lt;div class="mgwt-HeaderPanel">
 * 
 * &lt;/div>
 * </pre>
 * 
 * If elements are added to the header panel they are directly appended to the
 * div.
 * 
 * A HeaderPanel with left, center and right child could look like this:
 * 
 * <pre>
 * &lt;div class="mgwt-HeaderPanel">
 * 	&lt;div class="mgwt-HeaderPanel-left">custom child&lt;/div>
 * 	&lt;div class="mgwt-HeaderPanel-center">custom child&lt;/div>
 * 	&lt;div class="mgwt-HeaderPanel-right">custom child&lt;/div>
 * &lt;/div>
 * </pre>
 * 
 * @author Daniel Kurka
 */
public class HeaderPanel extends Composite {

  public static final HeaderAppearance DEFAULT_APPEARANCE = GWT.create(HeaderAppearance.class);

  private Widget left;
  private Widget right;
  private Widget title;

  @UiField
  protected FlowPanel container;

  private HeaderAppearance appearance;

  public HeaderPanel() {
    this(DEFAULT_APPEARANCE);
  }

  public HeaderPanel(HeaderAppearance appearance) {
    this.appearance = appearance;
    initWidget(this.appearance.panelBinder().createAndBindUi(this));
  }

  /**
   * Set a text to appear in the middle of the HeaderPanel
   * 
   * @param text the text to render
   */
  public void setCenter(String text) {
    HTML wrapper = new HTML();
    wrapper.setHTML(text);
    setCenterWidget(wrapper);
  }

  /**
   * Set a widget that should appear in the center of the header panel
   * 
   * @param w the widget that is displayed in the center
   */
  @UiChild(limit = 1, tagname = "center")
  public void setCenterWidget(Widget w) {
    if (title != null) {
      container.remove(title);
      title.removeStyleName(appearance.cssPanel().center());
    }
    title = w;
    if (title != null) {
      w.addStyleName(appearance.cssPanel().center());
      container.add(w);
    }
  }

  /**
   * Set the left widget of the header panel
   * 
   * @param newLeft the widget that should be displayed on the left side
   */
  @UiChild(limit = 1, tagname = "left")
  public void setLeftWidget(Widget newLeft) {
    if (left != null) {
      container.remove(left);
      left.removeStyleName(appearance.cssPanel().left());
    }

    left = newLeft;

    if (left != null) {
      left.addStyleName(appearance.cssPanel().left());
      container.add(left);
    }
  }

  /**
   * Set the right widget of the header panel
   * 
   * @param newRight the widget that should be displayed on the right side
   */
  @UiChild(limit = 1, tagname = "right")
  public void setRightWidget(Widget newRight) {
    if (right != null) {
      container.remove(right);
      right.removeStyleName(appearance.cssPanel().right());
    }

    right = newRight;

    if (right != null) {
      right.addStyleName(appearance.cssPanel().right());
      container.add(right);
    }
  }
}
