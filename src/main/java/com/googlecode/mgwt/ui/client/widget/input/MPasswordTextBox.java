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
package com.googlecode.mgwt.ui.client.widget.input;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.PasswordTextBox;

/**
 * An input element that can handle passwords
 * 
 * @author Daniel Kurka
 */
public class MPasswordTextBox extends MTextBox {

	private static class SPasswordTextBox extends PasswordTextBox implements HasSource {
		private Object source;

		@Override
		public void setSource(Object source) {
			this.source = source;
		}

		@Override
		protected HandlerManager createHandlerManager() {
			return new HandlerManager(source);
		}
	}

	public MPasswordTextBox() {
		this(InputApperanceHolder.DEFAULT_APPERAERANCE);
	}

	public MPasswordTextBox(InputAppearance appearance) {
		super(appearance, new SPasswordTextBox());
		addStyleName(appearance.css().passwordBox());
	}
}
