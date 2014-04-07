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
package com.googlecode.mgwt.ui.client.widget.progress;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple progress indicator
 */
public class ProgressIndicator extends Widget {

  private static final ProgressIndicatorAppearance DEFAULT_APPEARANCE =
      GWT.create(ProgressIndicatorAppearance.class);

  private ProgressIndicatorAppearance appearance;

  public ProgressIndicator() {
    this(DEFAULT_APPEARANCE);
  }

  public ProgressIndicator(ProgressIndicatorAppearance appearance) {
    this.appearance = appearance;
    setElement(this.appearance.uiBinder().createAndBindUi(this));
  }

  @UiFactory
  protected ProgressIndicatorAppearance getAppearance() {
	return appearance;
  }
}
