/*
 * Copyright 2014 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.panel.pull;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

public interface PullPanelAppearance {
  public interface PullPanelCss extends MGWTCssResource {

    @ClassName("mgwt-PullPanel")
    String pullPanel();

    @ClassName("mgwt-PullPanel-main")
    String pullPanelMain();

    @ClassName("mgwt-PullPanel-container")
    String pullPanelContainer();

    @ClassName("mgwt-PullToRefresh")
    public String pullToRefresh();

    @ClassName("mgwt-PullToRefresh-arrow")
    public String arrow();

    @ClassName("mgwt-PullToRefresh-text")
    public String text();

    @ClassName("mgwt-PullToRefresh-spinner")
    public String spinner();

    @ClassName("mgwt-PullToRefresh-error")
    public String error();

  }

  UiBinder<ScrollPanel, PullPanel> uiBinder();

  PullPanelCss css();

  UiBinder<Widget, PullArrowBase> arrow();
}
