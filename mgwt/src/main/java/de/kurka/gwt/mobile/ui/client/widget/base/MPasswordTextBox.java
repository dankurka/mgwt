/**
 * 30.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.base;

import com.google.gwt.user.client.ui.PasswordTextBox;

/**
 * @author kurt
 *
 */
public class MPasswordTextBox extends PasswordTextBox {

	public MPasswordTextBox() {
		setStylePrimaryName("mgwt-PasswordTextBox");
	}

	public void setPlaceHolder(String value) {
		getElement().setAttribute("placeholder", value);
	}

	public String getPlaceHolder() {
		return getElement().getAttribute("placeholder");
	}
}
