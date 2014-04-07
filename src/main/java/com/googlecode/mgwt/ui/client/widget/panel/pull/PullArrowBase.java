/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.panel.pull;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.widget.progress.ProgressIndicator;

/**
 * A header for a pull panel that shows an arrow
 * 
 * @author Daniel Kurka
 * 
 */
public class PullArrowBase extends Composite implements PullArrowWidget {

  @UiField
  protected FlowPanel icon;

  @UiField
  protected HTML textContainer;

  @UiField
  protected ProgressIndicator indicator;

  @UiField
  protected FlowPanel main;

  protected PullPanelAppearance appearance;

  public PullArrowBase(PullPanelAppearance appearance) {

    this.appearance = appearance;
    initWidget(this.appearance.arrow().createAndBindUi(this));

    indicator.getElement().getStyle().setDisplay(Display.NONE);

    resetRotation();

    addDomHandler(new TransitionEndHandler() {

      @Override
      public void onTransitionEnd(TransitionEndEvent event) {
        event.preventDefault();
        event.stopPropagation();

      }
    }, TransitionEndEvent.getType());

    showArrow();
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void onScroll(int positionY) {
    int degree = getRotation(positionY);
    CssUtil.rotate(icon.getElement(), degree);
  }

  public void showError() {
    removeStyles();
    icon.addStyleName(appearance.css().error());

    icon.setVisible(true);
    indicator.setVisible(false);
  }

  @Override
  public int getHeight() {
    // TODO
    return 70;
  }

  @Override
  public int getStateSwitchPosition() {
    // TODO
    return 50;
  }

  @Override
  public void setHTML(final String html) {
    String htmlToSet = html;
    if (html == null) {
      htmlToSet = "";
    }

    textContainer.setHTML(htmlToSet);

  }

  public void showArrow() {
    removeStyles();
    icon.addStyleName(appearance.css().arrow());

    resetRotation();

    icon.setVisible(true);
    indicator.setVisible(false);
  }

  public void showLoadingIndicator() {
    icon.getElement().setAttribute("style", "");
    showSpinner();
  }

  protected void showSpinner() {
    removeStyles();
    icon.setVisible(false);
    indicator.setVisible(true);

  }

  protected int getRotation(int y) {
    return y;
  }

  protected void resetRotation() {
    int rotation = getRotation(0);
    CssUtil.rotate(icon.getElement(), rotation);
  }

  protected void removeStyles() {
    icon.removeStyleName(appearance.css().arrow());
    icon.removeStyleName(appearance.css().error());
  }

  @UiFactory
  protected PullPanelAppearance getAppearance() {
	return appearance;
  }
}
