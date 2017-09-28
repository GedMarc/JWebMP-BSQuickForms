package com.armineasy.jwebswing.plugins.quickforms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.mmagon.BaseTestClass;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;

public class BSQuickFormTest extends BaseTestClass
{
	private BSQuickForm form;
	
	@BeforeEach
	public void setUp()
	{
		form = new BSQuickForm(new Dto());
	}
	
	@Test
	public void buildForm() throws Exception
	{
		System.out.println(form.toString(true));
	}
	
	@Test
	public void buildTextForm() throws Exception
	{
	}
	
	@Test
	public void buildDatePicker() throws Exception
	{
	}
	
	@Test
	public void buildEmailField() throws Exception
	{
	}
	
	@Test
	public void buildPasswordField() throws Exception
	{
	}
	
	@Test
	public void buildColourField() throws Exception
	{
	}
	
	@Test
	public void buildCheckboxField() throws Exception
	{
	}
	
	@Test
	public void buildFileUploadField() throws Exception
	{
	}
	
	@Test
	public void buildPrettyCheckbox() throws Exception
	{
	
	}
	
	@Test
	public void buildFieldLabel() throws Exception
	{
	}
	
	@Test
	public void buildRadioField() throws Exception
	{
	}
	
	@Test
	public void buildSearchField() throws Exception
	{
	}
	
	@Test
	public void buildSelectField() throws Exception
	{
	}
	
	@Test
	public void buildTelephoneField() throws Exception
	{
	}
	
	@Test
	public void buildTextAreaField() throws Exception
	{
	}
	
	@Test
	public void buildTimeField() throws Exception
	{
	}
	
	@Test
	public void buildUrlField() throws Exception
	{
	}
	
	public class Dto extends JavaScriptPart
	{
		private String name;
		private String description;
		private String textLine;
		private String textArea;
		private boolean booleanField;
		
		public String getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		public String getDescription()
		{
			return description;
		}
		
		public void setDescription(String description)
		{
			this.description = description;
		}
		
		public String getTextLine()
		{
			return textLine;
		}
		
		public void setTextLine(String textLine)
		{
			this.textLine = textLine;
		}
		
		public String getTextArea()
		{
			return textArea;
		}
		
		public void setTextArea(String textArea)
		{
			this.textArea = textArea;
		}
	}
}
