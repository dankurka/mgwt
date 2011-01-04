/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.Button;
import de.kurka.gwt.mobile.ui.client.panel.RoundPanel;

/**
 * @author kurt
 *
 */
public class AboutViewImpl implements AboutView {

	private RoundPanel main;
	private Button backbutton;

	public AboutViewImpl() {
		main = new RoundPanel();

		main.add(new HTML("mobile gwt"));
		main.add(new HTML("Version 0.5"));
		main.add(new HTML("Built by Daniel Kurka, @dankurka on Twitter"));

		main.add(new HTML("Using GWT to build mobile apps"));

		backbutton = new Button();

		main.add(backbutton);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void setBackButtonText(String text) {
		backbutton.setText(text);

	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return backbutton;
	}

}
