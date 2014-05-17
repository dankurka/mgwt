package com.googlecode.mgwt.ui.client.widget.input.listbox;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface MListBoxAppearance {
  public interface MListBoxCss extends MGWTCssResource {
    @ClassName("mgwt-ListBox")
    String listBox();
  }
  MListBoxCss css();
}
