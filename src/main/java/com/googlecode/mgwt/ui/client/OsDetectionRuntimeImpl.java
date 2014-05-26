package com.googlecode.mgwt.ui.client;

public class OsDetectionRuntimeImpl implements OsDetection {

  private static native String getUserAgent() /*-{
    return $wnd.navigator.userAgent.toLowerCase();
  }-*/;

  private static native double getDevicePixelRatio() /*-{
    return $wnd.devicePixelRatio || 1;
  }-*/;


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
    if (userAgent.contains("ipad") && !userAgent.contains("mobile")) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isAndroidPhone() {
    String userAgent = getUserAgent();
    if (userAgent.contains("ipad") && userAgent.contains("mobile")) {
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
}
