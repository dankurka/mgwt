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
package com.googlecode.mgwt.ui.client.widget.impl;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.ui.client.widget.event.HasScrollHandlers;

/**
 * ScrollPanelImpl abstracts different implementations for scrolling behaviour
 *
 * @author Daniel Kurka
 * @version $Id: $
 */
public abstract class ScrollPanelImpl extends Composite implements HasWidgets, HasScrollHandlers {

	/**
	 * set the position of the scroll panel
	 *
	 * @param newPosX the new x position
	 * @param newPosY the new y position
	 */
	public abstract void setPosition(int newPosX, int newPosY);

	/**
	 * instruct the panel to use position absolute instead of translate3d
	 *
	 * on android devices input fields behave strange when used with translate3d
	 *
	 * take a look into the mgwt docs
	 *
	 * @param pos true to use absolute position default: translate3d
	 */
	public abstract void setUsePos(boolean pos);

	/**
	 * Scroll to a given position in the specified time
	 *
	 * @param destX the new position x
	 * @param destY the new position y
	 * @param newDuration the duration
	 */
	public abstract void scrollTo(int destX, int destY, int newDuration);

	/**
	 * Is scrolling enabled in x-axis
	 *
	 * @return true if scrolling is enabled
	 */
	public abstract boolean isScrollingEnabledX();

	/**
	 * enable scrolling in x-axis
	 *
	 * @param scrollingEnabledX true to enable scrolling
	 */
	public abstract void setScrollingEnabledX(boolean scrollingEnabledX);

	/**
	 * Is scrolling enabled in y-axis
	 *
	 * @return true if scrolling is enabled
	 */
	public abstract boolean isScrollingEnabledY();

	/**
	 * enable scrolling in y-axis
	 *
	 * @param scrollingEnabledY a boolean.
	 */
	public abstract void setScrollingEnabledY(boolean scrollingEnabledY);

	/**
	 * set an offset to scrolling (hide part of the content)
	 *
	 * @param offsetX the offset in the x-axis
	 * @param offsetY the offset in the y-axis
	 */
	public abstract void setOffset(int offsetX, int offsetY);

	/**
	 * set the content of the scrollable area
	 *
	 * @param child the content of the scrollable area
	 */
	public abstract void setWidget(IsWidget child);

	/**
	 * Recalculate dimensions for scrolling
	 *
	 * (needs to be called when the content of the childarea changes without
	 * setting a new child)
	 */
	public abstract void refresh();
}
