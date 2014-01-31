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
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeEvent.ORIENTATION;
import com.googlecode.mgwt.dom.client.event.orientation.OrientationChangeHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.animation.AnimationWidget;

/**
 * Considered experimental and might change, use at your own risk
 */
public class OverlayMenu extends Composite {

  private static final OverlayMenuAppearance APPEARANCE = GWT.create(OverlayMenuAppearance.class);

  @UiField
  protected FlowPanel container;
  @UiField
  protected FlowPanel nav;
  @UiField
  protected FlowPanel main;
  @UiField
  protected AnimationWidget animationWidget;

  @UiField(provided = true)
  protected final OverlayMenuAppearance appearance;

  public OverlayMenu() {
    this(APPEARANCE);
    
    update(MGWT.getOrientation());
  }

  public OverlayMenu(OverlayMenuAppearance appearance) {
    this.appearance = appearance;
    initWidget(appearance.uiBinder().createAndBindUi(this));

    MGWT.addOrientationChangeHandler(new OrientationChangeHandler() {

      @Override
      public void onOrientationChanged(OrientationChangeEvent event) {
        update(event.getOrientation());
      }
    });
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

  private void update(ORIENTATION o) {
    // TODO proper
    switch (o) {
      case LANDSCAPE:
        nav.setWidth("300px");
        main.getElement().getStyle().setLeft(300, Unit.PX);
        break;
      case PORTRAIT:
        nav.setWidth("150px");
        main.getElement().getStyle().setLeft(150, Unit.PX);
        break;
    }
  }
}
