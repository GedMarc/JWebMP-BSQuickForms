/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.plugins.bsquickforms;

import com.jwebmp.Page;
import com.jwebmp.PageConfigurator;
import com.jwebmp.base.angular.AngularPageConfigurator;

/**
 * @author Marc Magon
 * @since 25 Mar 2017
 */
public class QuickFormsPageConfigurator
		extends PageConfigurator
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
