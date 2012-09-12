package com.googlecode.mgwt.ui.client.theme;

import com.google.gwt.core.client.GWT;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.OsDetection;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeAndroid;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeAndroidTablet;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeBlackberry;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeDesktop;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIPad;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIPadRetina;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIPhone;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeRetina;

public class MGWTThemeBaseThemeStandardImpl implements MGWTTheme {

  private MGWTClientBundle bundle;

  public MGWTThemeBaseThemeStandardImpl() {

    OsDetection detection = MGWT.getOsDetection();

    if (detection.isAndroidPhone()) {
      bundle = GWT.create(MGWTClientBundleBaseThemeAndroid.class);
    }

    if (detection.isAndroidTablet()) {
      bundle = GWT.create(MGWTClientBundleBaseThemeAndroidTablet.class);
    }

    if (detection.isIPhone()) {
      if (detection.isRetina()) {
        bundle = GWT.create(MGWTClientBundleBaseThemeRetina.class);
      } else {
        bundle = GWT.create(MGWTClientBundleBaseThemeIPhone.class);
      }
    }

    if (detection.isIPad()) {
      if (detection.isIPadRetina()) {
        bundle = GWT.create(MGWTClientBundleBaseThemeIPadRetina.class);
      } else {
        bundle = GWT.create(MGWTClientBundleBaseThemeIPad.class);
      }

    }

    if (detection.isBlackBerry()) {
      bundle = GWT.create(MGWTClientBundleBaseThemeBlackberry.class);
    }

    if (detection.isDesktop()) {
      bundle = GWT.create(MGWTClientBundleBaseThemeDesktop.class);
    }

  }

  @Override
  public MGWTClientBundle getMGWTClientBundle() {
    return bundle;
  }

}
