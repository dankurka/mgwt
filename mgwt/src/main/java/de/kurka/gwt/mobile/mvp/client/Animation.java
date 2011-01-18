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
package de.kurka.gwt.mobile.mvp.client;

/**
 * @author kurt
 *
 */
public class Animation {
	public static final String ANIMATION_SLIDE = "slide";
	public static final String ANIMATION_SLIDE_UP = "slideup";
	public static final String ANIMATION_DISSOLVE = "dissolve";
	public static final String ANIMATION_FADE = "fade";
	public static final String ANIMATION_FLIP = "flip";
	public static final String ANIMATION_POP = "pop";
	public static final String ANIMATION_SWAP = "swap";

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
