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
package com.googlecode.mgwt.ui.client.widget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.ProgressIndicatorCss;

/**
 * A simple progress indicator
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public class ProgressIndicator extends Widget {

	private final ProgressIndicatorCss css;

	/**
	 * Construct a progress indicator
	 */
	public ProgressIndicator() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getProgressIndicatorCss());
	}

	/**
	 * Construct a progress indicator with a given css
	 *
	 * @param css the css to use
	 */
	public ProgressIndicator(ProgressIndicatorCss css) {
		this.css = css;

		setElement(Document.get().createDivElement());

		this.css.ensureInjected();
		setStylePrimaryName(this.css.progressIndicator());
	}
}
