package com.googlecode.mgwt.ui.client.widget.panel.flex;

import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel.Alignment;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel.Justification;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel.Orientation;

public interface IsFlexPanel {

  void add(Widget widget, int flex) ;

  void setOrientation(Orientation value) ;

  void setAlignment(Alignment value) ;

  void setJustification(Justification value) ;
}
