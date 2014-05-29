/*
 * Copyright 2010 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.panel.flex;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IndexedPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;

/**
 * A FlexPanel uses the flexible box model to layout its children.
 * <p>
 * Note: For children implementing {@link IsFlexible} flex:1 is applied automatically when added
 * to this panel.
 *
 * @author Daniel Kurka
 */
public class FlexPanel extends Composite implements HasWidgets.ForIsWidget, IndexedPanel.ForIsWidget {

  public static enum Alignment {
    START("flex-start"), END("flex-end"), CENTER("center"), STRETCH("stretch"), BASELINE("baseline");

    private final String cssValue;

    private Alignment(String cssValue) {
      this.cssValue = cssValue;
    }

    private static String getCssProperty() {
      return "AlignItems";
    }

    private String getCssValue() {
      return cssValue;
    }
  }

  public static enum Justification {
    START("flex-start"), END("flex-end"), CENTER("center"), SPACE_BETWEEN("space-between");

    private final String cssValue;

    private Justification(String cssValue) {
      this.cssValue = cssValue;
    }

    private static String getCssProperty() {
      return "JustifyContent";
    }

    private String getCssValue() {
      return cssValue;
    }
  }

  public static enum Orientation {
    HORIZONTAL("row"), VERTICAL("column");

    private final String cssValue;

    private Orientation(String cssValue) {
      this.cssValue = cssValue;
    }

    private static String getCssProperty() {
      return "Direction";
    }

    private String getCssValue() {
      return cssValue;
    }
  }

  public static final FlexPanelAppearance DEFAULT_APPERANCE = GWT.create(FlexPanelAppearance.class);

  @UiField
  protected FlowPanel container;

  private FlexPanelAppearance appearance;

  public FlexPanel() {
    this(DEFAULT_APPERANCE);
  }

  public FlexPanel(FlexPanelAppearance appearance) {
    this.appearance = appearance;
    initWidget(this.appearance.uiBinder().createAndBindUi(this));
  }

  @Override
  public void add(Widget w) {
    if (w instanceof IsFlexible) {
      w.addStyleName(appearance.css().flexible());
    }
    container.add(w);
  }

  @Override
  public void clear() {
    Iterator<Widget> iterator = container.iterator();
    while (iterator.hasNext()) {
      Widget w = iterator.next();
      if (w instanceof IsFlexible) {
        w.removeStyleName(appearance.css().flexible());
      }
    }
    container.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return container.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    if (w instanceof IsFlexible) {
      w.removeStyleName(appearance.css().flexible());
    }
    return container.remove(w);
  }

  @Override
  public void add(IsWidget w) {
    if (w.asWidget() instanceof IsFlexible) {
      w.asWidget().addStyleName(appearance.css().flexible());
    }
    container.add(w);
  }

  public void add(Widget widget, double flex) {
    container.add(widget);
    widget.getElement().getStyle().setProperty("flex", Double.toString(flex));
  }

  @Override
  public boolean remove(IsWidget w) {
    if (w.asWidget() instanceof IsFlexible) {
      w.asWidget().removeStyleName(appearance.css().flexible());
    }
    return container.remove(w);
  }

  private void setFlexProperty(String name, String value) {
    setStyleProperty("MozFlex" + name, value);
    setStyleProperty("webkitFlex" + name, value);
    setStyleProperty("flex" + name, value);
  }

  private void setProperty(String name, String value) {
    setStyleProperty("Moz" + name, value);
    setStyleProperty("webkit" + name, value);
    setStyleProperty(name, value);
  }

  public void setOrientation(Orientation value) {
    setFlexProperty(Orientation.getCssProperty(), value.getCssValue());
  }

  public void setAlignment(Alignment value) {
    setProperty(Alignment.getCssProperty(), value.getCssValue());
  }

  public void setJustification(Justification value) {
    setProperty(Justification.getCssProperty(), value.getCssValue());
  }

  private void setStyleProperty(String property, String value) {
    container.getElement().getStyle().setProperty(property, value);
  }

  @UiFactory
  protected FlexPanelAppearance getAppearance() {
	  return appearance;
  }

  @Override
  public Widget getWidget(int index) {
    return container.getWidget(index);
  }

  @Override
  public int getWidgetCount() {
    return container.getWidgetCount();
  }

  @Override
  public int getWidgetIndex(Widget child) {
    return container.getWidgetIndex(child);
  }

  @Override
  public boolean remove(int index) {
    Widget w = getWidget(index);
    if (w instanceof IsFlexible) {
      w.removeStyleName(appearance.css().flexible());
    }
    return container.remove(index);
  }

  @Override
  public int getWidgetIndex(IsWidget child) {
    return container.getWidgetIndex(child);
  }
}
