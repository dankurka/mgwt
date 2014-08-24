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

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.util.HandlerRegistrationConverter;
import com.googlecode.mgwt.ui.client.widget.carousel.Carousel;
import com.googlecode.mgwt.ui.client.widget.panel.flex.FlexPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * At the moment theres no support for custom parsers:
 * http://code.google.com/p/google-web-toolkit/issues/detail?id=4461
 *
 * So if you want to use TabPanel in UIBinder its a bit choppy:
 *
 * <pre>
 * &lt;mgwt:TabPanel>
 * 	&lt;mgwt:tabs>
 * 		&lt;mgwt:Tab>
 * 			&lt;mgwt:button>
 * 				&lt;mgwt:DownloadsTabBarButton>&lt;/mgwt:DownloadsTabBarButton>
 * 			&lt;/mgwt:button>
 * 			&lt;mgwt:widget>
 * 				<!-- content for that tab -->
 * 			&lt;/mgwt:widget>
 * 		&lt;/mgwt:Tab>
 * 	&lt;/mgwt:tabs>
 * &lt;/mgwt:TabPanel>
 * </pre>
 *
 * @author Daniel Kurka
 */
public class TabPanel extends Composite implements HasSelectionHandlers<Integer> {

  public static class TabBar extends Composite implements HasSelectionHandlers<Integer> {

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

    @UiField
    public FlowPanel container;
    private List<TabBarButtonBase> children = new ArrayList<TabBarButtonBase>();;
    private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();

    public TabBar() {
      this(DEFAULT_APPEARANCE);
    }

    public TabBar(TabBarAppearance appearance) {
      initWidget(appearance.barBinder().createAndBindUi(this));
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

    int getIndexForWidget(TabBarButtonBase w) {
      return children.indexOf(w);
    }

    public boolean remove(TabBarButtonBase w) {
      int indexForWidget = getIndexForWidget(w);
      children.remove(w);
      if (indexForWidget != -1) {
        handlers.get(indexForWidget).removeHandler();
        handlers.remove(indexForWidget);
      }

      return container.remove(w);
    }

    public void setSelectedButton(int index) {
      setSelectedButton(index, true);
    }

    public void setSelectedButton(int index, boolean issueEvent) {
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
      if (issueEvent) {
        SelectionEvent.fire(this, Integer.valueOf(index));
      }
    }

    @Override
    public com.google.gwt.event.shared.HandlerRegistration addSelectionHandler(
        SelectionHandler<Integer> handler) {
      return addHandler(handler, SelectionEvent.getType());
    }

    public void remove(int index) {
      TabBarButtonBase w = getWidgetForIndex(index);
      remove(w);
    }

    private TabBarButtonBase getWidgetForIndex(int index) {
      return children.get(index);
    }
  }

  public static final TabBarAppearance DEFAULT_APPEARANCE = GWT.create(TabBarAppearance.class);

  @UiField
  public FlexPanel container;

  @UiField(provided=true)
  public Carousel tabContainer;

  @UiField(provided=true)
  public TabBar tabBar;

  protected final TabBarAppearance appearance;

  public TabPanel() {
    this(DEFAULT_APPEARANCE);
  }

  public TabPanel(TabBarAppearance appearance) {
    this.appearance = appearance;
    tabContainer = new Carousel();
    tabContainer.setShowCarouselIndicator(false);
    tabBar = new TabBar(appearance);
    initWidget(appearance.panelBinder().createAndBindUi(this));

    tabBar.addSelectionHandler(new SelectionHandler<Integer>() {
      @Override
      public void onSelection(SelectionEvent<Integer> event) {
        tabContainer.setSelectedPage(event.getSelectedItem(), false);
      }
    });

    tabContainer.addSelectionHandler(new SelectionHandler<Integer>() {
      @Override
      public void onSelection(SelectionEvent<Integer> event) {
        tabBar.setSelectedButton(event.getSelectedItem(), false);
      }
    });
  }

  public void setSelectedChild(int index) {
    tabBar.setSelectedButton(index, true);
    tabContainer.setSelectedPage(index);
  }

  public void add(TabBarButtonBase button, Widget child) {
    tabContainer.add(child);
    tabBar.add(button);
  }

  /**
   * at the moment there is no support for custom parsers:
   * http://code.google.com/p/google-web-toolkit/issues/detail?id=4461 this is a workaround to allow
   * use with UIBinder
   *
   * {@link TabPanel#add(TabBarButtonBase, Widget)} if you are writing java code
   *
   * @param b the tab to add
   */
  @UiChild(tagname = "tabs")
  public void addTab(Tab b) {
    Widget w = b.getWidget();
    TabBarButtonBase button = b.getButton();

    if (button == null) {
      throw new IllegalArgumentException("button can not be null");
    }

    if (w == null) {
      throw new IllegalArgumentException("widget can not be null");
    }
    add(button, w);
  }

  @Override
  public com.google.gwt.event.shared.HandlerRegistration addSelectionHandler(
      SelectionHandler<Integer> handler) {
    return new HandlerRegistrationConverter(tabBar.addSelectionHandler(handler));
  }

  @UiFactory
  public TabBarAppearance getAppearance() {
	  return appearance;
  }
}
