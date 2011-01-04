/**
 * 30.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.base;

import com.google.gwt.user.client.ui.TextArea;

/**
 * @author kurt
 *
 */
public class MTextArea extends TextArea {
	/**
	 * 
	 */
	public MTextArea() {
		setStylePrimaryName("mgwt-TextArea");
	}

	public void setPlaceHolder(String value) {
		getElement().setAttribute("placeholder", value);
	}

	public String getPlaceHolder() {
		return getElement().getAttribute("placeholder");
	}
}
