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

import de.kurka.gwt.mobile.dom.client.event.touch.simple.SimpleTouchHandler;
import de.kurka.gwt.mobile.ui.client.MGWTStyle;

public class Dialogs {

	public interface AlertCallback {
		public void onButtonPressed();
	}

	public static void alert(String title, String text, final AlertCallback callback) {
		AlertDialog alertDialog = new AlertDialog(MGWTStyle.getDefaultClientBundle().getDialogCss(), title, text);

		alertDialog.addSimpleTouchHandler(new SimpleTouchHandler() {

			@Override
			public void onTouch() {
				if (callback != null) {
					callback.onButtonPressed();
				}

			}
		});

		alertDialog.show();
	}
}
