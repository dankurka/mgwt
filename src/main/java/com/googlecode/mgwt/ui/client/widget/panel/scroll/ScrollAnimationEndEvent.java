/*
 * Copyright 2012 Daniel Kurka
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
package com.googlecode.mgwt.ui.client.widget.panel.scroll;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ScrollAnimationEndEvent extends GwtEvent<ScrollAnimationEndEvent.Handler> {

	public interface Handler extends EventHandler {
		public void onScrollAnimationEnd(ScrollAnimationEndEvent event);
	}

	private static GwtEvent.Type<ScrollAnimationEndEvent.Handler> TYPE = new Type<ScrollAnimationEndEvent.Handler>();

	public ScrollAnimationEndEvent() {

	}

	public static GwtEvent.Type<ScrollAnimationEndEvent.Handler> getTYPE() {
		return TYPE;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onScrollAnimationEnd(this);

	}

}
