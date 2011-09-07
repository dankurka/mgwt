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
package de.kurka.gwt.mobile.ui.client.widget;

import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.button.HeaderButton;
import de.kurka.gwt.mobile.ui.client.theme.base.HeaderButtonCss;

/**
 * @author Daniel Kurka
 *
 */
public class HeaderRoundButton extends HeaderButton {
	public HeaderRoundButton() {
		this(MGWTStyle.getDefaultClientBundle().getHeaderButtonCss());
	}

	public HeaderRoundButton(HeaderButtonCss css) {
		super(css);
		addStyleName(css.round());
	}
}
