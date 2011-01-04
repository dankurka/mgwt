/**
 * 29.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.panel.RoundPanel;
import de.kurka.gwt.mobile.ui.client.panel.ScrollPanel;

/**
 * @author kurt
 *
 */
public class ScrollWidgetViewImpl implements ScrollWidgetView {

	private FlowPanel main;
	private HeaderBackButton headerBackButton;

	public ScrollWidgetViewImpl() {

		main = new FlowPanel();

		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.getTitleWidget().setText("Scroll Widget");

		headerBackButton = new HeaderBackButton();
		headerBackButton.setText("UI");
		headerPanel.setLeftWidget(headerBackButton);

		main.add(headerPanel);

		ScrollPanel scrollPanel = new ScrollPanel();

		scrollPanel.setSize("320px", "320px");

		RoundPanel roundPanel = new RoundPanel();
		for (int i = 0; i < 100; i++) {
			roundPanel.add(new HTML("big html content <br/> and stuff"));
		}

		scrollPanel.setWidget(roundPanel);

		main.add(scrollPanel);

		//roundPanel = new RoundPanel();
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return headerBackButton;
	}

}
