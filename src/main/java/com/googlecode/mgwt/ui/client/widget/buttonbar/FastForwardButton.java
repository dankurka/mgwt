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
import com.googlecode.mgwt.ui.client.theme.base.buttonbar.FastForwardButtonCss;

/**
 * <p>FastForwardButton class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class FastForwardButton extends ButtonBarButtonBase {

	/**
	 * <p>Constructor for FastForwardButton.</p>
	 */
	public FastForwardButton() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getFastForwardButtonCss());
	}

	/**
	 * <p>Constructor for FastForwardButton.</p>
	 *
	 * @param fastForwardButtonCss a {@link com.googlecode.mgwt.ui.client.theme.base.buttonbar.FastForwardButtonCss} object.
	 */
	public FastForwardButton(FastForwardButtonCss fastForwardButtonCss) {
		super(fastForwardButtonCss);
		addStyleName(fastForwardButtonCss.fastForward());
	}

}
