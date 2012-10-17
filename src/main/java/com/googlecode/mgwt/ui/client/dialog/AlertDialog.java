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
package com.googlecode.mgwt.ui.client.dialog;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;

/**
 * A simple alert dialog with an ok button.
 * 
 * @author Daniel Kurka
 */
public class AlertDialog implements HasText, HasTitleText, HasTapHandlers, Dialog, HasHTML {

  private HTML textLabel;
  private PopinDialog popinDialog;
  private DialogPanel dialogPanel1;

  /**
   * Construct an alert dialog
   * 
   * @param title - the title of the dialog
   * @param text - the text of the dialog
   */
  public AlertDialog(String title, String text) {
    this(MGWTStyle.getTheme().getMGWTClientBundle().getDialogCss(), title, text);
  }

  /**
   * Construct an alert dialog
   * 
   * @param css - the css to use
   * @param title - the title of the dialog
   * @param text - the text of the dialog
   */
  public AlertDialog(DialogCss css, String title, String text) {
    this(css, title, text, "Ok");
  }

  /**
   * Construct an alert dialog
   * 
   * @param css - the css to use
   * @param title - the title of the dialog
   * @param text - the text of the dialog
   * @param okButtonText the text of the button of the dialog
   */
  public AlertDialog(DialogCss css, String title, String text, String okButtonText) {
    css.ensureInjected();
    popinDialog = new PopinDialog(css);
    dialogPanel1 = new DialogPanel();
    dialogPanel1.showCancelButton(false);
    dialogPanel1.showOkButton(true);

    textLabel = new HTML();
    dialogPanel1.getContent().add(textLabel);
    popinDialog.add(dialogPanel1);

    dialogPanel1.getOkButton().addTapHandler(new TapHandler() {

      @Override
      public void onTap(TapEvent event) {
        popinDialog.hide();
      }

    });

    setText(text);
    setTitleText(title);
    dialogPanel1.setOkButtonText(okButtonText);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.HasText#getText()
   */
  /** {@inheritDoc} */
  @Override
  public String getText() {
    return textLabel.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
   */
  /** {@inheritDoc} */
  @Override
  public void setText(String text) {
    textLabel.setText(text);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.googlecode.mgwt.ui.client.dialog.HasTitleText#setTitleText(java.lang.String)
   */
  /** {@inheritDoc} */
  @Override
  public void setTitleText(String text) {
    dialogPanel1.getDialogTitle().setText(text);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.googlecode.mgwt.ui.client.dialog.HasTitleText#getTitleText()
   */
  /** {@inheritDoc} */
  @Override
  public String getTitleText() {
    return dialogPanel1.getDialogTitle().getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.googlecode.mgwt.ui.client.dialog.Dialog#show()
   */
  /**
   * <p>
   * show
   * </p>
   */
  public void show() {
    popinDialog.center();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.googlecode.mgwt.dom.client.event.tap.HasTapEvent#addTapHandler(com.googlecode.mgwt.dom.
   * client.event.tap.TapHandler)
   */
  /** {@inheritDoc} */
  @Override
  public HandlerRegistration addTapHandler(TapHandler handler) {
    return dialogPanel1.getOkButton().addTapHandler(handler);
  }

  @Override
  public String getHTML() {
    return textLabel.getHTML();
  }

  @Override
  public void setHTML(String html) {
    textLabel.setHTML(html);

  }

}
