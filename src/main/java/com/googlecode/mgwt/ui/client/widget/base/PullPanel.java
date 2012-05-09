/*
 * Copyright 2012 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.base;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.base.PullPanel.PullWidget.PullState;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollMoveEvent;
import com.googlecode.mgwt.ui.client.widget.event.scroll.ScrollRefreshEvent;

public class PullPanel extends Composite implements HasWidgets {

	public interface PullWidget extends IsWidget {

		enum PullState {
			NORMAL, PULLED
		}

		public void onScroll(int positionY);

		public int getHeight();

		/**
		 * get the position in px that triggers a state change
		 * 
		 * @return the position in px that triggers the state change
		 */
		public int getStateSwitchPosition();

		/**
		 * set the html of a pull header
		 * 
		 * @param html the html as String
		 */
		public void setHTML(String html);

	}

	public interface Pullhandler {
		public void onPullStateChanged(PullWidget pullWidget, PullState state);

		public void onPullAction(PullWidget pullWidget);

	}

	private FlowPanel main;

	private ScrollPanel scrollPanel;

	private PullWidget header;
	private PullWidget footer;
	private FlowPanel container;

	private PullState headerState = PullState.NORMAL;

	private Pullhandler headerPullhandler;

	public PullPanel() {
		scrollPanel = new ScrollPanel();
		initWidget(scrollPanel);

		main = new FlowPanel();
		scrollPanel.setWidget(main);

		scrollPanel.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());

		container = new FlowPanel();
		main.add(container);

		scrollPanel.setBounceFactor(1.0);

		scrollPanel.addScrollRefreshHandler(new ScrollRefreshEvent.Handler() {

			@Override
			public void onEvent(ScrollRefreshEvent event) {
				if (header != null) {

					headerState = PullState.NORMAL;

				}
			}
		});

		scrollPanel.addScrollMoveHandler(new ScrollMoveEvent.Handler() {

			@Override
			public void onEvent(ScrollMoveEvent event) {
				int y = scrollPanel.getY();

				if (header != null) {

					if (y > header.getStateSwitchPosition() && headerState != PullState.PULLED) {
						headerState = PullState.PULLED;
						scrollPanel.setMinScrollY(0);

						if (headerPullhandler != null)
							headerPullhandler.onPullStateChanged(header, headerState);

					} else {
						if (y <= header.getStateSwitchPosition() && headerState != PullState.NORMAL) {
							headerState = PullState.NORMAL;
							scrollPanel.setMinScrollY(-header.getHeight());

							if (headerPullhandler != null)
								headerPullhandler.onPullStateChanged(header, headerState);
						}

					}
					header.onScroll(y);

				}

			}
		});

		scrollPanel.addScrollEndHandler(new ScrollEndEvent.Handler() {

			@Override
			public void onEvent(ScrollEndEvent event) {

				if (header != null) {
					if (headerState == PullState.PULLED) {
						headerState = PullState.NORMAL;
						if (headerPullhandler != null)
							headerPullhandler.onPullAction(header);
					}
				}
			}
		});

	}

	public void setHeader(PullWidget header) {
		if (this.header != null) {
			this.main.remove(this.header);
		}
		this.header = header;
		if (this.header != null) {
			main.insert(this.header, 0);
			scrollPanel.setOffSetY(this.header.getHeight());
		}

		scrollPanel.refresh();
	}

	public void setFooter(PullWidget footer) {
		if (this.footer != null) {
			this.main.remove(this.footer);
		}
		this.footer = footer;
		if (this.footer != null) {
			main.insert(this.footer, main.getWidgetCount());
		}

		scrollPanel.refresh();
	}

	public void refresh() {
		scrollPanel.refresh();

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
		return remove(container);
	}

	@Override
	protected void onAttach() {
		super.onAttach();

	}

	public void setHeaderPullhandler(Pullhandler headerPullhandler) {
		this.headerPullhandler = headerPullhandler;
	}

}
