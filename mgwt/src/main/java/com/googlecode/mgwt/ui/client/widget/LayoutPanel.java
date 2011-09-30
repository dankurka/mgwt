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

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Widget;

public class LayoutPanel extends Composite implements HasWidgets, InsertPanel {
	private FlowPanel main;
	private LayoutCss css;

	public interface LayoutCss extends CssResource {
		@ClassName("mgwt-LayoutPanel")
		String layoutPanel();

		String mainChild();
	}

	public interface LayoutPanelBundle extends ClientBundle {
		@Source("experimental/layout.css")
		LayoutCss getLayoutCss();

		public static final LayoutPanelBundle INSTANCE = GWT.create(LayoutPanelBundle.class);
	}

	public LayoutPanel() {
		css = LayoutPanelBundle.INSTANCE.getLayoutCss();
		css.ensureInjected();
		main = new FlowPanel();
		initWidget(main);

		main.addStyleName(css.layoutPanel());
	}

	@Override
	public void add(Widget w) {

		if (w instanceof ScrollPanel) {
			w.addStyleName(css.mainChild());
		}

		main.add(w);
	}

	@Override
	public void clear() {
		main.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return main.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return main.remove(w);
	}

	@Override
	public Widget getWidget(int index) {
		return main.getWidget(index);
	}

	@Override
	public int getWidgetCount() {
		return main.getWidgetCount();
	}

	@Override
	public int getWidgetIndex(Widget child) {
		return main.getWidgetIndex(child);
	}

	@Override
	public boolean remove(int index) {
		return main.remove(index);
	}

	@Override
	public void insert(Widget w, int beforeIndex) {
		main.insert(w, beforeIndex);

	}
}
