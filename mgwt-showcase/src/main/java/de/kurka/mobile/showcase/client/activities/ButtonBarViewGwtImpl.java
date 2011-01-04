/**
 * 04.01.2011
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ActionButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ButtonBar;
import de.kurka.gwt.mobile.ui.client.menu.bar.RefreshButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;

/**
 * @author kurt
 *
 */
public class ButtonBarViewGwtImpl implements ButtonBarView {

	private FlowPanel main;
	private ButtonBar footerPanel;
	private HeaderPanel headerPanel;
	private HeaderBackButton backButton;

	/**
	 * 
	 */
	public ButtonBarViewGwtImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();

		headerPanel.getTitleWidget().setText("ButtonBar");
		backButton = new HeaderBackButton();

		backButton.setText("UI");

		headerPanel.setLeftWidget(backButton);

		main.add(headerPanel);

		footerPanel = new ButtonBar();

		footerPanel.add(new RefreshButton());
		footerPanel.add(new ActionButton());

		main.add(footerPanel);
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	/* (non-Javadoc)
	 * @see de.kurka.mobile.showcase.client.activities.ButtonBarView#getBackButton()
	 */
	@Override
	public HasSimpleTouchHandler getBackButton() {
		return backButton;
	}

}
