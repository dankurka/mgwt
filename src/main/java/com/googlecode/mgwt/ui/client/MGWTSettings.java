/*
 * Copyright 2010 Daniel Kurka
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
package com.googlecode.mgwt.ui.client;

import com.googlecode.mgwt.ui.client.MGWTSettings.ViewPort.DENSITY;

/**
 * This class acts as a container for all settings for an mgwt app.
 * 
 * Those include things like the viewport, icons and scrolling behaviour.
 * 
 * @author Daniel Kurka
 */
public class MGWTSettings {

  /**
   * This class represents the mobile viewport
   * 
   * @author Daniel Kurka
   * 
   */
  public static class ViewPort {

    /**
     * densities of the viewport
     * 
     * @author Daniel Kurka
     * 
     */
    public enum DENSITY {
      /**
       * low density
       */
      LOW("low-dpi"),
      /**
       * medium density
       */
      MEDIUM("medium-dpi"),
      /**
       * high density
       */
      HIGH("high-dpi"),
      /**
       * devive dpi
       */
      DEVICE("device-dpi");

      private final String value;

      private DENSITY(String value) {
        this.value = value;

      }

      /**
       * get the value of density
       * 
       * @return the value of density
       */
      public String getValue() {
        return value;
      }
    };

    private String width;
    private String height;

    private double initialScale = 1;
    private double minimumScale = 1;
    private double maximumScale = 1;
    private boolean userScaleAble = false;

    private String targetDensity;

    /**
     * Set the width of the viewport
     * 
     * @param value the width in px
     * @return the viewport instance
     */
    public ViewPort setWidth(int value) {
      this.width = "" + value;
      return this;
    }

    /**
     * Set the height of the viewport
     * 
     * @param value the height in px
     * @return the viewport instance
     */
    public ViewPort setHeight(int value) {
      this.height = "" + value;
      return this;
    }

    /**
     * Set width to device width
     * 
     * Most common for most apps
     * 
     * @return the viewport instance
     */
    public ViewPort setWidthToDeviceWidth() {
      this.width = "device-width";
      return this;
    }

    /**
     * Set the height to device height
     * 
     * 
     * @return the viewport instance
     */
    public ViewPort setHeightToDeviceHeight() {
      this.height = "device-height";
      return this;
    }

    /**
     * <p>
     * android only
     * </p>
     * set the target density in dpi
     * 
     * 
     * @param value the target density in dpi
     * @return the viewport instance
     */
    public ViewPort setTargetDensity(int value) {
      targetDensity = "" + value;
      return this;
    }

    /**
     * <p>
     * android only
     * </p>
     * 
     * set the target density
     * 
     * @param d the density to use
     * @return the viewport instance
     */
    public ViewPort setTargetDensity(DENSITY d) {
      targetDensity = d.getValue();
      return this;
    }

    /**
     * Should the viewport be scalable by the user
     * 
     * @param userScaleAble ture to allow scaling
     * @return the viewport instance
     */
    public ViewPort setUserScaleAble(boolean userScaleAble) {
      this.userScaleAble = userScaleAble;
      return this;
    }

    /**
     * set the minimum scaling of the viewport
     * 
     * @param minimumScale the scale to use
     * @return the viewport instance
     */
    public ViewPort setMinimumScale(double minimumScale) {
      this.minimumScale = minimumScale;
      return this;
    }

    /**
     * Set the maximum scale of the viewport
     * 
     * @param maximumScale the scale to use
     * @return the viewport instance
     */
    public ViewPort setMaximumScale(double maximumScale) {
      this.maximumScale = maximumScale;
      return this;
    }

    /**
     * set the initial scale of the viewport
     * 
     * @param initialScale the scale to use
     * @return the viewport instance
     */
    public ViewPort setInitialScale(double initialScale) {
      this.initialScale = initialScale;
      return this;
    }

    /**
     * get the content for the viewport meta tag
     * 
     * @return content of the viewport meta tag
     */
    public String getContent() {
      StringBuffer buffer = new StringBuffer();

      // initial scale
      buffer.append("initial-scale=" + initialScale);
      // minimum scale
      buffer.append(",minimum-scale=" + minimumScale);
      // maximum scale
      buffer.append(",maximum-scale=" + maximumScale);

      // width
      if (width != null) {
        buffer.append(",width=" + width);
      }

      // height
      if (height != null) {
        buffer.append(",height=" + height);
      }

      // user scaleable
      if (!userScaleAble) {
        buffer.append(",user-scalable=no");
      }
      if (targetDensity != null && MGWT.getOsDetection().isAndroid()) {
        buffer.append(",target-densitydpi=" + targetDensity);
      }
      return buffer.toString();
    }

  }

  /**
   * get the default setting for building an app
   * 
   * @return the default setting for building an app
   */
  public static MGWTSettings getAppSetting() {
    ViewPort viewPort = new MGWTSettings.ViewPort();
    viewPort.setTargetDensity(DENSITY.MEDIUM);
    viewPort.setUserScaleAble(false).setMinimumScale(1.0).setInitialScale(1.0).setMaximumScale(1.0);

    MGWTSettings settings = new MGWTSettings();
    settings.setViewPort(viewPort);
    settings.setAddGlosToIcon(true);
    settings.setFullscreen(true);
    settings.setPreventScrolling(true);

    return settings;
  }

  /**
   * <p>
   * ios only
   * </p>
   * 
   * All possible styles for the statusbar if the app is running in fullscreen
   * 
   * @author Daniel Kurka
   * 
   */
  public enum StatusBarStyle {
    /**
     * leave the status bar as is
     */
    DEFAULT,
    /**
     * render a black status bar
     */
    BLACK,
    /**
     * render a black translucent status bar
     */
    BLACK_TRANSLUCENT;
  };

  private ViewPort viewPort;

  private boolean gloss;

  private String iconUrl;

  private String startUrl;

  private boolean fullscreen;

  private String statusBar;

  private boolean preventScrolling;

  private boolean disablePhoneNumberDetection;

  private StatusBarStyle statusBarStyle;

  /**
   * Get the viewport for the mgwt app
   * 
   * @return the viewport
   */
  public ViewPort getViewPort() {
    return viewPort;
  }

  /**
   * Set the viewport the the mgwt app
   * 
   * @param viewPort the viewport to use
   */
  public void setViewPort(ViewPort viewPort) {
    this.viewPort = viewPort;
  }

  /**
   * If the app is added to the home screen on an ios device we can select if we like a gloss added
   * to the icon of the app
   * 
   * <p>
   * only relevant on ios devices
   * </p>
   * 
   * @return true if gloss should be added, otherwise false
   */
  public boolean isAddGlosToIcon() {
    return gloss;
  }

  /**
   * If the app is added to the home screen on an ios device we can select if we like a gloss added
   * to the icon of the app
   * 
   * <p>
   * only relevant on ios devices
   * </p>
   * 
   * @param gloss true if gloss should be added, otherwise false
   */
  public void setAddGlosToIcon(boolean gloss) {
    this.gloss = gloss;
  }

  /**
   * The icon url to use on the home screen on ios
   * 
   * @return the icon url
   */
  public String getIconUrl() {
    return iconUrl;
  }

  /**
   * Set the icon url to use on the home screen on ios
   * 
   * @param url the url of the icon to use
   */
  public void setIconUrl(String url) {
    this.iconUrl = url;
  }

  /**
   * Get the url to the image to use at startup if running on home screen
   * 
   * @return the url of the image
   */
  public String getStartUrl() {
    return startUrl;
  }

  /**
   * Set the url to the image to use at startup if running on home screen
   * 
   * @param startUrl the url to use
   */
  public void setStartUrl(String startUrl) {
    this.startUrl = startUrl;
  }

  /**
   * Can the app be used in full screen mode
   * 
   * @return true if the app can be used in full screen mode
   */
  public boolean isFullscreen() {
    return fullscreen;
  }

  /**
   * Can the app be used in full screen
   * 
   * @param fullscreen true if app can be run in full screen
   */
  public void setFullscreen(boolean fullscreen) {
    this.fullscreen = fullscreen;
  }

  /**
   * <p>
   * Getter for the field <code>statusBar</code>.
   * </p>
   * 
   * @return a {@link java.lang.String} object.
   */
  public String getStatusBar() {
    return statusBar;
  }

  /**
   * <p>
   * Setter for the field <code>statusBar</code>.
   * </p>
   * 
   * @param statusBar a {@link java.lang.String} object.
   */
  public void setStatusBar(String statusBar) {
    this.statusBar = statusBar;
  }

  /**
   * Should mgwt prevent default scrolling behaviour
   * 
   * @return true if mgwt should prevent default scrolling behaviour
   */
  public boolean isPreventScrolling() {
    return preventScrolling;
  }

  /**
   * Should mgwt prevent default scrolling behaviour
   * 
   * @param preventScrolling true if mgwt should prevent default scrolling behaviour
   */
  public void setPreventScrolling(boolean preventScrolling) {
    this.preventScrolling = preventScrolling;
  }

  /**
   * <p>
   * ios only
   * </p>
   * disable the auto dection of phonenumbers in your app
   * 
   * @param disablePhoneNumberDetection true to disable
   */
  public void setDisablePhoneNumberDetection(boolean disablePhoneNumberDetection) {
    this.disablePhoneNumberDetection = disablePhoneNumberDetection;
  }

  /**
   * 
   * <p>
   * ios only
   * </p>
   * disable the auto dection of phonenumbers in your app
   * 
   * @return true to disable
   */
  public boolean isDisablePhoneNumberDetection() {
    return disablePhoneNumberDetection;
  }

  /**
   * <p>
   * ios only
   * </p>
   * 
   * set the style of the status bar if the app is running in full screen
   * 
   * @param statusBarStyle the style to use
   */
  public void setStatusBarStyle(StatusBarStyle statusBarStyle) {
    this.statusBarStyle = statusBarStyle;
  }

  /**
   * <p>
   * ios only
   * </p>
   * 
   * get the style of the status bar if the app is running in full screen
   * 
   * @return statusBarStyle the style to use
   */
  public StatusBarStyle getStatusBarStyle() {
    return statusBarStyle;
  }
}
