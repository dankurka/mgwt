/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.mobile.showcase.client.activities;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;
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
public class UIViewImpl implements UIView {

	private FlowPanel main;
	private HeaderPanel headerPanel;
	private HeaderBackButton headerBackButton;
	private CellListWithHeader<Item> cellListWithHeader;

	public UIViewImpl() {
		main = new FlowPanel();

		headerPanel = new HeaderPanel();
		main.add(headerPanel);

		headerBackButton = new HeaderBackButton();
		headerPanel.setLeftWidget(headerBackButton);

		cellListWithHeader = new CellListWithHeader<Item>(new BasicCell<Item>() {

			@Override
			public String getDisplayString(Item model) {
				return model.getDisplayString();
			}

			@Override
			public boolean canBeSelected(Item model) {
				return true;
			}
		});

		main.add(cellListWithHeader);

	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public void setBackButtonText(String text) {
		headerBackButton.setText(text);

	}

	@Override
	public HasSimpleTouchHandler getBackButton() {
		return headerBackButton;
	}

	@Override
	public void setTitle(String title) {
		headerPanel.getTitleWidget().setText(title);

	}

	@Override
	public HasCellSelectedHandler getList() {
		return cellListWithHeader.getCellList();
	}

	@Override
	public void renderItems(List<Item> items) {
		cellListWithHeader.getCellList().render(items);

	}

}
