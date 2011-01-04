/**
 * 04.01.2011
 * created by kurt
 */
package de.kurka.gwt.mobile.ui.client.menu;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author kurt
 *
 */
public class ButtonBar extends Composite implements HasWidgets {

	private FlowPanel main;

	public ButtonBar() {
		main = new FlowPanel();
		initWidget(main);

		setStylePrimaryName("mgwt-ButtonBar");
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
	 */
	@Override
	public void add(Widget w) {
		main.add(w);

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#clear()
	 */
	@Override
	public void clear() {
		main.clear();

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
	 */
	@Override
	public Iterator<Widget> iterator() {
		return main.iterator();
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
	 */
	@Override
	public boolean remove(Widget w) {
		return main.remove(w);
	}

}
