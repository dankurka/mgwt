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

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;

import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;

/**
 * A simple confirm dialog with ok and cancel buttons
 * 
 * @author Daniel Kurka
 */
public class ConfirmDialog implements HasText, HasTitleText, Dialog {
  /**
   * The callback used when buttons are taped
   * 
   * @author Daniel Kurka
   * 
   */
  public interface ConfirmCallback {
    /**
     * Called if ok button is taped
     */
    public void onOk();

    /**
     * called if cancel button is taped
     */
    public void onCancel();
  }

  private PopinDialog popinDialog;
  private DialogPanel dialogPanel1;
  private Label textLabel;
  private ConfirmCallback callback;

  /**
   * Construct a Confirmdialg
   * 
   * @param title - the title of the dialog
   * @param text - the text of the dialog
   * @param callback - the callback used when a button of the dialog is taped
   */
  public ConfirmDialog(String title, String text, ConfirmCallback callback) {
    this(Dialogs.DEFAULT_APPEARANCE, title, text, callback);
  }

  /**
   * Construct a Confirmdialg
   * 
   * @param css . css to use
   * @param title - the title of the dialog
   * @param text - the text of the dialog
   * @param callback - the callback used when a button of the dialog is taped
   */
  public ConfirmDialog(DialogAppearance appearance, String title, String text, ConfirmCallback callback) {
    this(appearance, title, text, callback, "Ok", "Cancel");
  }

  /**
   * Construct a Confirmdialg
   * 
   * @param css . css to use
   * @param title - the title of the dialog
   * @param text - the text of the dialog
   * @param callback - the callback used when a button of the dialog is taped
   */
  public ConfirmDialog(DialogAppearance appearance, String title, String text, ConfirmCallback callback, String okButtonText, String cancelButtonText) {
    this.callback = callback;
    popinDialog = new PopinDialog(appearance);
    dialogPanel1 = new DialogPanel(appearance);
    dialogPanel1.showCancelButton(true);
    dialogPanel1.showOkButton(true);

    textLabel = new Label();
    dialogPanel1.getContent().add(textLabel);
    popinDialog.add(dialogPanel1);

    dialogPanel1.getOkButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        popinDialog.hide();
        if (ConfirmDialog.this.callback != null)
          ConfirmDialog.this.callback.onOk();
      }
    });

    dialogPanel1.getCancelButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        popinDialog.hide();
        if (ConfirmDialog.this.callback != null)
          ConfirmDialog.this.callback.onCancel();
      }
    });

    setText(text);
    setTitleText(title);
    dialogPanel1.setCancelButtonText(cancelButtonText);
    dialogPanel1.setOkButtonText(okButtonText);
  }

  @Override
  public void setTitleText(String title) {
    dialogPanel1.getDialogTitle().setHTML(title);
  }

  @Override
  public void setText(String text) {
    textLabel.setText(text);
  }

  @Override
  public String getTitleText() {
    return dialogPanel1.getDialogTitle().getHTML();
  }

  @Override
  public String getText() {
    return textLabel.getText();
  }

  public void show() {
    popinDialog.center();
  }

  @Override
  public void hide() {
    popinDialog.hide();
  }
}
