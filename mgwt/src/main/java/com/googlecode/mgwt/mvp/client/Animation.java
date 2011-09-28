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
public class Animation {
	public static final String ANIMATION_SLIDE = MVPClientBundle.INSTANCE.animationCss().slide();
	public static final String ANIMATION_SLIDE_UP = MVPClientBundle.INSTANCE.animationCss().slideup();
	public static final String ANIMATION_DISSOLVE = MVPClientBundle.INSTANCE.animationCss().dissolve();
	public static final String ANIMATION_FADE = MVPClientBundle.INSTANCE.animationCss().fade();
	public static final String ANIMATION_FLIP = MVPClientBundle.INSTANCE.animationCss().flip();
	public static final String ANIMATION_POP = MVPClientBundle.INSTANCE.animationCss().pop();
	public static final String ANIMATION_SWAP = MVPClientBundle.INSTANCE.animationCss().swap();

	private String type = ANIMATION_SLIDE;
	private boolean direction;

	/**
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param animationSlide
	 */
	public void setType(String type) {
		this.type = type;

	}

	/**
	 * @return the direction - true for backwards
	 */
	public boolean isDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
}
