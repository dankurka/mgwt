package com.googlecode.mgwt.ui.client.widget.input.checkbox;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;

public interface MCheckBoxAppearance {
  public interface CheckBoxCss extends CssResource {

    @ClassName("mgwt-CheckBox")
    public String checkBox();

    @ClassName("mgwt-CheckBox-middle")
    public String middle();

    @ClassName("mgwt-CheckBox-middle-content")
    public String content();

    @ClassName("mgwt-CheckBox-on")
    public String on();

    @ClassName("mgwt-CheckBox-off")
    public String off();

    @ClassName("mgwt-CheckBox-important")
    public String important();

    @ClassName("mgwt-CheckBox-checked")
    public String checked();

    @ClassName("mgwt-CheckBox-notchecked")
    public String notChecked();

  }

  CheckBoxCss css();

  UiBinder<? extends Element, MCheckBox> uiBinder();
}
