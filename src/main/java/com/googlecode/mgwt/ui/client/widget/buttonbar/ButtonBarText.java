package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.google.gwt.user.client.ui.HasHTML;

import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class ButtonBarText extends TouchWidget implements HasHTML {

  private ButtonBarAppearance appearance;

  public ButtonBarText() {
    this(ButtonBar.DEFAULT_APPEARANCE, "");
  }

  public ButtonBarText(String html) {
    this(ButtonBar.DEFAULT_APPEARANCE, html);
  }

  public ButtonBarText(ButtonBarAppearance appearance, String html) {
    this.appearance = appearance;
    setElement(this.appearance.barText().createAndBindUi(this));
    setHTML(html);
  }

  @Override
  public String getText() {
    return getElement().getInnerText();
  }

  @Override
  public void setText(String text) {
    getElement().setInnerText(text);
  }

  @Override
  public String getHTML() {
    return getElement().getInnerHTML();
  }

  @Override
  public void setHTML(String html) {
    getElement().setInnerHTML(html);
  }
}
