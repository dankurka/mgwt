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

import com.google.gwt.core.ext.BadPropertyValueException;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.PropertyOracle;
import com.google.gwt.core.ext.SelectionProperty;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;

/**
 * <h1>Considered internal</h1>
 *
 * @author Daniel Kurka
 */
public abstract class RebindingGenerator extends Generator {

  protected static class WriteHolder {
    String packageName;

    String simpleName;

    String fullName;

    ClassSourceFileComposerFactory composer;

    PrintWriter printWriter;

    public WriteHolder(String packageName, String simpleName, ClassSourceFileComposerFactory composer, PrintWriter printWriter) {
      this.packageName = packageName;
      this.simpleName = simpleName;
      this.composer = composer;
      this.printWriter = printWriter;
      this.fullName = packageName + "." + simpleName;
    }
  }

	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {

		SelectionProperty property = getSelectionProperty(logger, context, getSelectionPropertyName());
		JClassType classType = getClassType(logger, context, typeName);


		WriteHolder writeHolder = createWriteHolder(logger, context, classType, property, typeName);

		if (writeHolder.printWriter == null) {
      return writeHolder.fullName;
    }

	  // start writing the implementation
    SourceWriter writer = writeHolder.composer.createSourceWriter(context, writeHolder.printWriter);
		writeImplementatioon(logger, property, writer);
		return writeHolder.fullName;
	}

  protected abstract String getSelectionPropertyName();

  protected abstract void writeImplementatioon(TreeLogger logger, SelectionProperty property, SourceWriter writer);

  private JClassType getClassType(TreeLogger logger, GeneratorContext context, String typeName)
      throws UnableToCompleteException {
    try {
      // get the type we are looking for
      return context.getTypeOracle().getType(typeName);
    } catch (NotFoundException e) {
      // if we can`t get it die
      logger.log(TreeLogger.ERROR, "can not find type: '" + typeName + "'", e);
      throw new UnableToCompleteException();
    }
  }

  private WriteHolder createWriteHolder(TreeLogger logger, GeneratorContext context, JClassType classType,
      SelectionProperty property, String typeName) {
    // get the package name
    String packageName = classType.getPackage().getName();
    // build name for implementation class
    String simpleName = classType.getSimpleSourceName() + "_" + property.getCurrentValue();
    PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);
    ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
    composer.addImplementedInterface(typeName);
    return new WriteHolder(packageName, simpleName, composer, printWriter);
  }

  private SelectionProperty getSelectionProperty(TreeLogger logger, GeneratorContext context, String propertyName)
      throws UnableToCompleteException {
    // get the property oracle
		PropertyOracle propertyOracle = context.getPropertyOracle();
		SelectionProperty property = null;
		try {
			// get mgwt.os variable
			property = propertyOracle.getSelectionProperty(logger, propertyName);
		} catch (BadPropertyValueException e) {
			// if we can`t find it die
			logger.log(TreeLogger.ERROR, "can not resolve " + propertyName + " variable", e);
			throw new UnableToCompleteException();
		}
    return property;
  }
}
