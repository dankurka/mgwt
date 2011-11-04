/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.celllist;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.widget.CellList;

/**
 * <p>
 * CellListWithHeader class.
 * </p>
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class CellListWithHeader<T> extends Composite {
	private FlowPanel main;
	private CellList<T> cellList;
	private HTML header;

	/**
	 * <p>
	 * Constructor for CellListWithHeader.
	 * </p>
	 * 
	 * @param cell a {@link com.googlecode.mgwt.ui.client.widget.celllist.Cell}
	 *            object.
	 */
	public CellListWithHeader(Cell<T> cell) {
		this(cell, MGWTStyle.getDefaultClientBundle().getListCss());
	}

	/**
	 * <p>
	 * Constructor for CellListWithHeader.
	 * </p>
	 * 
	 * @param cell a {@link com.googlecode.mgwt.ui.client.widget.celllist.Cell}
	 *            object.
	 * @param css a {@link com.googlecode.mgwt.ui.client.theme.base.ListCss}
	 *            object.
	 */
	public CellListWithHeader(Cell<T> cell, ListCss css) {
		css.ensureInjected();
		main = new FlowPanel();

		initWidget(main);

		header = new HTML();
		header.setStylePrimaryName(css.listHeader());
		main.add(header);

		cellList = new CellList<T>(cell);
		main.add(cellList);
	}

	/**
	 * <p>
	 * Getter for the field <code>header</code>.
	 * </p>
	 * 
	 * @return a {@link com.google.gwt.user.client.ui.HasText} object.
	 */
	public HasText getHeader() {
		return header;
	}

	/**
	 * <p>
	 * Getter for the field <code>cellList</code>.
	 * </p>
	 * 
	 * @return the cellList
	 */
	public CellList<T> getCellList() {
		return cellList;
	}
}
