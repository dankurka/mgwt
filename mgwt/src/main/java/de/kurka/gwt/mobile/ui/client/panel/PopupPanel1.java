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
package de.kurka.gwt.mobile.ui.client.panel;

import de.kurka.gwt.mobile.mvp.client.Animation;
import de.kurka.gwt.mobile.ui.client.MGWTStyle;
import de.kurka.gwt.mobile.ui.client.theme.base.DialogCss;

public class PopupPanel1 extends AnimatableDialogBase {

	private final DialogCss dialogCss;

	public PopupPanel1() {
		this(MGWTStyle.getDefaultClientBundle().getDialogCss());

	}

	public PopupPanel1(DialogCss dialogCss) {
		this.dialogCss = dialogCss;
		setCenterContent(false);
		container.addStyleName(dialogCss.getBottomPanel());
	}

	@Override
	protected Animation getShowAnimation() {
		Animation animation = new Animation();
		animation.setType(Animation.ANIMATION_SLIDE_UP);
		return animation;
	}

	@Override
	protected Animation getHideAnimation() {
		Animation animation = new Animation();
		animation.setType(Animation.ANIMATION_SLIDE_UP);
		animation.setDirection(true);
		return animation;
	}

}
