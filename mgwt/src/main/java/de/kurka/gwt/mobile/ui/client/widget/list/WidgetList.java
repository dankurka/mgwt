/**
 * 30.12.2010
 * created by kurt
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
 * @author kurt
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
