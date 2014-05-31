package com.googlecode.mgwt.ui.client;

public class OsDetectionRuntimeImpl implements OsDetection {

  @Override
  public boolean isAndroid() {
    return isAndroidPhone() || isAndroidTablet();
  }

  @Override
  public boolean isIPhone() {
    String userAgent = getUserAgent();
    if (userAgent.contains("iphone") && getDevicePixelRatio() < 2) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isIPad() {
    String userAgent = getUserAgent();
    if (userAgent.contains("ipad") && getDevicePixelRatio() < 2) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isIOs() {
    return isIPad() || isIPadRetina() || isIPhone() || isRetina();
  }

  @Override
  public boolean isRetina() {
    String userAgent = getUserAgent();
    if (userAgent.contains("iphone") && getDevicePixelRatio() >= 2) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isIPadRetina() {
    String userAgent = getUserAgent();
    if (userAgent.contains("ipad") && getDevicePixelRatio() >= 2) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isDesktop() {
    return !isIOs() && !isAndroid();
  }

  @Override
  public boolean isTablet() {
    return isIPad() || isIPadRetina() || isAndroidTablet();
  }

  @Override
  public boolean isAndroidTablet() {
    String userAgent = getUserAgent();
    if (userAgent.contains("android") && !userAgent.contains("mobile")) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isAndroidPhone() {
    String userAgent = getUserAgent();
    if (userAgent.contains("android") && userAgent.contains("mobile")) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isPhone() {
    return isIPhone() || isRetina() || isAndroidPhone();
  }

  @Override
  public boolean isBlackBerry() {
    return false;
  }

  @Override
  public boolean isAndroid4_4_OrHigher() {
    String userAgent = getUserAgent();
    if (userAgent.contains("android") && userAgent.contains("chrome")) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isAndroid2x() {
    String userAgent = getUserAgent();
    if (userAgent.contains("android 2.")) {
      return true;
    }
    return false;
  }

  native String getUserAgent() /*-{
    return $wnd.navigator.userAgent.toLowerCase();
  }-*/;

  native double getDevicePixelRatio() /*-{
    return $wnd.devicePixelRatio || 1;
  }-*/;
}
