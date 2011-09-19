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

import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.kurka.gwt.mobile.ui.client.panel.SlideUpPanel;
import de.kurka.gwt.mobile.ui.client.theme.base.DialogCss;

public class OptionsDialog implements HasWidgets {

	public interface OptionCallback {
		public void onOptionSelected(int index);
	}

	public enum ButtonType {
		NORMAL, IMPORTANT, CONFIRM
	};

	public static class OptionsDialogOption {
		private final String text;
		private final ButtonType type;

		public OptionsDialogOption(String text, ButtonType type) {
			this.text = text;
			this.type = type;

		}

		public String getText() {
			return text;
		}

		public ButtonType getType() {
			return type;
		}
	}

	private SlideUpPanel slideUpPanel;

	public OptionsDialog(DialogCss css) {
		css.ensureInjected();
		slideUpPanel = new SlideUpPanel();

	}

	public void show() {
		slideUpPanel.show();
	}

	public void hide() {
		slideUpPanel.hide();
	}

	@Override
	public void add(Widget w) {
		slideUpPanel.add(w);

	}

	@Override
	public void clear() {
		slideUpPanel.clear();

	}

	@Override
	public Iterator<Widget> iterator() {
		return slideUpPanel.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return slideUpPanel.remove(w);
	}

	public void setPanelToOverlay(HasWidgets widgetToCover) {
		slideUpPanel.setPanelToOverlay(widgetToCover);

	}

}
