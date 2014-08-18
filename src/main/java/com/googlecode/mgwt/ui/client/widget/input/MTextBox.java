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
package com.googlecode.mgwt.ui.client.widget.input;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.TextBox;

import com.googlecode.mgwt.ui.client.widget.base.MTextBoxBase;

/**
 * A simple text box
 */
public class MTextBox extends MTextBoxBase {

	private static class STextBox extends TextBox implements HasSource {
		private Object source;

		@Override
		public void setSource(Object source) {
			this.source = source;

		}

		@Override
		protected HandlerManager createHandlerManager() {
			return new HandlerManager(source);
		}

	}

	public MTextBox() {
		this(InputApperanceHolder.DEFAULT_APPERAERANCE);
	}

	public MTextBox(InputAppearance appearance) {
		this(appearance, new STextBox());
	}

	/**
	 * Construct a text box with a given css and a {@link TextBox} to delegate
	 * to
	 *
	 * @param css the css to use
	 * @param textBox the text box to use
	 */
	protected MTextBox(InputAppearance appearance, TextBox textBox) {
		super(appearance, textBox);
		addStyleName(appearance.css().textBox());
	}

	/**
	 * get the number of characters for this input element
	 *
	 * @return a int.
	 */
	public int getMaxLength() {
		return getInputElement().getMaxLength();
	}

	/**
	 * get the number of visible characters
	 *
	 * @return a int.
	 */
	public int getVisibleLength() {
		return getInputElement().getSize();
	}

	/**
	 * set the maximum number of characters
	 *
	 * @param length the maximum number of characters
	 */
	public void setMaxLength(int length) {
		getInputElement().setMaxLength(length);
	}

	/**
	 * set the number of visible characters
	 *
	 * @param length the number of visible characters
	 */
	public void setVisibleLength(int length) {
		getInputElement().setSize(length);
	}

	private InputElement getInputElement() {
		return box.getElement().cast();
	}

}
