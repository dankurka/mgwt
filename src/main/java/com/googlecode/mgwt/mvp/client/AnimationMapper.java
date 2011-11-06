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

import com.google.gwt.place.shared.Place;

/**
 * An animation mapper provides the animation to be executed when switching from
 * one place to another.
 *
 * The {@link AnimatingActivityManager} needs an instance of this interface
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public interface AnimationMapper {
	/**
	 * Called when we need to move from one place to another
	 *
	 * @param oldPlace - the old place where we have been (can be null at
	 *            startup)
	 * @param newPlace - the new place where we are going to (can not be null)
	 * @return the aniation to execute, if null is returned to animation is
	 *         executed and the change happens immediately
	 */
	public Animation getAnimation(Place oldPlace, Place newPlace);
}
