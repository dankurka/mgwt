/*
 * Copyright 2013 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.menu.overlay;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;

/**
 * Considered experimental and might change, use at your own risk
 */
public class OverlayMenu extends Composite {

  private static final OverlayMenuAppearance APPEARANCE = GWT.create(OverlayMenuAppearance.class);

  private static int menuId = 0;

  @UiField
  protected FlowPanel container;
  @UiField
  protected FlowPanel nav;
  @UiField
  protected FlowPanel main;
  @UiField
  protected AnimationWidget animationWidget;

  private String id;

  @UiField(provided = true)
  protected final OverlayMenuAppearance appearance;

  public OverlayMenu() {
    this(APPEARANCE);
  }

  public OverlayMenu(OverlayMenuAppearance appearance) {
    this.appearance = appearance;
    initWidget(appearance.uiBinder().createAndBindUi(this));

    id = "__mgwt_overlaymenu__" + menuId++;
    getElement().setId(id);
    String mainClass = appearance.css().main();
    String navClass = appearance.css().nav();

    StringBuilder css = new StringBuilder();

    // nasty hack since we can not use media queries directly
    css.append("@media (orientation:portrait) {\n");

    css.append("#" + id + " ." + mainClass + "{\n");
    css.append("left: 200px;");
    css.append("}");

    css.append("#" + id + " ." + navClass + "{\n");
    css.append("width: 200px;");
    css.append("}");

    css.append("}");

    StyleInjector.inject(css.toString());
  }

  public void setMaster(Widget master) {
    animationWidget.setFirstWidget(master);
    animationWidget.animate(null, true, null);
  }

  public void setDetail(Widget detail) {
    main.clear();
    if (detail != null) {
      main.add(detail);
    }
  }

  @UiFactory
  protected static OverlayMenuAppearance getAppearance() {
	  return APPEARANCE;
  }
}
