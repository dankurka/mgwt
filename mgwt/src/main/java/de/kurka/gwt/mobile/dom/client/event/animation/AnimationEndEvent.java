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
package de.kurka.gwt.mobile.dom.client.event.animation;

import com.google.gwt.event.dom.client.DomEvent;

/**
 * @author Daniel Kurka
 *
 */
public class AnimationEndEvent extends DomEvent<AnimationEndHandler> {
	private static final Type<AnimationEndHandler> TYPE = new Type<AnimationEndHandler>("webkitAnimationEnd", new AnimationEndEvent());

	public static Type<AnimationEndHandler> getType() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.dom.client.DomEvent.Type<AnimationEndHandler> getAssociatedType() {
		return TYPE;
	}

	protected AnimationEndEvent() {

	}

	@Override
	protected void dispatch(AnimationEndHandler handler) {
		handler.onAnimationEnd(this);

	}
}
