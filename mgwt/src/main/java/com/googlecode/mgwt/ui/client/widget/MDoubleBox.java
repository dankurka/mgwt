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
package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.user.client.ui.DoubleBox;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.InputCss;
import com.googlecode.mgwt.ui.client.widget.base.MValueBoxBase;

/**
 * A input box that accepts doubles
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class MDoubleBox extends MValueBoxBase<Double> {

	/**
	 * Construct a double box
	 */
	public MDoubleBox() {
		this(MGWTStyle.getDefaultClientBundle().getInputCss());
	}

	/**
	 * Construct a double box with a given css
	 *
	 * @param css the css to use
	 */
	public MDoubleBox(InputCss css) {
		super(css, new DoubleBox());
		box.getElement().setPropertyString("type", "number");
		addStyleName(css.textBox());
	}

}
