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
package com.googlecode.mgwt.mvp.client;

import com.googlecode.mgwt.mvp.client.resources.MVPClientBundle;

/**
 * @author Daniel Kurka
 * 
 */

public enum Animation {
	SLIDE(MVPClientBundle.INSTANCE.animationCss().slide()), SLIDE_REVERSE(MVPClientBundle.INSTANCE.animationCss().slide(), true), SLIDE_UP(MVPClientBundle.INSTANCE.animationCss().slideup()), SLIDE_UP_REVERSE(
			MVPClientBundle.INSTANCE.animationCss().slideup(), true), DISSOLVE(MVPClientBundle.INSTANCE.animationCss().dissolve()), DISSOLVE_REVERSE(
			MVPClientBundle.INSTANCE.animationCss().dissolve(), true), FADE(MVPClientBundle.INSTANCE.animationCss().fade()), FADE_REVERSE(MVPClientBundle.INSTANCE.animationCss().fade(), true), FLIP(
			MVPClientBundle.INSTANCE.animationCss().flip()), FLIP_REVERSE(MVPClientBundle.INSTANCE.animationCss().flip(), true), POP(MVPClientBundle.INSTANCE.animationCss().pop()), POP_REVERSE(
			MVPClientBundle.INSTANCE.animationCss().pop(), true), SWAP(MVPClientBundle.INSTANCE.animationCss().swap()), SWAP_REVERSE(MVPClientBundle.INSTANCE.animationCss().swap(), true), CUSTOM("");

	private String cssName;
	private boolean inverse;

	Animation(String cssName) {
		this.cssName = cssName;
		inverse = false;
	}

	Animation(String cssName, boolean inverse) {
		this.cssName = cssName;
		this.inverse = inverse;
	}

	public void setCssName(String cssName) {
		this.cssName = cssName;
	}

	public void setInverse(boolean inverse) {
		this.inverse = inverse;
	}

	public String getCssName() {
		return cssName;
	}

	public boolean isInverse() {
		return inverse;
	}

}
