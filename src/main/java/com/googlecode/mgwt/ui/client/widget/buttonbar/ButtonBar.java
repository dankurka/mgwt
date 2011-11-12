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
package com.googlecode.mgwt.ui.client.widget.buttonbar;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss;


/**
 * <p>ButtonBar class.</p>
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class ButtonBar extends Composite implements HasWidgets {

	private FlowPanel main;
	protected final ButtonBarCss css;

	/**
	 * <p>Constructor for ButtonBar.</p>
	 */
	public ButtonBar() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getButtonBarCss());
	}

	/**
	 * <p>Constructor for ButtonBar.</p>
	 *
	 * @param css a {@link com.googlecode.mgwt.ui.client.theme.base.ButtonBarCss} object.
	 */
	public ButtonBar(ButtonBarCss css) {
		this.css = css;
		css.ensureInjected();
		main = new FlowPanel();
		initWidget(main);

		setStylePrimaryName(this.css.buttonBar());
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public void add(Widget w) {
		main.add(w);

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#clear()
	 */
	/** {@inheritDoc} */
	@Override
	public void clear() {
		main.clear();

	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
	 */
	/** {@inheritDoc} */
	@Override
	public Iterator<Widget> iterator() {
		return main.iterator();
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public boolean remove(Widget w) {
		return main.remove(w);
	}

}
