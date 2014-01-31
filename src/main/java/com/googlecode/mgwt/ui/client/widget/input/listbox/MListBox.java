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
package com.googlecode.mgwt.ui.client.widget.input.listbox;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.ListBox;

/**
 * A list box
 *
 * @author Daniel Kurka
 */
public class MListBox extends ListBox {

	private static final MListBoxAppearance DEFAULT_APPEARANCE = GWT.create(MListBoxAppearance.class);

	public MListBox() {
		this(DEFAULT_APPEARANCE);
	}

	public MListBox(MListBoxAppearance appearance) {
		setStylePrimaryName(appearance.css().listBox());
	}
}
