/*
 * Copyright 2011 Daniel Kurka
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
package com.googlecode.mgwt.mvp.client.resources;

import com.google.gwt.resources.client.ClientBundle;

/**
 * Considered internal
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public interface MVPBundle extends ClientBundle {
	/**
	 * <p>animationCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.mvp.client.resources.AnimationCss} object.
	 */
	@Source("animation.css")
	public AnimationCss animationCss();

	/**
	 * <p>transitionCss</p>
	 *
	 * @return a {@link com.googlecode.mgwt.mvp.client.resources.TransistionCss} object.
	 */
	@Source("transition.css")
	public TransistionCss transitionCss();
}
