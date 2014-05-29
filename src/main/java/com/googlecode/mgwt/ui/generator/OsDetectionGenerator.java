/*
 * Copyright 2009 Daniel Kurka
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
package com.googlecode.mgwt.ui.generator;

import com.google.gwt.core.ext.SelectionProperty;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Considered internal
 *
 * {@link OsDetectionGenerator} creates the implementation for
 * OsDetection for each platform
 *
 * @author Daniel Kurka
 */
public class OsDetectionGenerator extends RebindingGenerator {

  @Override
  protected void writeImplementatioon(TreeLogger logger, SelectionProperty property,
      SourceWriter writer) {
    writer.println("public boolean isAndroid() {");
    writer.println("return isAndroidTablet() || isAndroidPhone();");
    writer.println("}");

    writer.println("public boolean isIPhone() {");
    writer.println("return " + property.getCurrentValue().equals("iphone") + " || "
        + property.getCurrentValue().equals("retina") + ";");
    writer.println("}");

    writer.println("public boolean isIPad() {");
    writer.println("return " + property.getCurrentValue().equals("ipad") + " || "
        + property.getCurrentValue().equals("ipad_retina") + ";");
    writer.println("}");

    writer.println("public boolean isIOs() {");
    writer.println("return isIPhone() || isIPad();");
    writer.println("}");

    writer.println("public boolean isDesktop() {");
    writer.println("return " + property.getCurrentValue().equals("desktop") + ";");
    writer.println("}");

    writer.println("public boolean isBlackBerry() {");
    writer.println("return false;");
    writer.println("}");

    writer.println("public boolean isTablet() {");
    writer.println("return isDesktop() || isIPad() || isAndroidTablet();");
    writer.println("}");

    writer.println("public boolean isPhone() {");
    writer.println("return isIPhone() || isAndroidPhone() || isBlackBerry();");
    writer.println("}");

    writer.println("public boolean isAndroidTablet() {");
    writer.println("return " + property.getCurrentValue().equals("android_tablet") + ";");
    writer.println("}");

    writer.println("public boolean isAndroidPhone() {");
    writer.println("return " + property.getCurrentValue().equals("android") + ";");
    writer.println("}");

    writer.println("public boolean isRetina() {");
    writer.println("return " + property.getCurrentValue().equals("retina") + ";");
    writer.println("}");

    writer.println("public boolean isIPadRetina() {");
    writer.println("return " + property.getCurrentValue().equals("ipad_retina") + ";");
    writer.println("}");

    writer.commit(logger);
  }

  @Override
  protected String getSelectionPropertyName() {
    return "mgwt.os";
  }
}
