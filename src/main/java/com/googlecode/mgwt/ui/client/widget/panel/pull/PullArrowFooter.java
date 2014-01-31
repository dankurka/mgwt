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
package com.googlecode.mgwt.ui.client.widget.panel.pull;


/**
 * A header for a pull panel that shows an arrow
 * 
 * @author Daniel Kurka
 * 
 */
public class PullArrowFooter extends PullArrowBase {

	public PullArrowFooter() {
		this(PullPanel.DEFAULT_APPEARANCE);
	}

	public PullArrowFooter(PullPanelAppearance appearance) {
		super(appearance);
	}

	protected int getRotation(int y) {
		int degree = (y + 30) * -10;
		if (degree < -90)
			degree = -90;
		if (degree > 90) {
			degree = 90;
		}
		return degree;
	}

}
