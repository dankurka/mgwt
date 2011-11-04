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
 * <p>MGWTSettings class.</p>
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class MGWTSettings {

	private boolean addGlosToIcon;

	private String iconUrl;

	private String startUrl;

	private boolean fixViewPort;

	private boolean fullscreen;

	private String statusBar;

	private boolean preventScrolling;

	/**
	 * <p>isAddGlosToIcon</p>
	 *
	 * @return a boolean.
	 */
	public boolean isAddGlosToIcon() {
		return addGlosToIcon;
	}

	/**
	 * <p>Setter for the field <code>addGlosToIcon</code>.</p>
	 *
	 * @param addGlosToIcon a boolean.
	 */
	public void setAddGlosToIcon(boolean addGlosToIcon) {
		this.addGlosToIcon = addGlosToIcon;
	}

	/**
	 * <p>Getter for the field <code>iconUrl</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * <p>Setter for the field <code>iconUrl</code>.</p>
	 *
	 * @param iconUrl a {@link java.lang.String} object.
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * <p>Getter for the field <code>startUrl</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getStartUrl() {
		return startUrl;
	}

	/**
	 * <p>Setter for the field <code>startUrl</code>.</p>
	 *
	 * @param startUrl a {@link java.lang.String} object.
	 */
	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	/**
	 * <p>isFixViewPort</p>
	 *
	 * @return a boolean.
	 */
	public boolean isFixViewPort() {
		return fixViewPort;
	}

	/**
	 * <p>Setter for the field <code>fixViewPort</code>.</p>
	 *
	 * @param fixViewPort a boolean.
	 */
	public void setFixViewPort(boolean fixViewPort) {
		this.fixViewPort = fixViewPort;
	}

	/**
	 * <p>isFullscreen</p>
	 *
	 * @return a boolean.
	 */
	public boolean isFullscreen() {
		return fullscreen;
	}

	/**
	 * <p>Setter for the field <code>fullscreen</code>.</p>
	 *
	 * @param fullscreen a boolean.
	 */
	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	/**
	 * <p>Getter for the field <code>statusBar</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getStatusBar() {
		return statusBar;
	}

	/**
	 * <p>Setter for the field <code>statusBar</code>.</p>
	 *
	 * @param statusBar a {@link java.lang.String} object.
	 */
	public void setStatusBar(String statusBar) {
		this.statusBar = statusBar;
	}

	/**
	 * <p>isPreventScrolling</p>
	 *
	 * @return a boolean.
	 */
	public boolean isPreventScrolling() {
		return preventScrolling;
	}

	/**
	 * <p>Setter for the field <code>preventScrolling</code>.</p>
	 *
	 * @param preventScrolling
	 *            the preventScrolling to set
	 */
	public void setPreventScrolling(boolean preventScrolling) {
		this.preventScrolling = preventScrolling;
	}

}
