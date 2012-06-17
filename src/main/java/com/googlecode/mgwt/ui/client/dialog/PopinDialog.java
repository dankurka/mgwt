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
 * A dialog that pops into view
 * 
 * @author Daniel Kurka
 */
public class PopinDialog extends AnimatableDialogBase {

	/**
	 * Construct a popin dialog
	 */
	public PopinDialog() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getDialogCss());
	}

	/**
	 * Construct a popin dialog with specific css
	 * 
	 * @param css the css to use
	 */
	public PopinDialog(DialogCss css) {
		super(css);
	}

	/** {@inheritDoc} */
	@Override
	protected Animation getShowAnimation() {
		return Animation.POP;
	}

	/** {@inheritDoc} */
	@Override
	protected Animation getHideAnimation() {
		return Animation.POP_REVERSE;
	}

}
