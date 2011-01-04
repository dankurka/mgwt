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
package de.kurka.gwt.mobile.ui.client;

/**
 * @author Daniel Kurka
 *
 */
public class MGWTSettings {

	private boolean addGlosToIcon;

	private String iconUrl;

	private String startUrl;

	private boolean fixViewPort;

	private boolean fullscreen;

	private String statusBar;

	private boolean preventScrolling;

	public boolean isAddGlosToIcon() {
		return addGlosToIcon;
	}

	public void setAddGlosToIcon(boolean addGlosToIcon) {
		this.addGlosToIcon = addGlosToIcon;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getStartUrl() {
		return startUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	public boolean isFixViewPort() {
		return fixViewPort;
	}

	public void setFixViewPort(boolean fixViewPort) {
		this.fixViewPort = fixViewPort;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public String getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(String statusBar) {
		this.statusBar = statusBar;
	}

	/**
	 * @return
	 */
	public boolean isPreventScrolling() {
		return preventScrolling;
	}

	/**
	 * @param preventScrolling the preventScrolling to set
	 */
	public void setPreventScrolling(boolean preventScrolling) {
		this.preventScrolling = preventScrolling;
	}

}
