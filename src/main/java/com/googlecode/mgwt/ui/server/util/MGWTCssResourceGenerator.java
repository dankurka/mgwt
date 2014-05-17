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
package com.googlecode.mgwt.ui.server.util;

import com.google.gwt.core.ext.BadPropertyValueException;
import com.google.gwt.core.ext.ConfigurationProperty;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.ext.ResourceContext;
import com.google.gwt.resources.rg.CssResourceGenerator;
import com.google.gwt.user.rebind.SourceWriter;

public class MGWTCssResourceGenerator extends CssResourceGenerator {

  private boolean injectAtStart;

  @Override
  protected void writeEnsureInjected(SourceWriter sw) {
    if (!injectAtStart) {
      super.writeEnsureInjected(sw);
    } else {
      sw.println("private boolean injected;");
      sw.println("public boolean ensureInjected() {");
      sw.indent();
      sw.println("if (!injected) {");
      sw.indentln("injected = true;");
      sw.indentln(StyleInjector.class.getName() + ".injectAtStart(getText());");
      sw.indentln("return true;");
      sw.println("}");
      sw.println("return false;");
      sw.outdent();
      sw.println("}");
    }
  }

  @Override
  public void init(TreeLogger logger, ResourceContext context)
      throws UnableToCompleteException {
    super.init(logger, context);

    try {
      // get module base url config value
      ConfigurationProperty property =
          context.getGeneratorContext().getPropertyOracle().getConfigurationProperty("mgwt.css.inject");
      injectAtStart = "start".equals(property.getValues().get(0));
    } catch (BadPropertyValueException e) {
      // if we can`t find it die
      logger
          .log(TreeLogger.ERROR,
              "Configuration property mgwt.css.inject is not defined. Is UI.gwt.xml inherited?");
      throw new UnableToCompleteException();
    }
  }
}
