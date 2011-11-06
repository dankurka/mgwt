/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.buttonbar;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.NextSlideButtonCss;

/**
 * <p>NextSlideButton class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class NextSlideButton extends ButtonBarButtonBase {

	/**
	 * <p>Constructor for NextSlideButton.</p>
	 */
	public NextSlideButton() {
		this(MGWTStyle.getDefaultClientBundle().getNextSlideButtonCss());
	}

	/**
	 * <p>Constructor for NextSlideButton.</p>
	 *
	 * @param nextSlideButtonCss a {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.NextSlideButtonCss} object.
	 */
	public NextSlideButton(NextSlideButtonCss nextSlideButtonCss) {
		super(nextSlideButtonCss);
		addStyleName(nextSlideButtonCss.nextSlide());
	}

}
