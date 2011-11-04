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
package com.googlecode.mgwt.ui.client.widget;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.PanelCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchPanel;

/**
 * A simple panel with rounded corners
 * 
 * @author Daniel Kurka
 * 
 */
public class RoundPanel extends TouchPanel {

	/**
	 * Construct a round panel
	 */
	public RoundPanel() {
		this(MGWTStyle.getDefaultClientBundle().getPanelCss());
	}

	/**
	 * Construct a round panel with a given css
	 * 
	 * @param css the css to use
	 */
	public RoundPanel(PanelCss css) {
		css.ensureInjected();
		addStyleName(css.roundPanel());
	}
}
