package de.kurka.gwt.mobile.ui.generator;

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

public class FeatureDetectionGenerator extends Generator {

	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {

		PropertyOracle propertyOracle = context.getPropertyOracle();
		SelectionProperty property = null;
		try {
			property = propertyOracle.getSelectionProperty(logger, "mgwt.os");
		} catch (BadPropertyValueException e) {
			logger.log(TreeLogger.ERROR, "can not resolve mgwt.os variable", e);
			throw new UnableToCompleteException();
		}

		JClassType classType = null;

		try {
			classType = context.getTypeOracle().getType(typeName);
		} catch (NotFoundException e) {
			logger.log(TreeLogger.ERROR, "can not find type: '" + typeName + "'", e);
			throw new UnableToCompleteException();
		}

		String mgwtProperty = property.getCurrentValue();
		String packageName = classType.getPackage().getName();
		String simpleName = classType.getSimpleSourceName() + "_" + property.getCurrentValue();
		String fullName = packageName + "." + simpleName;

		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
		composer.addImplementedInterface(typeName);
		composer.addImport(typeName);

		PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);

		if (printWriter == null) {
			return fullName;
		}

		SourceWriter writer = composer.createSourceWriter(context, printWriter);

		writer.println("public boolean isAndroid() {");
		writer.println("return " + mgwtProperty.equals("android") + ";");
		writer.println("}");

		writer.println("public boolean isIPhone() {");
		writer.println("return " + mgwtProperty.equals("iphone") + ";");
		writer.println("}");

		writer.println("public boolean isIPad() {");
		writer.println("return " + mgwtProperty.equals("ipad") + ";");
		writer.println("}");

		writer.println("public boolean isIOs() {");
		writer.println("return " + (mgwtProperty.equals("iphone") || mgwtProperty.equals("ipad")) + ";");
		writer.println("}");

		writer.println("public boolean isDesktop() {");
		writer.println("return " + mgwtProperty.equals("desktop") + ";");
		writer.println("}");

		writer.println("public boolean isTablet() {");
		writer.println("return isDesktop() || isIPad();");
		writer.println("}");

		writer.commit(logger);

		return fullName;

	}

}
