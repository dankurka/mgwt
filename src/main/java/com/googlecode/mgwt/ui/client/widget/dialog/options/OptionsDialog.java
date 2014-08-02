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
package com.googlecode.mgwt.ui.client.widget.dialog.options;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialog;
import com.googlecode.mgwt.ui.client.widget.dialog.overlay.SlideUpDialogOverlay;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;

import java.util.Iterator;

/**
 * A simple options dialog
 *
 * This dialog should offer a number of choises, represented by a number of buttons
 *
 * @author Daniel Kurka
 */
public class OptionsDialog implements HasWidgets, Dialog {

  private static final OptionsDialogAppearance DEFAULT_APPEARANCE = GWT
      .create(OptionsDialogAppearance.class);

  private SlideUpDialogOverlay slideUpPanel;
  private FlexPanel container;

  public OptionsDialog() {
    this(DEFAULT_APPEARANCE);
  }

  public OptionsDialog(OptionsDialogAppearance appearance) {
    slideUpPanel = new SlideUpDialogOverlay(appearance);
    container = new FlexPanel();
    container.addStyleName(appearance.css().optionsDialog());
    slideUpPanel.add(container);
  }

  @Override
  public void add(Widget w) {
    // Older versions need block style for flexible box model to work
    if(MGWT.getOsDetection().isIOS6() || MGWT.getOsDetection().isAndroid4_3_orLower()) {
      w.getElement().getStyle().setDisplay(Display.BLOCK);
    }
    container.add(w);
  }

  @Override
  public void clear() {
    container.clear();
  }

  @Override
  public void hide() {
    slideUpPanel.hide();
  }

  @Override
  public Iterator<Widget> iterator() {
    return container.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return container.remove(w);
  }

  public void setPanelToOverlay(HasWidgets widgetToCover) {
    slideUpPanel.setPanelToOverlay(widgetToCover);
  }

  public void show() {
    slideUpPanel.show();
  }
}
