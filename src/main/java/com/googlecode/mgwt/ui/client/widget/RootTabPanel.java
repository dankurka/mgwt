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

import java.util.LinkedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndEvent;
import com.googlecode.mgwt.dom.client.event.animation.TransitionEndHandler;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.theme.base.TabBarCss;
import com.googlecode.mgwt.ui.client.util.CssUtil;
import com.googlecode.mgwt.ui.client.util.HandlerRegistrationConverter;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

/**
 * This TabPanel has support for content that can be animated.
 * 
 * This is intended to be used as a root element of an app where the content of
 * the tab panel is switched according to state.
 * 
 * It does not store the content of all tabs in it like {@link TabPanel}. It
 * only has a AnimateAble display that can change content with animations.
 * 
 * You can use this panel with an {@link AnimatingActivityManager} or an
 * {@link AnimationHelper}. Simply register for
 * {@link #addSelectionHandler(SelectionHandler)} and trigger the animation you
 * want
 * 
 * @author Daniel Kurka
 */
public class RootTabPanel extends Composite implements HasSelectionHandlers<Integer> {

	private LayoutPanel container;

	private FlowPanel aniContainer;

	private TabBar tabBar;

	private final AnimatableDisplay animatableDisplay;

	private HandlerRegistration addDomHandler;

	private HandlerRegistration addDomHandler1;

	public RootTabPanel() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss(), (AnimatableDisplay) GWT.create(AnimatableDisplay.class));
	}

	public RootTabPanel(TabBarCss css, AnimatableDisplay display) {
		this.animatableDisplay = display;
		container = new LayoutPanel();
		initWidget(container);

		aniContainer = new FlowPanel();
		// TODO
		aniContainer.getElement().getStyle().setPosition(Position.RELATIVE);

		container.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());

		aniContainer.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());

		aniContainer.add(animatableDisplay);

		tabBar = new TabBar(css);

		tabBar.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				event.getSelectedItem();
				// fire place event
			}
		});

		if (MGWT.getOsDetection().isAndroid()) {
			container.add(tabBar);
			container.add(aniContainer);

		} else {
			container.add(aniContainer);
			container.add(tabBar);

		}
		tabBar.getElement().getStyle().setProperty("WebkitTransformProperty", "opacity");

	}

	public void showTabBar(boolean show) {
		if (show) {
			CssUtil.setTransitionDuration(tabBar.getElement(), 200);

			CssUtil.setOpacity(tabBar.getElement(), 0);

			addDomHandler1 = tabBar.addDomHandler(new TransitionEndHandler() {

				@Override
				public void onTransitionEnd(TransitionEndEvent event) {
					if (addDomHandler1 != null) {
						addDomHandler1.removeHandler();
						tabBar.setVisible(true);
						addDomHandler1 = null;
					}

				}
			}, TransitionEndEvent.getType());
		} else {
			CssUtil.setTransitionDuration(tabBar.getElement(), 200);

			CssUtil.setOpacity(tabBar.getElement(), 0);

			addDomHandler = tabBar.addDomHandler(new TransitionEndHandler() {

				@Override
				public void onTransitionEnd(TransitionEndEvent event) {
					if (addDomHandler != null) {
						addDomHandler.removeHandler();
						tabBar.setVisible(false);
						addDomHandler = null;
					}

				}
			}, TransitionEndEvent.getType());

		}
	}

	public AnimatableDisplay getAnimatableDisplay() {
		return animatableDisplay;
	}

	/**
	 * <p>
	 * setSelectedChild
	 * </p>
	 * 
	 * @param index a int.
	 */
	public void setSelectedChild(int index) {
		tabBar.setSelectedButton(index, true);

	}

	public void add(TabBarButtonBase button) {

		tabBar.add(button);
	}

	public void remove(int index) {

		tabBar.remove(index);
	}

	public void remove(TabBarButtonBase w) {
		int childIndex = tabBar.getIndexForWidget(w);
		tabBar.remove(childIndex);
	}

	/** {@inheritDoc} */
	@Override
	public com.google.gwt.event.shared.HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return new HandlerRegistrationConverter(tabBar.addSelectionHandler(handler));
	}

	public static class TabBar extends Composite implements HasSelectionHandlers<Integer> {

		private FlowPanel container;
		private LinkedList<TabBarButtonBase> children;
		private LinkedList<HandlerRegistration> handlers = new LinkedList<HandlerRegistration>();
		protected final TabBarCss css;

		public TabBar() {
			this(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss());
		}

		public TabBar(TabBarCss css) {
			this.css = css;
			css.ensureInjected();
			children = new LinkedList<TabBarButtonBase>();
			container = new FlowPanel();
			container.setStylePrimaryName(css.tabbar());
			initWidget(container);
		}

		private class InternalTouchHandler implements TapHandler {

			private final TabBarButtonBase button;

			public InternalTouchHandler(TabBarButtonBase button) {
				this.button = button;

			}

			@Override
			public void onTap(TapEvent event) {
				setSelectedButton(getIndexForWidget(button));
			}

		}

		public void add(TabBarButtonBase w) {
			if (children.size() == 0) {
				w.setSelected(true);
			}
			container.add(w);
			children.add(w);
			handlers.add(w.addTapHandler(new InternalTouchHandler(w)));

		}

		public void clear() {
			container.clear();
			children.clear();
			handlers.clear();

		}

		public int getIndexForWidget(TabBarButtonBase w) {
			return children.indexOf(w);
		}

		public boolean remove(TabBarButtonBase w) {
			children.remove(w);
			int indexForWidget = getIndexForWidget(w);
			if (indexForWidget != -1) {
				handlers.get(indexForWidget).removeHandler();
				handlers.remove(indexForWidget);
			}

			return container.remove(w);

		}

		public void setSelectedButton(int index) {
			setSelectedButton(index, false);
		}

		public void setSelectedButton(int index, boolean suppressEvent) {
			if (index < 0) {
				throw new IllegalArgumentException("invalud index");
			}

			if (index >= children.size()) {
				throw new IllegalArgumentException("invalud index");
			}
			int count = 0;
			for (TabBarButtonBase button : children) {
				if (count == index) {
					button.setSelected(true);
				} else {
					button.setSelected(false);
				}
				count++;
			}
			if (!suppressEvent)
				SelectionEvent.fire(this, Integer.valueOf(index));
		}

		@Override
		public com.google.gwt.event.shared.HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
			return addHandler(handler, SelectionEvent.getType());
		}

		/**
		 * @param index
		 */
		public void remove(int index) {
			TabBarButtonBase w = getWidgetForIndex(index);
			remove(w);
		}

		/**
		 * @param index
		 * @return
		 */
		private TabBarButtonBase getWidgetForIndex(int index) {
			return children.get(index);
		}

	}

}
