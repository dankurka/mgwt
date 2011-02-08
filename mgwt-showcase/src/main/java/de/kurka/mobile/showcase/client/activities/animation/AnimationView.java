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
package de.kurka.mobile.showcase.client.activities.animation;

import java.util.List;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;

import de.kurka.gwt.mobile.dom.client.event.touch.simple.HasSimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.widget.celllist.HasCellSelectedHandler;

/**
 * @author Daniel Kurka
 *
 */
public interface AnimationView extends IsWidget {
	public void setTitle(String text);

	public void setLeftButtonText(String text);

	public HasSimpleTouchHandler getBackButton();

	public HasCellSelectedHandler getCellSelectedHandler();

	public void setAnimations(List<Animation> animations);

	public HasText getFirstHeader();
}
