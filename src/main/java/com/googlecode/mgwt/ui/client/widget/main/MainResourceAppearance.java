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
package com.googlecode.mgwt.ui.client.widget.main;

import com.googlecode.mgwt.ui.client.util.MGWTCssResource;

public interface MainResourceAppearance {
  interface MainCss extends MGWTCssResource {
  }

  interface UtilCss extends MGWTCssResource {
    String landscapeonly();

    String portraitonly();

    String portrait();

    String landscape();
  }

  interface SelectionCss extends MGWTCssResource {
    String userSelectNone();

    String userSelectText();

    String userSelectAll();

    String userSelectElement();
  }

  MainCss css();

  UtilCss utilCss();

  SelectionCss selectionCss();
}
