/*
 * Copyright 2014 Daniel Kurka
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

import com.googlecode.mgwt.ui.client.FormFactor;

/**
 * <h1>Considered internal</h1>
 * <p>
 * FormFactorGenerator creates the implementation for
 * {@link FormFactor} for each device.
 *
 * @author Daniel Kurka
 */
public class FormFactorGenerator extends RebindingGenerator {

  @Override
  protected void writeImplementatioon(TreeLogger logger, SelectionProperty property,
      SourceWriter writer) {
    writer.println("public boolean isPhone() {");
    writer.println("return " + property.getCurrentValue().equals("phone") + ";");
    writer.println("}");

    writer.println("public boolean isTablet() {");
    writer.println("return " + property.getCurrentValue().equals("tablet") + ";");
    writer.println("}");

    writer.println("public boolean isDesktop() {");
    writer.println("return " + property.getCurrentValue().equals("desktop") + ";");
    writer.println("}");

    writer.commit(logger);
  }

  @Override
  protected String getSelectionPropertyName() {
    return "mgwt.formfactor";
  }
}
