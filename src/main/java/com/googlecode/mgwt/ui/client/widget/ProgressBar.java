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
import com.googlecode.mgwt.ui.client.theme.base.ProgressBarCss;

/**
 * A progress bar widget
 * 
 * <h2>Styling</h2> The DOM structure looks like:
 * 
 * <pre>
 * &lt;div class="mgwt-ProgressBar">&lt;/div>
 * </pre>
 * 
 * @author Daniel Kurka
 */
public class ProgressBar extends Widget {

	protected final ProgressBarCss css;

	/**
	 * Construct a progress bar widget
	 */
	public ProgressBar() {
		this(MGWTStyle.getTheme().getMGWTClientBundle().getProgressBarCss());
	}

	/**
	 * Construct a progress bar widget with a given css
	 * 
	 * @param css the css to use
	 */
	public ProgressBar(ProgressBarCss css) {
		this.css = css;
		setElement(Document.get().createDivElement());

		this.css.ensureInjected();
		setStylePrimaryName(this.css.progressBar());

	}
}
