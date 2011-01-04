/**
 * 30.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.base;

import com.google.gwt.user.client.ui.TextBox;

/**
 * @author kurt
 *
 */
public class MTextBox extends TextBox {

	public MTextBox() {
		setStylePrimaryName("mgwt-TextBox");
	}

	public void setPlaceHolder(String value) {
		getElement().setAttribute("placeholder", value);
	}

	public String getPlaceHolder() {
		return getElement().getAttribute("placeholder");
	}
}
