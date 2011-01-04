/**
 * 29.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.button.HeaderBackButton;
import de.kurka.gwt.mobile.ui.client.panel.HeaderPanel;
import de.kurka.gwt.mobile.ui.client.widget.celllist.CellListWithHeader;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;
import de.kurka.mobile.showcase.client.BasicCell;

/**
 * @author kurt
 *
 */
public class AnimationViewImpl implements AnimationView {

	private CellListWithHeader<Animation> list;
	private FlowPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton headerBackButton;

	/**
	 * 
	 */
	public AnimationViewImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();

		headerBackButton = new HeaderBackButton();
		headerPanel.setLeftWidget(headerBackButton);

		main.add(headerPanel);

		list = new CellListWithHeader<Animation>(new BasicCell<Animation>() {

			@Override
			public String getDisplayString(Animation model) {
				return model.getName();
			}

			@Override
			public boolean canBeSelected(Animation model) {
				return true;
			}
		});
		main.add(list);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void setTitle(String text) {
		headerPanel.getTitleWidget().setText(text);

	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return headerBackButton;
	}

	@Override
	public HasCellSelectedHandler getCellSelectedHandler() {
		return list.getCellList();
	}

	@Override
	public void setLeftButtonText(String text) {
		headerBackButton.setText(text);

	}

	@Override
	public void setAnimations(List<Animation> animations) {
		list.getCellList().render(animations);

	}

	@Override
	public HasText getFirstHeader() {
		return list.getHeader();
	}

}
