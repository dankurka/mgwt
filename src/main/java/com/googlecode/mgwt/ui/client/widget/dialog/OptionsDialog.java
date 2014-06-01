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
package com.googlecode.mgwt.ui.client.widget.dialog;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

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

  private SlideUpPanel slideUpPanel;
  private FlexPanel container;

  /**
   * Construct an OptionDialog with a css class
   *
   * @param css the css class to use
   */
  public OptionsDialog(DialogAppearance appearance) {
    slideUpPanel = new SlideUpPanel();
    container = new FlexPanel();
    container.addStyleName(appearance.dialogCss().getBottomPanel());
    slideUpPanel.add(container);
  }

  @Override
  public void add(Widget w) {
    container.add(w);
  }

  @Override
  public void clear() {
    container.clear();
  }

  /**
   * Hide the options dialog
   */
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

  /**
   * Set the area to cover
   *
   * @param widgetToCover the widet to cover
   */
  public void setPanelToOverlay(HasWidgets widgetToCover) {
    slideUpPanel.setPanelToOverlay(widgetToCover);
  }

  public void show() {
    slideUpPanel.show();
  }
}
