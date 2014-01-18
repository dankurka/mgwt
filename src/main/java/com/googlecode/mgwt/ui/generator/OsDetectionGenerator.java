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

/**
 * Considered internal
 * 
 * {@link OsDetectionGenerator} creates the implementation for
 * OsDetection for each platform
 * 
 * @author Daniel Kurka
 * @version $Id: $
 */
public class OsDetectionGenerator extends Generator {

	/** {@inheritDoc} */
	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {

		// get the property oracle
		PropertyOracle propertyOracle = context.getPropertyOracle();
		SelectionProperty property = null;
		try {
			// get mgwt.os variable
			property = propertyOracle.getSelectionProperty(logger, "mgwt.os");
		} catch (BadPropertyValueException e) {
			// if we can`t find it die
			logger.log(TreeLogger.ERROR, "can not resolve mgwt.os variable", e);
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

		// get value of mgwt.os
		String mgwtProperty = property.getCurrentValue();
		// get the package name
		String packageName = classType.getPackage().getName();
		// build name for implementation class
		String simpleName = classType.getSimpleSourceName() + "_" + property.getCurrentValue();
		// combine package name and simple name to full qualified
		String fullName = packageName + "." + simpleName;

		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
		composer.addImplementedInterface(typeName);
		composer.addImport(typeName);

		PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);

		if (printWriter == null) {
			return fullName;
		}

		// start writing the implementation
		SourceWriter writer = composer.createSourceWriter(context, printWriter);

		writer.println("public boolean isAndroid() {");
		writer.println("return isAndroidTablet() || isAndroidPhone();");
		writer.println("}");

		writer.println("public boolean isIPhone() {");
		writer.println("return " + mgwtProperty.equals("iphone") + " || " + mgwtProperty.equals("retina") + ";");
		writer.println("}");

		writer.println("public boolean isIPad() {");
		writer.println("return " + mgwtProperty.equals("ipad") + " || " + mgwtProperty.equals("ipad_retina") + ";");
		writer.println("}");

		writer.println("public boolean isIOs() {");
		writer.println("return isIPhone() || isIPad();");
		writer.println("}");

		writer.println("public boolean isDesktop() {");
		writer.println("return " + mgwtProperty.equals("desktop") + ";");
		writer.println("}");

		writer.println("public boolean isBlackBerry() {");
		writer.println("return " + mgwtProperty.equals("blackberry") + ";");
		writer.println("}");

		writer.println("public boolean isTablet() {");
		writer.println("return isDesktop() || isIPad() || isAndroidTablet();");
		writer.println("}");

		writer.println("public boolean isPhone() {");
		writer.println("return isIPhone() || isAndroidPhone() || isBlackBerry();");
		writer.println("}");

		writer.println("public boolean isAndroidTablet() {");
		writer.println("return " + mgwtProperty.equals("android_tablet") + ";");
		writer.println("}");

		writer.println("public boolean isAndroidPhone() {");
		writer.println("return " + mgwtProperty.equals("android") + ";");
		writer.println("}");

		writer.println("public boolean isRetina() {");
		writer.println("return " + mgwtProperty.equals("retina") + ";");
		writer.println("}");

		writer.println("public boolean isIPadRetina() {");
		writer.println("return " + mgwtProperty.equals("ipad_retina") + ";");
		writer.println("}");

		writer.commit(logger);

		return fullName;

	}
}
