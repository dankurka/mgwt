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

import com.google.gwt.core.shared.GWT;

import com.googlecode.mgwt.ui.client.widget.main.MainResourceAppearance.UtilCss;

public class MainResourceHolder {

  private static final MainResourceAppearance DEFAULT_APPEARANCE = GWT
      .create(MainResourceAppearance.class);

  public interface MainResourceInjector {
    void inject();
  }

  public static class NoopMainResourceInjector implements MainResourceInjector {

    @Override
    public void inject() {
    }
  }

  public static class RealMainResourceInjector implements MainResourceInjector {

    @Override
    public void inject() {
      DEFAULT_APPEARANCE.css().ensureInjected();
      DEFAULT_APPEARANCE.selectionCss().ensureInjected();
      DEFAULT_APPEARANCE.utilCss().ensureInjected();
    }
  }

  public static void inject() {
    MainResourceInjector injector = GWT.create(MainResourceInjector.class);
    injector.inject();
  }

  public static UtilCss getUtilCss() {
    return DEFAULT_APPEARANCE.utilCss();
  }

  private MainResourceHolder() {
  }
}
