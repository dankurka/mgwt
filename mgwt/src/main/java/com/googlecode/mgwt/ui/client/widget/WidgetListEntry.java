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
package com.googlecode.mgwt.ui.client.widget;

import java.util.Iterator;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;


/**
 * @author Daniel Kurka
 *
 */
public class WidgetListEntry extends Composite implements HasWidgets {
	private static class LIFlowPanel extends ComplexPanel {

		public LIFlowPanel() {
			setElement(Document.get().createLIElement());

		}

		@Override
		public void add(Widget w) {
			add(w, getElement());
		}
	}

	private Panel container;

	/**
	 * 
	 */
	public WidgetListEntry() {
		this(MGWTStyle.getDefaultClientBundle().getListCss());
	}

	public WidgetListEntry(ListCss css) {
		container = new LIFlowPanel();
		initWidget(container);

	}

	@Override
	public void add(Widget w) {
		container.add(w);

	}

	@Override
	public void clear() {
		container.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return container.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return container.remove(w);
	}
}
