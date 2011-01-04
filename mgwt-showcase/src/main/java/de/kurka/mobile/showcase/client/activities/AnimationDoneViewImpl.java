/**
 * 29.12.2010
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
public class AnimationDoneViewImpl implements AnimationDoneView {

	private RoundPanel panel;
	private Button button;

	/**
	 * 
	 */
	public AnimationDoneViewImpl() {
		panel = new RoundPanel();

		panel.add(new HTML("toll, wa?"));

		button = new Button();
		button.setText("Back");
		panel.add(button);

	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return button;
	}

}
