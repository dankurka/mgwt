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
package com.googlecode.mgwt.ui.client.theme.mgwt;

/**
 * <p>MGWTColorScheme class.</p>
 *
 * @author kurt
 * @version $Id: $
 */
public class MGWTColorScheme {
	private static String baseColor = "#0D56a6";

	private static String fontColor = "#eeeeff";

	/**
	 * <p>Getter for the field <code>baseColor</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public static String getBaseColor() {
		return baseColor;
	}

	/**
	 * <p>Getter for the field <code>fontColor</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public static String getFontColor() {
		return fontColor;
	}

	/**
	 * <p>Setter for the field <code>baseColor</code>.</p>
	 *
	 * @param baseColor a {@link java.lang.String} object.
	 */
	public static void setBaseColor(String baseColor) {
		MGWTColorScheme.baseColor = baseColor;
	}

	/**
	 * <p>Setter for the field <code>fontColor</code>.</p>
	 *
	 * @param fontColor a {@link java.lang.String} object.
	 */
	public static void setFontColor(String fontColor) {
		MGWTColorScheme.fontColor = fontColor;
	}
}
