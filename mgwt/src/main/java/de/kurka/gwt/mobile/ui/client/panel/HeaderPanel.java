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
package de.kurka.gwt.mobile.ui.client.panel;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author kurt
 *
 */
public class HeaderPanel extends Composite {

	private Widget left;
	private Widget right;
	private HTML title;

	private FlowPanel container;

	public HeaderPanel() {
		container = new FlowPanel();
		container.setStylePrimaryName("mgwt-HeaderPanel");

		initWidget(container);

		title = new HTML();
		title.setStylePrimaryName("title");

		container.add(title);
	}

	public HasText getTitleWidget() {
		return title;
	}

	public void setLeftWidget(Widget newLeft) {
		if (left != null) {
			container.remove(left);
		}

		left = newLeft;

		if (left != null) {
			left.addStyleName("left");
			container.add(left);
		}
	}

	public void setRightWidget(Widget newRight) {
		if (right != null) {
			container.remove(right);
		}

		right = newRight;

		if (right != null) {
			right.addStyleName("right");
			container.add(right);
		}
	}

}
