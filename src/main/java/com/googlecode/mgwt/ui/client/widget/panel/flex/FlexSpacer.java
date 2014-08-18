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
package com.googlecode.mgwt.ui.client.widget.panel.flex;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

public class FlexSpacer extends Widget {

  public FlexSpacer() {
    this(1);
  }

  public FlexSpacer(double flex) {
    setElement(Document.get().createDivElement());
    setFlex(flex);
  }

  public void setFlex(double flex) {
    FlexPropertyHelper.setFlex(getElement(), flex);
  }
}
