package com.googlecode.mgwt.examples.showcase.client;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;

public interface DetailView extends IsWidget{
	public HasText getHeader();
	
	public HasText getBackbuttonText();
	
	public HasSimpleTouchHandler getBackbutton();
	
	public HasText getMainButtonText();
	
	public HasSimpleTouchHandler getMainButton();
}
