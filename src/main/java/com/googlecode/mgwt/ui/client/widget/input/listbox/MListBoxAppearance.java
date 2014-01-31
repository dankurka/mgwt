package com.googlecode.mgwt.ui.client.widget.input.listbox;

import com.google.gwt.resources.client.CssResource;

public interface MListBoxAppearance {
  public interface MListBoxCss extends CssResource {
    @ClassName("mgwt-ListBox")
    String listBox(); 
  }
  MListBoxCss css();
}
