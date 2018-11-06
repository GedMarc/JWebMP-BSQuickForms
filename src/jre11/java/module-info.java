import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions;
import com.jwebmp.plugins.bsquickforms.QuickFormsPageConfigurator;
import com.jwebmp.plugins.bsquickforms.implementations.BSQuickFormsExclusionsModule;

module com.jwebmp.plugins.bsquickforms {
	exports com.jwebmp.plugins.bsquickforms;
	exports com.jwebmp.plugins.bsquickforms.annotations.actions;

	requires com.jwebmp.core;
	requires com.jwebmp.logmaster;
	requires com.fasterxml.jackson.annotation;

	requires java.validation;
	requires com.jwebmp.plugins.bootstrap;
	requires com.jwebmp.plugins.bootstrapswitch;
	requires java.logging;

	requires com.jwebmp.plugins.quickforms;
	requires com.jwebmp.guicedinjection;

	provides IPageConfigurator with QuickFormsPageConfigurator;
	provides IGuiceScanJarExclusions with BSQuickFormsExclusionsModule;
	provides IGuiceScanModuleExclusions with BSQuickFormsExclusionsModule;

	opens com.jwebmp.plugins.bsquickforms to com.fasterxml.jackson.databind, com.jwebmp.core;
	opens com.jwebmp.plugins.bsquickforms.annotations.actions to com.fasterxml.jackson.databind, com.jwebmp.core;
}
