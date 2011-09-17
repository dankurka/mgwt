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
package de.kurka.gwt.mobile.ui.client.dialog;

import java.util.List;

import de.kurka.gwt.mobile.ui.client.theme.base.DialogCss;

public class OptionsDialog {

	public interface OptionCallback {
		public void onOptionSelected(int index);
	}

	public OptionsDialog(DialogCss css, List<String> optionText, OptionCallback callback) {
		// slideUpPanel = new SlideUpPanel();
		//
		// final Button redButton = new Button("Important");
		// redButton.setImportant(true);
		// slideUpPanel.add(redButton);
		//
		// final Button okButton = new Button("Ok");
		// okButton.setConfirm(true);
		// slideUpPanel.add(okButton);
		//
		// popupPanelCloseButton = new Button("Close");
		// slideUpPanel.add(popupPanelCloseButton);
		//
		// slideUpPanel.add(popupPanelCloseButton);
		// slideUpPanel.setPanelToOverlay(main);
	}
}
