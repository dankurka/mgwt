/**
 * 30.12.2010
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.widget.list;

import java.util.Iterator;

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
		container = new LIFlowPanel();
		initWidget(container);
		setStylePrimaryName("mgwt-List-Cell");
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
