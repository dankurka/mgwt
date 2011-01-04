/**
 * 28.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.celllist;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;

/**
 * @author kurt
 *
 */
public class CellListWithHeader<T> extends Composite {
	private FlowPanel main;
	private CellList<T> cellList;
	private HTML header;

	public CellListWithHeader(Cell<T> cell) {
		main = new FlowPanel();

		initWidget(main);

		header = new HTML();
		header.setStylePrimaryName("mgwt-List-Header");
		main.add(header);

		cellList = new CellList<T>(cell);
		main.add(cellList);
	}

	public HasText getHeader() {
		return header;
	}

	/**
	 * @return the cellList
	 */
	public CellList<T> getCellList() {
		return cellList;
	}
}
