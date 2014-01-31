package com.googlecode.mgwt.ui.client.resource;

import com.google.gwt.core.shared.GWT;

public class MainResourceHolder {

  public interface MainResourceInjector {
    void inject();
  }

  public static class NoopMainResourceInjector implements MainResourceInjector {

    @Override
    public void inject() {
    }
  }

  public static class RealMainResourceInjector implements MainResourceInjector {

    private static final MainResourceAppearance DEFAULT_APPEARANCE = GWT
        .create(MainResourceAppearance.class);

    @Override
    public void inject() {
      DEFAULT_APPEARANCE.css().ensureInjected();
    }
  }

  public static void inject() {
    MainResourceInjector injector = GWT.create(MainResourceInjector.class);
    injector.inject();
  }

  private MainResourceHolder() {
  }
}
