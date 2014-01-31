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
package com.googlecode.mgwt.ui.client.widget.dialog;

import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;


/**
 * A dialog that pops into view
 * 
 * @author Daniel Kurka
 */
public class PopinDialog extends DialogBase {

	public PopinDialog() {
		this(Dialogs.DEFAULT_APPEARANCE);
	}

	public PopinDialog(DialogAppearance appearance) {
		super(appearance);
	}

	@Override
	protected Animation getShowAnimation() {
		return Animations.POP;
	}

	@Override
	protected Animation getHideAnimation() {
		return Animations.POP_REVERSE;
	}
}
