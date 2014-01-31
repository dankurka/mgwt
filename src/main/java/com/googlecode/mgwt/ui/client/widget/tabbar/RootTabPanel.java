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
package com.googlecode.mgwt.ui.client.widget.tabbar;


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
//public class RootTabPanel extends Composite implements HasSelectionHandlers<Integer> {
//
//	private RootLayoutPanel container;
//
//	private FlowPanel aniContainer;
//
//	private TabPanel.TabBar tabBar;
//
//	private final AnimatableDisplay animatableDisplay;
//
//	private HandlerRegistration addDomHandler;
//
//	private HandlerRegistration addDomHandler1;
//
//	public RootTabPanel() {
//		this(new AnimationWidget());
//	}
//
//	public RootTabPanel(AnimatableDisplay display) {
//		this.animatableDisplay = display;
//		container = new RootLayoutPanel();
//		initWidget(container);
//
//		aniContainer = new FlowPanel();
//		// TODO
//		aniContainer.getElement().getStyle().setPosition(Position.RELATIVE);
//
////		container.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
////
////		aniContainer.addStyleName(MGWTStyle.getTheme().getMGWTClientBundle().getLayoutCss().fillPanelExpandChild());
//
//		aniContainer.add(animatableDisplay);
//		
//		// TODO
//		FlowPanel flowPanel = new FlowPanel();
//
//		tabBar = new TabPanel.TabBar(flowPanel);
//
//		setDisplayTabBarOnTop(MGWT.getOsDetection().isAndroid());
//		tabBar.getElement().getStyle().setProperty("WebkitTransformProperty", "opacity");
//
//	}
//
//	public void showTabBar(boolean show) {
//		if (show) {
//			CssUtil.setTransitionDuration(tabBar.getElement(), 200);
//
//			CssUtil.setOpacity(tabBar.getElement(), 0);
//
//			addDomHandler1 = tabBar.addDomHandler(new TransitionEndHandler() {
//
//				@Override
//				public void onTransitionEnd(TransitionEndEvent event) {
//					if (addDomHandler1 != null) {
//						addDomHandler1.removeHandler();
//						tabBar.setVisible(true);
//						addDomHandler1 = null;
//					}
//
//				}
//			}, TransitionEndEvent.getType());
//		} else {
//			CssUtil.setTransitionDuration(tabBar.getElement(), 200);
//
//			CssUtil.setOpacity(tabBar.getElement(), 0);
//
//			addDomHandler = tabBar.addDomHandler(new TransitionEndHandler() {
//
//				@Override
//				public void onTransitionEnd(TransitionEndEvent event) {
//					if (addDomHandler != null) {
//						addDomHandler.removeHandler();
//						tabBar.setVisible(false);
//						addDomHandler = null;
//					}
//
//				}
//			}, TransitionEndEvent.getType());
//
//		}
//	}
//
//	public AnimatableDisplay getAnimatableDisplay() {
//		return animatableDisplay;
//	}
//
//	/**
//	 * <p>
//	 * setSelectedChild
//	 * </p>
//	 * 
//	 * @param index a int.
//	 */
//	public void setSelectedChild(int index) {
//		tabBar.setSelectedButton(index, true);
//
//	}
//
//	public void add(TabBarButtonBase button) {
//
//		tabBar.add(button);
//	}
//
//	public void remove(int index) {
//
//		tabBar.remove(index);
//	}
//
//	public void remove(TabBarButtonBase w) {
//		int childIndex = tabBar.getIndexForWidget(w);
//		tabBar.remove(childIndex);
//	}
//
//	/** {@inheritDoc} */
//	@Override
//	public com.google.gwt.event.shared.HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
//		return new HandlerRegistrationConverter(tabBar.addSelectionHandler(handler));
//	}
//
//	public void setDisplayTabBarOnTop(boolean top) {
//		container.clear();
//		if (top) {
//			container.add(tabBar);
//			container.add(aniContainer);
//
//		} else {
//			container.add(aniContainer);
//			container.add(tabBar);
//
//		}
//	}
//
//}
