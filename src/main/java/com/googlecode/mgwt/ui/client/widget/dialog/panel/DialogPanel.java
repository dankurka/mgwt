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
package com.googlecode.mgwt.ui.client.widget.dialog.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasWidgets;

import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

/**
 * A dialog panel with title, text, ok and cancel button
 *
 * @author Daniel Kurka
 */
public class DialogPanel extends Composite {

  public static final DialogPanelAppearance DEFAULT_APPEARANCE = GWT
      .create(DialogPanelAppearance.class);

  @UiField
  public HTML title;
  @UiField
  public FlowPanel content;
  @UiField
  public FlowPanel buttonContainer;

  @UiField(provided = true)
  public DialogButton okButton;
  @UiField(provided = true)
  public DialogButton cancelButton;

  private DialogPanelAppearance appearance;

  /**
   * Construct the panel
   */
  public DialogPanel() {
    this(DEFAULT_APPEARANCE);
  }

  /**
   * Construct panel with a special css
   *
   * @param css the css to use
   */
  public DialogPanel(DialogPanelAppearance appearance) {
    this.appearance = appearance;
    okButton = new DialogButton(appearance, "Ok");
    cancelButton = new DialogButton(appearance, "cancel");
    initWidget(this.appearance.dialogBinder().createAndBindUi(this));
    cancelButton.setCancel(true);
    okButton.setOK(true);
  }

  /**
   * get the container of the panel
   *
   * @return the container of the dialog panel
   */
  public HasWidgets getContent() {
    return content;
  }

  /**
   * get {@link HasTapHandlers} for the cancel button
   *
   * @return the {@link HasTapHandlers} for cancel button
   */
  public HasTapHandlers getCancelButton() {
    return cancelButton;
  }

  /**
   * get {@link HasTapHandlers} for the ok button
   *
   * @return the {@link HasTapHandlers} for ok button
   */
  public HasTapHandlers getOkButton() {
    return okButton;
  }

  public void setOkButtonText(String text) {
    this.okButton.setText(text);
  }

  public void setCancelButtonText(String text) {
    this.cancelButton.setText(text);
  }

  /**
   * show the cancel button
   *
   * @param show true to show, otherwise hidden
   */
  public void showCancelButton(boolean show) {
    if (show) {
      int widgetCount = buttonContainer.getWidgetCount();
      if (widgetCount == 0) {
        buttonContainer.add(cancelButton);
      }
    } else {
      buttonContainer.remove(cancelButton);
    }
  }

  /**
   * show the ok button
   *
   * @param show true to show, otherwise hidden
   */
  public void showOkButton(boolean show) {
    if (show) {
      buttonContainer.insert(okButton, 0);
    } else {
      buttonContainer.remove(okButton);
    }
  }

  /**
   * Get the title of the dialog
   *
   * @return the title of the dialog
   */
  public HasHTML getDialogTitle() {
    return title;
  }
}
