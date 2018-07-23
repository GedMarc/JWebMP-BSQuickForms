/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.plugins.bsquickforms;

import com.jwebmp.core.Page;
import com.jwebmp.core.base.angular.AngularPageConfigurator;
import com.jwebmp.core.services.IPageConfigurator;

/**
 * @author Marc Magon
 * @since 25 Mar 2017
 */
public class QuickFormsPageConfigurator
		implements IPageConfigurator
{

	private static final long serialVersionUID = 1L;

	/*
	 * Constructs a new QuickFormsPageConfigurator
	 */
	public QuickFormsPageConfigurator()
	{
		//Nothing needed
	}

	@Override
	public Page configure(Page page)
	{
		if (!page.isConfigured())
		{
			AngularPageConfigurator.setRequired(true);
		}
		return page;
	}
}
