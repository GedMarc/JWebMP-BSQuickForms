/*
 * Copyright (C) 2017 GedMarc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jwebmp.plugins.bsquickforms;

import com.jwebmp.core.utilities.StaticStrings;
import com.jwebmp.logger.LogFactory;
import com.jwebmp.plugins.bootstrap.forms.BSFormLabel;
import com.jwebmp.plugins.bootstrap.forms.controls.*;
import com.jwebmp.plugins.bootstrap.forms.groups.BSFormGroup;
import com.jwebmp.plugins.bootstrapswitch.BootstrapSwitchCheckBox;
import com.jwebmp.plugins.quickforms.IQuickForm;
import com.jwebmp.plugins.quickforms.QuickFormFieldGroup;
import com.jwebmp.plugins.quickforms.QuickForms;
import com.jwebmp.plugins.quickforms.annotations.*;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <E>
 * 		Entity class type if wanted
 * @param <J>
 *
 * @author GedMarc
 * @since 25 Mar 2017
 */
public abstract class BSQuickForm<E extends Serializable, G extends BSFormGroup<G>, J extends BSQuickForm<E, G, J>>
		extends QuickForms<QuickFormFieldGroup<G, ? extends QuickFormFieldGroup>, J>
		implements IQuickForm<QuickFormFieldGroup<G, ? extends QuickFormFieldGroup>>
{


	private static final Logger log = LogFactory.getLog("BSQuickForms");

	/**
	 * Constructs a new BSQuickForm
	 */
	public BSQuickForm(E anything)
	{
		super(anything);
	}

	@Override
	public String getDtoName()
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildTextField(Field field, TextField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{

		BSFormTextInput input = new BSFormTextInput();
		input.bind(getID() + StaticStrings.STRING_DOT + field.getName());
		if (anno.minLength() != Integer.MIN_VALUE)
		{
			input.setMinimumLength(anno.minLength());
		}
		if (anno.maxLength() != Integer.MIN_VALUE)
		{
			input.setMaximumLength(anno.maxLength());
		}

		input.setPlaceholder(anno.placeholder());

		fieldGroup.getGroup()
		          .setInputComponent(input);
		fieldGroup.getGroup()
		          .setAngularValidation(true);
		fieldGroup.getGroup()
		          .setShowControlFeedback(anno.showControlFeedback());

		setValue(field, input);

		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setPatternMessage(anno.patternMessage());
		}

		if (!anno.style()
		         .isEmpty())
		{
			input.addStyle(anno.style());
		}
		if (!anno.regex()
		         .isEmpty())
		{
			input.setPattern(anno.regex(), true);
		}
		if (!anno.regexBind()
		         .isEmpty())
		{
			input.setPattern(anno.regexBind());
		}

		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildDateTimePicker(Field field, DateTimePickerField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildDatePicker(Field field, DatePickerField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildEmailField(Field field, EmailField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BSFormEmailInput input = new BSFormEmailInput();
		input.bind(getID() + StaticStrings.STRING_DOT + field.getName());

		fieldGroup.getGroup()
		          .setInputComponent(input);
		fieldGroup.getGroup()
		          .setAngularValidation(true);
		fieldGroup.getGroup()
		          .setShowControlFeedback(true);

		setValue(field, input);

		if (!anno.placeholder()
		         .isEmpty())
		{
			input.setPlaceholder(anno.placeholder());
		}
		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setPatternMessage(anno.patternMessage());
		}

		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildSubHeaderField(Field field, SubHeaderField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BSFormLabel label = new BSFormLabel();
		label.setTag("H3");
		label.setText(anno.label());
		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildHeaderField(Field field, HeaderField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BSFormLabel label = new BSFormLabel();
		label.setTag("H3");
		label.setText(anno.label());
		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildFieldLabel(Field field, LabelField label, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BSFormLabel component = new BSFormLabel();
		component.setText(label.label());
		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildPasswordField(Field field, PasswordField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BSFormPasswordInput input = new BSFormPasswordInput();
		input.bind(getID() + StaticStrings.STRING_DOT + field.getName());
		if (anno.minLength() != Integer.MIN_VALUE)
		{
			input.setMinimumLength(anno.minLength());
			fieldGroup.getGroup()
			          .setMinLengthMessage(anno.minLengthMessage());
		}
		if (anno.maxLength() != Integer.MIN_VALUE)
		{
			input.setMaximumLength(anno.maxLength());
			fieldGroup.getGroup()
			          .setMinLengthMessage(anno.maxLengthMessage());
		}
		input.setPlaceholder("Password");

		fieldGroup.getGroup()
		          .setInputComponent(input);
		fieldGroup.getGroup()
		          .setAngularValidation(true);
		fieldGroup.getGroup()
		          .setShowControlFeedback(true);

		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setPatternMessage(anno.patternMessage());
		}

		setValue(field, input);

		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildColourField(Field field, ColorField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildCheckboxField(Field field, CheckboxField annotation, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildFileUploadField(Field field, FileField annotation, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildRadioField(Field field, RadioField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildSearchField(Field field, SearchField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildNumberSpinnerField(Field field, NumberSpinnerField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BSFormNumberInput input = new BSFormNumberInput();
		input.bind(getID() + StaticStrings.STRING_DOT + field.getName());

		if (anno.maximumValue() != Integer.MIN_VALUE)
		{
			input.setMaximumLength(anno.maximumValue());
		}
		if (anno.minimumValue() != Integer.MIN_VALUE)
		{
			input.setMinimumLength(anno.minimumValue());
		}

		if (anno.required())
		{
			input.setRequired();
		}

		fieldGroup.getGroup()
		          .setInputComponent(input);
		fieldGroup.getGroup()
		          .setAngularValidation(true);
		fieldGroup.getGroup()
		          .setShowControlFeedback(anno.showControlFeedback());

		if (!anno.requiredMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setPatternMessage(anno.patternMessage());
		}

		setValue(field, input);

		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildSwitchField(Field field, SwitchField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BootstrapSwitchCheckBox input = new BootstrapSwitchCheckBox();
		input.bind(getID() + StaticStrings.STRING_DOT + field.getName());
		if (anno.required())
		{
			input.setRequired();
		}
		try
		{
			field.setAccessible(true);
			if (field.get(getObject()) != null)
			{
				input.setChecked(field.getBoolean(getObject()));
			}
		}
		catch (IllegalAccessException e)
		{
			BSQuickForm.log.log(Level.WARNING, "Unable to set checked for field [" + field.getName() + "]", e);
		}

		fieldGroup.getGroup()
		          .setInputComponent(input);

		if (anno.required())
		{
			input.setRequired();
			fieldGroup.getGroup()
			          .setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.onText()
		         .isEmpty())
		{
			input.getOptions()
			     .setOnText(anno.onText());
		}
		if (!anno.offText()
		         .isEmpty())
		{
			input.getOptions()
			     .setOffText(anno.offText());
		}
		if (!anno.requiredMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setPatternMessage(anno.patternMessage());
		}

		fieldGroup.getGroup()
		          .setAngularValidation(true);
		fieldGroup.getGroup()
		          .setShowControlFeedback(anno.showControlFeedback());

		setValue(field, input);

		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildSelectField(Field field, SelectField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildTelephoneField(Field field, TelephoneField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildTextAreaField(Field field, TextAreaField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BSFormTextAreaInput input = new BSFormTextAreaInput();
		input.bind(getID() + StaticStrings.STRING_DOT + field.getName());
		if (anno.minLength() != Integer.MIN_VALUE)
		{
			input.setMinimumLength(anno.minLength());
			fieldGroup.getGroup()
			          .setMinLengthMessage(anno.minLengthMessage());
		}

		if (anno.maxLength() != Integer.MIN_VALUE)
		{
			input.setMaximumLength(anno.maxLength());
			fieldGroup.getGroup()
			          .setMaxLengthMessage(anno.maxLengthMessage());
		}

		if (anno.required())
		{
			input.setRequired();
		}

		input.setPlaceholder(anno.placeholder());

		fieldGroup.getGroup()
		          .setInputComponent(input);
		fieldGroup.getGroup()
		          .setAngularValidation(true);
		fieldGroup.getGroup()
		          .setShowControlFeedback(anno.showControlFeedback());

		setValue(field, input);

		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setPatternMessage(anno.patternMessage());
		}

		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildNumberField(Field field, NumberField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		BSFormNumberInput input = new BSFormNumberInput();
		input.bind(getID() + StaticStrings.STRING_DOT + field.getName());

		if (anno.maximumValue() != Integer.MIN_VALUE)
		{
			input.setMaximumLength(anno.maximumValue());
		}
		if (anno.minimumValue() != Integer.MIN_VALUE)
		{
			input.setMinimumLength(anno.minimumValue());
		}

		fieldGroup.getGroup()
		          .setInputComponent(input);
		fieldGroup.getGroup()
		          .setAngularValidation(true);
		fieldGroup.getGroup()
		          .setShowControlFeedback(anno.showControlFeedback());

		if (anno.required())
		{
			input.setRequired();
		}

		if (!anno.requiredMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage()
		         .isEmpty())
		{
			fieldGroup.getGroup()
			          .setPatternMessage(anno.patternMessage());
		}

		setValue(field, input);

		return fieldGroup;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildTimeField(Field field, TimePickerField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildUrlField(Field field, UrlField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

	@Override
	public QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> buildHiddenField(Field field, HiddenField anno, QuickFormFieldGroup<G, ? extends QuickFormFieldGroup> fieldGroup)
	{
		return null;
	}

}
