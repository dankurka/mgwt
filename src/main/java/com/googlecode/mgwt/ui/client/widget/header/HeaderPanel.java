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
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel.Justification;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel.Orientation;
import com.googlecode.mgwt.ui.client.widget.panel.flex.IsFlexPanel;

import java.util.Iterator;

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
public class HeaderPanel extends Composite implements IsFlexPanel, HasWidgets.ForIsWidget {

  public static final HeaderAppearance DEFAULT_APPEARANCE = GWT.create(HeaderAppearance.class);

  private HeaderAppearance appearance;

  @UiField
  protected FlexPanel container;

  public HeaderPanel() {
    this(DEFAULT_APPEARANCE);
  }

  public HeaderPanel(HeaderAppearance appearance) {
    this.appearance = appearance;
    initWidget(appearance.panelBinder().createAndBindUi(this));
  }

  @UiFactory
  protected HeaderAppearance getAppearance() {
	  return appearance;
  }

  @Override
  public void add(Widget widget, int flex) {
    container.add(widget, flex);
  }

  @Override
  public void setOrientation(Orientation value) {
    container.setOrientation(value);
  }

  @Override
  public void setAlignment(Alignment value) {
    container.setAlignment(value);
  }

  @Override
  public void setJustification(Justification value) {
    container.setJustification(value);
  }

  @Override
  public void add(Widget w) {
    container.add(w);
  }

  @Override
  public void clear() {
    container.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return container.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return container.remove(w);
  }

  @Override
  public void add(IsWidget w) {
    container.add(w);
  }

  @Override
  public boolean remove(IsWidget w) {
    return container.remove(w);
  }
}
