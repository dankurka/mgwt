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

import java.io.PrintWriter;

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

import com.googlecode.mgwt.ui.client.FormFactor;

/**
 * <h1>Considered internal</h1>
 * <p>
 * FormFactorGenerator creates the implementation for
 * {@link FormFactor} for each device.
 *
 * @author Daniel Kurka
 */
public class DeviceDensityGenerator extends Generator {

	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {

		// get the property oracle
		PropertyOracle propertyOracle = context.getPropertyOracle();
		SelectionProperty property = null;
		try {
			// get mgwt.os variable
			property = propertyOracle.getSelectionProperty(logger, "mgwt.density");
		} catch (BadPropertyValueException e) {
			// if we can`t find it die
			logger.log(TreeLogger.ERROR, "can not resolve mgwt.density variable", e);
			throw new UnableToCompleteException();
		}

		JClassType classType = null;

		try {
			// get the type we are looking for
			classType = context.getTypeOracle().getType(typeName);
		} catch (NotFoundException e) {
			// if we can`t get it die
			logger.log(TreeLogger.ERROR, "can not find type: '" + typeName + "'", e);
			throw new UnableToCompleteException();
		}

		// get value of mgwt.formfactor
		String mgwtProperty = property.getCurrentValue();
		// get the package name
		String packageName = classType.getPackage().getName();
		// build name for implementation class
		String simpleName = classType.getSimpleSourceName() + "_" + property.getCurrentValue();
		// combine package name and simple name to full qualified
		String fullName = packageName + "." + simpleName;

		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
		composer.addImplementedInterface(typeName);

		PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);

		if (printWriter == null) {
			return fullName;
		}

		// start writing the implementation
		SourceWriter writer = composer.createSourceWriter(context, printWriter);

		writer.println("public boolean isLow() {");
		writer.println("return " + mgwtProperty.equals("low") + ";");
		writer.println("}");

		writer.println("public boolean isMiddle() {");
    writer.println("return " + mgwtProperty.equals("mid") + ";");
    writer.println("}");

    writer.println("public boolean isHigh() {");
    writer.println("return " + mgwtProperty.equals("high") + ";");
    writer.println("}");

		writer.commit(logger);

		return fullName;
	}
}
