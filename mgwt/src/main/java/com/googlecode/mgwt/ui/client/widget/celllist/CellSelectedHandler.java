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
package com.googlecode.mgwt.ui.client.widget.celllist;

import com.google.gwt.event.shared.EventHandler;

/**
 * Objects that implement this interface reveive {@link CellSelectedEvent}
 * events
 * 
 * @author Daniel Kurka
 * 
 */
public interface CellSelectedHandler extends EventHandler {
	/**
	 * Call when a cell is selected
	 * 
	 * @param event the event containing more information about the cell
	 */
	public void onCellSelected(CellSelectedEvent event);
}
