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
package com.googlecode.mgwt.ui.client.widget.layout;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.layout.LayoutAppearance.LayoutCss;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

import java.util.Iterator;

/**
 * A layout panel can resize children to take up remaining space on the screen
 * 
 * This is done automatically for {@link ScrollPanel}
 * 
 * Other children that want to fill space should add the style {@link LayoutCss#flexible()}
 * 
 * @author Daniel Kurka
 */
public class LayoutPanel extends Composite implements HasWidgets.ForIsWidget, HasLayout {

  public static final LayoutAppearance DEFAULT_APPERANCE = GWT.create(LayoutAppearance.class);

  @UiField
  protected FlowPanel container;

  private LayoutAppearance appearance;

  public LayoutPanel() {
    this(DEFAULT_APPERANCE);
  }

  public LayoutPanel(LayoutAppearance appearance) {
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

  @Override
  public void flex(boolean flex) {
    if (flex) {
      container.addStyleName(appearance.css().flexible());
    } else {
      container.removeStyleName(appearance.css().flexible());
    }
  }

  @Override
  public void fillParent(boolean fill) {
    if (fill) {
      container.addStyleName(appearance.css().fillParent());
    } else {
      container.removeStyleName(appearance.css().fillParent());
    }
  }

  @Override
  public void center(boolean center) {
    if (center) {
      container.addStyleName(appearance.css().centerHorizontically());
      container.addStyleName(appearance.css().centerVertically());
    } else {
      container.removeStyleName(appearance.css().centerHorizontically());
      container.removeStyleName(appearance.css().centerVertically());
    }
  }

  @Override
  public void centerHorizontically(boolean center) {
    if (center) {
      container.addStyleName(appearance.css().centerHorizontically());
    } else {
      container.removeStyleName(appearance.css().centerHorizontically());
    }
  }

  @Override
  public void centerVertically(boolean center) {
    if (center) {
      container.addStyleName(appearance.css().centerVertically());
    } else {
      container.removeStyleName(appearance.css().centerVertically());
    }
  }

  @UiFactory
  protected LayoutAppearance getAppearance() {
	return appearance;
  }
}
