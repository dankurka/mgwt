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

import com.googlecode.mgwt.mvp.client.resources.AnimationSelector;

/**
 * @author Daniel Kurka
 * 
 */

public enum Animation {
	SLIDE(AnimationSelector.getNames().slide()), SLIDE_REVERSE(AnimationSelector.getNames().slide(), true), SLIDE_UP(AnimationSelector.getNames().slideup()), SLIDE_UP_REVERSE(AnimationSelector
			.getNames().slideup(), true), DISSOLVE(AnimationSelector.getNames().dissolve()), DISSOLVE_REVERSE(AnimationSelector.getNames().dissolve(), true), FADE(AnimationSelector.getNames().fade()), FADE_REVERSE(
			AnimationSelector.getNames().fade(), true), FLIP(AnimationSelector.getNames().flip()), FLIP_REVERSE(AnimationSelector.getNames().flip(), true), POP(AnimationSelector.getNames().pop()), POP_REVERSE(
			AnimationSelector.getNames().pop(), true), SWAP(AnimationSelector.getNames().swap()), SWAP_REVERSE(AnimationSelector.getNames().swap(), true), CUSTOM("");

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
