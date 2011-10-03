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
package com.googlecode.mgwt.examples.showcase.client.activities.popup;

import java.util.List;

import com.googlecode.mgwt.dom.client.event.touch.simple.HasSimpleTouchHandler;
import com.googlecode.mgwt.examples.showcase.client.DetailView;
import com.googlecode.mgwt.ui.client.dialog.ConfirmDialog.ConfirmCallback;
import com.googlecode.mgwt.ui.client.dialog.OptionsDialog.OptionCallback;
import com.googlecode.mgwt.ui.client.dialog.OptionsDialog.OptionsDialogOption;

/**
 * @author Daniel Kurka
 * 
 */
public interface PopupView extends DetailView {

	public HasSimpleTouchHandler getSlideUpButton();

	public HasSimpleTouchHandler getAlertButton();

	public HasSimpleTouchHandler getConfirmButton();

	public void alertSomeStuff(String title, String text);

	public void confirmSomeStuff(String title, String text, ConfirmCallback callback);

	public void showSomeOptions(List<OptionsDialogOption> optionText, OptionCallback callback);
}
