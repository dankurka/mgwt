/*
 * Copyright 2014 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.form;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class Form extends Composite implements HasWidgets {

  public static final FormAppearance DEFAULT_APPEARANCE = GWT.create(FormAppearance.class);

  private FormAppearance appearance;

  @UiField
  protected Panel container;

  @UiField
  protected Panel headerContainer;

  private List<FormEntry> children = new ArrayList<FormEntry>();
  private FormEntry firstChild;
  private FormEntry lastChild;

  public Form() {
    this(DEFAULT_APPEARANCE);
  }

  public Form(FormAppearance appearance) {
    this.appearance = appearance;
    initWidget(this.appearance.uiBinder().createAndBindUi(this));
  }

  @Override
  public void add(Widget w) {
    if(!(w instanceof FormEntry)) {
      throw new IllegalArgumentException("A form does only take FormListEntries as children");
    }
    FormEntry formEntry = (FormEntry) w;
    if(children.size() == 0) {
      formEntry.addStyleName(this.appearance.css().formEntryFirstChild());
      formEntry.addStyleName(this.appearance.css().formEntryLastChild());
      firstChild = formEntry;
      lastChild = formEntry;
    } else {
      lastChild.removeStyleName(this.appearance.css().formEntryLastChild());
      formEntry.addStyleName(this.appearance.css().formEntryLastChild());
      lastChild = formEntry;
    }
    container.add(w);
    children.add(formEntry);
  }

  @Override
  public void clear() {
    container.clear();
    children.clear();
    firstChild = null;
    lastChild = null;
  }

  @Override
  public Iterator<Widget> iterator() {
    return container.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    children.remove(w);
    boolean remove = container.remove(w);

    if(w == firstChild) {
      firstChild.removeStyleName(this.appearance.css().formEntryFirstChild());
      firstChild = null;
      if(children.size() > 0) {
        firstChild = children.get(0);
        firstChild.addStyleName(this.appearance.css().formEntryFirstChild());
      }
    }
    if(w == lastChild) {
      lastChild.removeStyleName(this.appearance.css().formEntryLastChild());
      lastChild = null;
      if(children.size() > 0) {
        lastChild = children.get(children.size() - 1);
        lastChild.addStyleName(this.appearance.css().formEntryLastChild());
      }
    }
    return remove;
  }

  public boolean isValid() {
    for (FormEntry entry : children) {
      if(!entry.isValid()) {
        return false;
      }
    }
    return true;
  }

  public void setRound(boolean round) {
    if (round) {
      addStyleName(this.appearance.css().round());
    } else {
      removeStyleName(this.appearance.css().round());
    }
  }

  public void setHeader(Widget header) {
    headerContainer.setVisible(header != null);
    headerContainer.clear();
    if (header != null) {
      headerContainer.add(header);
    }
  }

  @UiFactory
  protected FormAppearance getAppearance() {
	return appearance;
  }
}
