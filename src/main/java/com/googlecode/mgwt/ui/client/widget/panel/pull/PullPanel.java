/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.ui.client.widget.panel.pull;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.mgwt.ui.client.widget.base.HasRefresh;
import com.googlecode.mgwt.ui.client.widget.layout.IsFlexible;
import com.googlecode.mgwt.ui.client.widget.panel.pull.PullPanel.PullWidget.PullState;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollEndEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollMoveEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollRefreshEvent;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollStartEvent;

import java.util.Iterator;

public class PullPanel extends Composite implements HasWidgets, HasRefresh, IsFlexible {

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
    /**
     * this method get called if the pull state of the panel changes
     * 
     * @param pullWidget the PullWidget set for the region (header, footer)
     * @param state the current state of the pull panel
     */
    public void onPullStateChanged(PullWidget pullWidget, PullState state);

    /**
     * called if a pull got executed
     * 
     * @param pullWidget the PullWidget set for the region (header, footer)
     */
    public void onPullAction(PullWidget pullWidget);

  }

  public static final PullPanelAppearance DEFAULT_APPEARANCE = GWT.create(PullPanelAppearance.class);

  @UiField
  protected FlowPanel main;

  @UiField
  protected ScrollPanel scrollPanel;
  @UiField
  protected FlowPanel container;

  protected PullWidget header;
  protected PullWidget footer;

  protected PullState headerState = PullState.NORMAL;

  protected Pullhandler headerPullhandler;

  protected Pullhandler footerPullhandler;
  protected PullState footerState = PullState.NORMAL;

  private PullPanelAppearance apperance;

  public PullPanel() {
    this(DEFAULT_APPEARANCE);
  }

  public PullPanel(PullPanelAppearance apperance) {
    this.apperance = apperance;
    scrollPanel = this.apperance.uiBinder().createAndBindUi(this);
    initWidget(scrollPanel);

    scrollPanel.setBounceFactor(1.0);

    scrollPanel.addScrollRefreshHandler(new ScrollRefreshEvent.Handler() {

      @Override
      public void onScrollRefresh(ScrollRefreshEvent event) {

        if (header != null) {

          headerState = PullState.NORMAL;

        }

        if (footer != null) {
          footerState = PullState.NORMAL;

        }
      }
    });

    scrollPanel.addScrollStartHandler(new ScrollStartEvent.Handler() {
      @Override
      public void onScrollStart(ScrollStartEvent event) {
        if (header != null && headerPullhandler != null) {
          headerState = PullState.NORMAL;
          headerPullhandler.onPullStateChanged(header, headerState);
        }

        if (footer != null && footerPullhandler != null) {
          footerState = PullState.NORMAL;
          footerPullhandler.onPullStateChanged(footer, footerState);
        }
      }
    });

    scrollPanel.addScrollMoveHandler(new ScrollMoveEvent.Handler() {

      @Override
      public void onScrollMove(ScrollMoveEvent event) {
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

        int y_off = y;

        // footer
        if (footer != null && y < -footer.getHeight()) {

          if (footerState == PullState.PULLED) {
            y_off = y_off - footer.getHeight();
          }

          if (footerState == PullState.NORMAL) {
            y_off = y_off + footer.getHeight();
          }

          if (y_off < (scrollPanel.getMaxScrollY() - footer.getStateSwitchPosition())
              && footerState != PullState.PULLED) {
            footerState = PullState.PULLED;

            scrollPanel.setMaxScrollY(scrollPanel.getMaxScrollY() - footer.getHeight());

            if (footerPullhandler != null) {
              footerPullhandler.onPullStateChanged(footer, footerState);
            }
          } else {
            if (y_off > (scrollPanel.getMaxScrollY() - footer.getStateSwitchPosition())
                && footerState != PullState.NORMAL) {
              footerState = PullState.NORMAL;
              scrollPanel.setMaxScrollY(scrollPanel.getMaxScrollY() + footer.getHeight());
              if (footerPullhandler != null) {
                footerPullhandler.onPullStateChanged(footer, footerState);
              }
            }
          }

          footer.onScroll(y_off - scrollPanel.getMaxScrollY());
        }

      }
    });

    scrollPanel.addScrollEndHandler(new ScrollEndEvent.Handler() {

      @Override
      public void onScrollEnd(ScrollEndEvent event) {

        if (header != null) {
          if (headerState == PullState.PULLED) {
            headerState = PullState.NORMAL;
            if (headerPullhandler != null)
              headerPullhandler.onPullAction(header);
          }
        }

        if (footer != null) {
          if (footerState == PullState.PULLED) {
            footerState = PullState.NORMAL;

            if (footerPullhandler != null)
              footerPullhandler.onPullAction(footer);
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
    } else {
      scrollPanel.setOffSetY(0);
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
      scrollPanel.setOffSetMaxY(this.footer.getHeight());
    } else {
      scrollPanel.setOffSetMaxY(0);
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
    return container.remove(w);
  }

  public void setHeaderPullHandler(Pullhandler headerPullhandler) {
    this.headerPullhandler = headerPullhandler;
  }

  @Deprecated
  public void setHeaderPullhandler(Pullhandler headerPullhandler) {
    setHeaderPullHandler(headerPullhandler);
  }

  public void setFooterPullHandler(Pullhandler headerPullhandler) {
    this.footerPullhandler = headerPullhandler;
  }

  public ScrollPanel getScrollPanel() {
    return scrollPanel;
  }
}
