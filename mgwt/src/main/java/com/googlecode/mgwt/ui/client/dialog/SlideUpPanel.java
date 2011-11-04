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
package com.googlecode.mgwt.ui.client.dialog;

import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.DialogCss;

/**
 * A dialog panel that slides up into view
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class SlideUpPanel extends AnimatableDialogBase {

	/**
	 * Construct a slide up panel
	 */
	public SlideUpPanel() {
		this(MGWTStyle.getDefaultClientBundle().getDialogCss());

	}

	/**
	 * Construct a slide up panel with a specific css
	 *
	 * @param dialogCss the css to use
	 */
	public SlideUpPanel(DialogCss dialogCss) {
		super(dialogCss);
		setCenterContent(false);
	}

	/** {@inheritDoc} */
	@Override
	protected Animation getShowAnimation() {
		return Animation.SLIDE_UP;
	}

	/** {@inheritDoc} */
	@Override
	protected Animation getHideAnimation() {
		return Animation.SLIDE_UP_REVERSE;
	}

}
