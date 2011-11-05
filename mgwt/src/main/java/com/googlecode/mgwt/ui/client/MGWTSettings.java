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
package com.googlecode.mgwt.ui.client;

/**
 * <p>
 * MGWTSettings class.
 * </p>
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class MGWTSettings {

	public static class ViewPort {

		public enum DENSITY {
			LOW("low-dpi"), MEDIUM("medium-dpi"), HIGH("high-dpi"), DEVICE("device-dpi");

			private final String value;

			private DENSITY(String value) {
				this.value = value;

			}

			public String getValue() {
				return value;
			}
		};

		private String width = "device-width";
		private String height;

		private double initialScale = 1;
		private double minimumScale = 1;
		private double maximumScale = 1;
		private boolean userScaleAble = false;

		private String targetDensity;

		public ViewPort setWidth(int value) {
			this.width = "" + value;
			return this;
		}

		public ViewPort setHeight(int value) {
			this.height = "" + value;
			return this;
		}

		public ViewPort setWidthToDeviceWidth() {
			this.width = "device-width";
			return this;
		}

		public ViewPort setHeightToDeviceHeight() {
			this.height = "device-height";
			return this;
		}

		public ViewPort setTargetDensity(int value) {
			targetDensity = "" + value;
			return this;
		}

		public ViewPort setTargetDensity(DENSITY d) {
			targetDensity = d.getValue();
			return this;
		}

		public ViewPort setUserScaleAble(boolean userScaleAble) {
			this.userScaleAble = userScaleAble;
			return this;
		}

		public ViewPort setMinimumScale(double minimumScale) {
			this.minimumScale = minimumScale;
			return this;
		}

		public ViewPort setMaximumScale(double maximumScale) {
			this.maximumScale = maximumScale;
			return this;
		}

		public ViewPort setInitialScale(double initialScale) {
			this.initialScale = initialScale;
			return this;
		}

		public String getContent() {
			StringBuffer buffer = new StringBuffer();

			// width
			buffer.append("width=" + width);

			// height
			if (height != null) {
				buffer.append(",height=" + height);
			}

			// initial scale
			buffer.append(",initial-scale=" + initialScale);
			// minimum scale
			buffer.append(",minimum-scale=" + minimumScale);
			// maximum scale
			buffer.append(",maximum-scale=" + maximumScale);

			// user scaleable
			if (!userScaleAble) {
				buffer.append(",user-scalable=no");
			}
			if (targetDensity != null && MGWT.getOsDetection().isAndroid()) {
				buffer.append(",target-density=" + targetDensity);
			}
			return "";
		}

	}

	private ViewPort viewPort;

	private boolean gloss;

	private String iconUrl;

	private String startUrl;

	private boolean fullscreen;

	private String statusBar;

	private boolean preventScrolling;

	public ViewPort getViewPort() {
		return viewPort;
	}

	public void setViewPort(ViewPort viewPort) {
		this.viewPort = viewPort;
	}

	/**
	 * If the app is added to the home screen on an ios device we can select if
	 * we like a gloss added to the icon of the app
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
	 * If the app is added to the home screen on an ios device we can select if
	 * we like a gloss added to the icon of the app
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
	 * <p>
	 * Getter for the field <code>iconUrl</code>.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * <p>
	 * Setter for the field <code>iconUrl</code>.
	 * </p>
	 * 
	 * @param iconUrl a {@link java.lang.String} object.
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * <p>
	 * Getter for the field <code>startUrl</code>.
	 * </p>
	 * 
	 * @return a {@link java.lang.String} object.
	 */
	public String getStartUrl() {
		return startUrl;
	}

	/**
	 * <p>
	 * Setter for the field <code>startUrl</code>.
	 * </p>
	 * 
	 * @param startUrl a {@link java.lang.String} object.
	 */
	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	/**
	 * <p>
	 * isFullscreen
	 * </p>
	 * 
	 * @return a boolean.
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}

	/**
	 * <p>
	 * Setter for the field <code>fullscreen</code>.
	 * </p>
	 * 
	 * @param fullscreen a boolean.
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
	 * <p>
	 * isPreventScrolling
	 * </p>
	 * 
	 * @return a boolean.
	 */
	public boolean isPreventScrolling() {
		return preventScrolling;
	}

	/**
	 * <p>
	 * Setter for the field <code>preventScrolling</code>.
	 * </p>
	 * 
	 * @param preventScrolling the preventScrolling to set
	 */
	public void setPreventScrolling(boolean preventScrolling) {
		this.preventScrolling = preventScrolling;
	}

}
