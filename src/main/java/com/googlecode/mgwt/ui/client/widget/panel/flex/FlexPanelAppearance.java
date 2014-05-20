/*
 * Copyright 2014 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.panel.flex;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Panel;
import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface FlexPanelAppearance {
  public interface FlexPanelCss extends MGWTCssResource {

    @ClassName("mgwt-FlexPanel")
    String flexPanel();

    @ClassName("mgwt-RootFlexPanel")
    String rootFlexPanel();

    @ClassName("mgwt-FlexPanel-flex")
    String flexible();
  }

  FlexPanelCss css();

  UiBinder<Panel, FlexPanel> uiBinder();
}
