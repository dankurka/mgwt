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
package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;


/**
 * 
 */
public class ButtonBar extends Composite implements HasWidgets {

  protected static final ButtonBarAppearance DEFAULT_APPEARANCE = GWT
      .create(ButtonBarAppearance.class);

  @UiField
  protected Panel main;

  protected ButtonBarAppearance appearance;

  public ButtonBar() {
    this(DEFAULT_APPEARANCE);
  }

  public ButtonBar(ButtonBarAppearance appearance) {
    this.appearance = appearance;
    initWidget(appearance.barBinder().createAndBindUi(this));
  }

  @Override
  public void add(Widget w) {
    main.add(w);
  }

  @Override
  public void clear() {
    main.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return main.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return main.remove(w);
  }
}
