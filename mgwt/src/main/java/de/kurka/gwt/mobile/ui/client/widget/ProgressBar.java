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
package de.kurka.gwt.mobile.ui.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Daniel Kurka
 *
 */
public class ProgressBar extends Widget {

	public interface ProgressBarCss extends CssResource {
		@ClassName("mgwt-ProgressBar")
		String progressBar();
	}

	public interface ProgressBarResources extends ClientBundle {
		@Source("css/progressbar.css")
		ProgressBarCss css();
	}

	private static ProgressBarResources defaultResources;
	private final ProgressBarResources resources;

	public static ProgressBarResources getDefaultResources() {
		if (defaultResources == null) {
			defaultResources = GWT.create(ProgressBarResources.class);
		}
		return defaultResources;
	}

	public ProgressBar() {
		this(getDefaultResources());
	}

	public ProgressBar(ProgressBarResources resources) {
		this.resources = resources;
		setElement(DOM.createDiv());

		this.resources.css().ensureInjected();
		setStylePrimaryName(this.resources.css().progressBar());
		//TODO what about internal styling?
		//addStyleName("mgwt-ProgressBar");

	}
}
