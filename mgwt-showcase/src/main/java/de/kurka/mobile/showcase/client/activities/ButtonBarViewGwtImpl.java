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
import de.kurka.gwt.mobile.ui.client.menu.bar.ArrowDownButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ArrowLeftButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ArrowRightButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ArrowUpButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.BookmarkButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ButtonBar;
import de.kurka.gwt.mobile.ui.client.menu.bar.CameraButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ComposeButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ContactAddButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.DeleteButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.FastForwardButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.InfoButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.LocateButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.NewButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.NextSlideButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.OrganizeButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.PauseButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.PlayButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.PlusButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.PreviousSlideButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.RefreshButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.ReplyButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.RewindButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.SearchButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.StopButton;
import de.kurka.gwt.mobile.ui.client.menu.bar.TrashButton;
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
		footerPanel.add(new ArrowDownButton());
		footerPanel.add(new ArrowUpButton());
		footerPanel.add(new ArrowLeftButton());
		footerPanel.add(new ArrowRightButton());
		footerPanel.add(new BookmarkButton());
		footerPanel.add(new CameraButton());
		footerPanel.add(new ComposeButton());
		footerPanel.add(new ContactAddButton());
		footerPanel.add(new FastForwardButton());
		footerPanel.add(new DeleteButton());
		footerPanel.add(new InfoButton());

		footerPanel.add(new LocateButton());
		footerPanel.add(new NewButton());
		footerPanel.add(new NextSlideButton());
		footerPanel.add(new OrganizeButton());
		footerPanel.add(new PauseButton());
		footerPanel.add(new PlayButton());
		footerPanel.add(new PlusButton());
		footerPanel.add(new PreviousSlideButton());
		footerPanel.add(new ReplyButton());
		footerPanel.add(new RewindButton());
		footerPanel.add(new SearchButton());
		footerPanel.add(new StopButton());
		footerPanel.add(new TrashButton());

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
