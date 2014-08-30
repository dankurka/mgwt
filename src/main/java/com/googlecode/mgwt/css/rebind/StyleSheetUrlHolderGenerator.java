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
package com.googlecode.mgwt.css.rebind;

import java.io.PrintWriter;
import java.util.List;

import com.google.gwt.core.ext.BadPropertyValueException;
import com.google.gwt.core.ext.ConfigurationProperty;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import com.googlecode.mgwt.css.client.updater.StyleSheetUrlHolderDefaultImpl;
import com.googlecode.mgwt.css.client.updater.StyleSheetUrlHolderNoopImpl;

public class StyleSheetUrlHolderGenerator extends Generator {

  private static final String KEY_ENABLED = "mgwt.css.updater.enabled";
  private static final String KEY_INTERVAL = "mgwt.css.updater.interval";
  private static final String KEY_URLS = "mgwt.css.updater.urls";


  @Override
  public String generate(TreeLogger logger, GeneratorContext context, String typeName)
      throws UnableToCompleteException {
    // get the property oracle
    PropertyOracle propertyOracle = context.getPropertyOracle();
    String enablePropertyValue = getSingleValue(propertyOracle, KEY_ENABLED, logger);

    if (!"true".equals(enablePropertyValue)) {
      return StyleSheetUrlHolderNoopImpl.class.getName();
    }

    List<String> urls = getValues(propertyOracle, KEY_URLS, logger);

    int interval = Integer.parseInt(getSingleValue(propertyOracle, KEY_INTERVAL, logger));

    JClassType classType = null;

    try {
      // get the type we are looking for
      classType = context.getTypeOracle().getType(typeName);
    } catch (NotFoundException e) {
      // if we can`t get it die
      logger.log(TreeLogger.ERROR, "can not find type: '" + typeName + "'", e);
      throw new UnableToCompleteException();
    }

    // get the package name
    String packageName = classType.getPackage().getName();
    // build name for implementation class
    String simpleName = classType.getSimpleSourceName() + "_generated";
    // combine package name and simple name to full qualified
    String fullName = packageName + "." + simpleName;

    PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);

    if (printWriter == null) {
      return fullName;
    }

    ClassSourceFileComposerFactory composer =
        new ClassSourceFileComposerFactory(packageName, simpleName);
    composer.setSuperclass(StyleSheetUrlHolderDefaultImpl.class.getName());
    composer.addImport(StyleSheetUrlHolderDefaultImpl.class.getName());

    // start writing the implementation
    SourceWriter writer = composer.createSourceWriter(context, printWriter);

    writer.println("public " + simpleName + "() {");
    writer.print("super(");

    boolean first = true;
    for (String url : urls) {
      if (first) {
        first = false;
      } else {
        writer.print(",");
      }
      writer.print("\"" + url + "\"");
    }
    writer.print(");\n");

    writer.println("}");

    writer.println("public int interval() {");
    writer.println("return " + interval + ";");
    writer.println("}");
    writer.commit(logger);

    return fullName;
  }

  private String getSingleValue(PropertyOracle propertyOracle, String name, TreeLogger logger) throws UnableToCompleteException {
    ConfigurationProperty configProperty = getConfigurationProperty(propertyOracle, logger, name);
    return configProperty.getValues().get(0);
  }

  private List<String> getValues(PropertyOracle propertyOracle, String name, TreeLogger logger) throws UnableToCompleteException {
    ConfigurationProperty configProperty = getConfigurationProperty(propertyOracle, logger, name);
    return configProperty.getValues();
  }

  private ConfigurationProperty getConfigurationProperty(PropertyOracle propertyOracle, TreeLogger logger, String name) throws UnableToCompleteException {
    try {
      return propertyOracle.getConfigurationProperty(name);
    } catch (BadPropertyValueException e) {
      // if we can`t find it die
      logger.log(TreeLogger.ERROR, "can not resolve " + name , e);
      throw new UnableToCompleteException();
    }
  }
}
