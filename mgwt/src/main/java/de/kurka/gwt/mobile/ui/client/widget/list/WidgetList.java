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
package de.kurka.gwt.mobile.ui.client.widget.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Daniel Kurka
 *
 */
public class WidgetList extends Composite implements HasWidgets {

	private static class ULFlowPanel extends ComplexPanel {

		public ULFlowPanel() {
			setElement(Document.get().createULElement());
		}

		@Override
		public void add(Widget w) {
			add(w, getElement());
		}
	}

	private Panel container;
	private Map<Widget, Widget> map;

	public WidgetList() {
		container = new ULFlowPanel();
		initWidget(container);

		setStylePrimaryName("mgwt-List");

		map = new HashMap<Widget, Widget>();
	}

	@Override
	public void add(Widget w) {
		WidgetListEntry widgetListEntry = new WidgetListEntry();
		widgetListEntry.add(w);
		map.put(w, widgetListEntry);
		container.add(widgetListEntry);

	}

	@Override
	public void clear() {
		container.clear();
		map.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return map.values().iterator();
	}

	@Override
	public boolean remove(Widget w) {
		Widget entry = map.remove(w);
		if (entry == null)
			return false;

		return container.remove(entry);

	}

}
