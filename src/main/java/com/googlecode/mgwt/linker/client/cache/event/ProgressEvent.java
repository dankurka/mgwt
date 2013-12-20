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
package com.googlecode.mgwt.linker.client.cache.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * The user agent is downloading resources listed by the manifest.
 * 
 * @author Daniel Kurka
 * 
 */
public class ProgressEvent extends GwtEvent<ProgressEvent.Handler> {
	public interface Handler extends EventHandler {
		public void onProgressEvent(ProgressEvent event);
	}

	private static final GwtEvent.Type<Handler> TYPE = new Type<Handler>();

	public static GwtEvent.Type<Handler> getType() {
		return TYPE;
	}

	private boolean lengthComputable;
	private int loaded;
	private int total;

	public ProgressEvent(boolean lengthComputable, int loaded, int total) {
		this.lengthComputable = lengthComputable;
		this.loaded = loaded;
		this.total = total;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	public boolean isLengthComputable() {
		return lengthComputable;
	}

	public int getLoaded() {
		return loaded;
	}

	public int getTotal() {
		return total;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onProgressEvent(this);
	}
}
