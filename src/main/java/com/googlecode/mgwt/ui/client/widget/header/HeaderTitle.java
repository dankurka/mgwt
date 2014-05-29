package com.googlecode.mgwt.ui.client.widget.header;

import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class HeaderTitle extends Widget implements HasText {

  private HeaderAppearance appearance;

  public HeaderTitle() {
    this(HeaderPanel.DEFAULT_APPEARANCE);
  }

  public HeaderTitle(String text) {
    this(HeaderPanel.DEFAULT_APPEARANCE, text);
  }

  public HeaderTitle(HeaderAppearance appearance) {
    this(appearance, "");
  }

  public HeaderTitle(HeaderAppearance appearance, String text) {
    this.appearance = appearance;
    setElement(appearance.uiBinderTitle().createAndBindUi(this));
    setText(text);
  }

  @Override
  public String getText() {
    return getElement().getInnerText();
  }

  @Override
  public void setText(String text) {
    getElement().setInnerText(text);
  }

  @UiFactory
  protected HeaderAppearance getAppearance() {
    return appearance;
  }
}
