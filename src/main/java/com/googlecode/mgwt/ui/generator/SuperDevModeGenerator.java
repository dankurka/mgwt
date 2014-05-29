/*
 * Copyright 2012 Daniel Kurka
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

public class SuperDevModeGenerator extends Generator{

	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
		// get the property oracle
		PropertyOracle propertyOracle = context.getPropertyOracle();
		ConfigurationProperty property = null;
		try {
			// get mgwt.superdevmode variable
			property = propertyOracle.getConfigurationProperty("mgwt.superdevmode");
		} catch (BadPropertyValueException e) {
			// if we can`t find it die
			logger.log(TreeLogger.ERROR, "can not resolve mgwt.superdevmode variable", e);
			throw new UnableToCompleteException();
		}

		ConfigurationProperty superDevModeServer = null;
		try {
			// get mgwt.superdevmode variable
			superDevModeServer = propertyOracle.getConfigurationProperty("mgwt.superdevmode_host");
		} catch (BadPropertyValueException e) {
			// if we can`t find it die
			logger.log(TreeLogger.INFO, "can not resolve mgwt.superdevmode_host variable - using default - http://<server>:9876", e);
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

		// get value of mgwt.os
		String mgwtSuperDevmodePropertyValue = property.getValues().get(0);

		if("on".equals(mgwtSuperDevmodePropertyValue)){
			return buildOnImplementation(logger, context, classType, typeName, superDevModeServer);
		}else{
			return "com.googlecode.mgwt.ui.client.util.impl.SuperDevModeOffImpl";
		}



	}

	private String buildOnImplementation(TreeLogger logger, GeneratorContext context, JClassType classType, String typeName, ConfigurationProperty superDevModeServer) {



		if(superDevModeServer.getValues().get(0) == null){
			//use default impl!
			return "com.googlecode.mgwt.ui.client.util.impl.SuperDevModeHelperOnDefaultImpl";
		}


		// get the package name
		String packageName = classType.getPackage().getName();
		// build name for implementation class
		String simpleName = classType.getSimpleSourceName() + "_generated";
		// combine package name and simple name to full qualified
		String fullName = packageName + "." + simpleName;

		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
		composer.setSuperclass("com.googlecode.mgwt.ui.client.util.impl.SuperDevModeHelperOnImpl");
		composer.addImport("com.googlecode.mgwt.ui.client.util.impl.SuperDevModeHelperOnImpl");


		PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);

		if (printWriter == null) {
			return fullName;
		}


		// start writing the implementation
		SourceWriter writer = composer.createSourceWriter(context, printWriter);

		writer.println("protected String getServerUrl() {");
		writer.println("return \"" + superDevModeServer.getValues().get(0) + "\";");
		writer.println("}");
		writer.commit(logger);

		return fullName;
	}

}
