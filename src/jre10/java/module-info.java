import com.jwebmp.core.services.IPageConfigurator;
import com.jwebmp.plugins.bsquickforms.QuickFormsPageConfigurator;

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

	provides IPageConfigurator with QuickFormsPageConfigurator;
	opens com.jwebmp.plugins.bsquickforms to com.fasterxml.jackson.databind,com.jwebmp.core;
	opens com.jwebmp.plugins.bsquickforms.annotations.actions to com.fasterxml.jackson.databind,com.jwebmp.core;
}
