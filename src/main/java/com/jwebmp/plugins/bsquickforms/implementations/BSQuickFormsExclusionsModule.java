package com.jwebmp.plugins.bsquickforms.implementations;

import com.jwebmp.guicedinjection.interfaces.IGuiceScanJarExclusions;
import com.jwebmp.guicedinjection.interfaces.IGuiceScanModuleExclusions;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class BSQuickFormsExclusionsModule
		implements IGuiceScanModuleExclusions<BSQuickFormsExclusionsModule>,
				           IGuiceScanJarExclusions<BSQuickFormsExclusionsModule>
{

	@Override
	public @NotNull Set<String> excludeJars()
	{
		Set<String> strings = new HashSet<>();
		strings.add("jwebmp-bootstrap-quick-forms-*");
		return strings;
	}

	@Override
	public @NotNull Set<String> excludeModules()
	{
		Set<String> strings = new HashSet<>();
		strings.add("com.jwebmp.plugins.bsquickforms");
		return strings;
	}
}
