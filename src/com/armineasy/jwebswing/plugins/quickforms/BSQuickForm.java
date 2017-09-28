/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armineasy.jwebswing.plugins.quickforms;

import com.armineasy.injection.GuiceContext;
import com.armineasy.jwebswing.plugins.quickforms.annotations.actions.PrettyCheckboxField;
import lombok.Data;
import lombok.extern.java.Log;
import za.co.mmagon.jwebswing.base.ajax.AjaxResponse;
import za.co.mmagon.jwebswing.base.html.Input;
import za.co.mmagon.jwebswing.htmlbuilder.javascript.JavaScriptPart;
import za.co.mmagon.jwebswing.plugins.angularprettycheckboxes.PrettyCheckbox;
import za.co.mmagon.jwebswing.plugins.bootstrap.dropdown.BSDropDown;
import za.co.mmagon.jwebswing.plugins.bootstrap.forms.BSForm;
import za.co.mmagon.jwebswing.plugins.bootstrap.forms.BSFormLabel;
import za.co.mmagon.jwebswing.plugins.bootstrap.forms.controls.*;
import za.co.mmagon.jwebswing.plugins.bootstrap.forms.groups.BSFormGroup;
import za.co.mmagon.jwebswing.plugins.bootstrap.forms.groups.sets.BSComponentInputGroupOptions;
import za.co.mmagon.jwebswing.plugins.bootstrap.forms.groups.sets.BSFormCheckInput;
import za.co.mmagon.jwebswing.plugins.bootstrapswitch.BootstrapSwitchCheckBox;
import za.co.mmagon.jwebswing.plugins.quickforms.annotations.*;
import za.co.mmagon.jwebswing.plugins.quickforms.annotations.states.ReadOnlyWebComponent;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;

/**
 * @param <E> Entity class type if wanted
 * @param <J>
 *
 * @author Marc Magon
 * @since 25 Mar 2017
 */
@Log
@Data
public class BSQuickForm<E extends JavaScriptPart, J extends BSQuickForm<E, J>>
		extends BSForm<J>
{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * The object in question
	 */
	private E object;
	
	/**
	 * If this form should render as read only or as editable
	 */
	private boolean editable = true;
	
	private BSQuickForm()
	{

	}
	
	/**
	 * Constructs a new BSQuickForm
	 */
	public BSQuickForm(E anything)
	{
		this.object = anything;
		setID(anything.getClass().getCanonicalName().replace('.', '_'));
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			buildForm();
			AjaxResponse ac = GuiceContext.getInstance(AjaxResponse.class);
			//ac.addDto(getID(),getObject());
		}
		super.init();
	}
	
	public void setObject(E object)
	{
		this.object = object;
		setID(object.getClass().getCanonicalName().replace('.', '_'));
	}
	
	protected BSForm buildForm()
	{
		Field[] myFields = object.getClass().getDeclaredFields();
		for (Field field : myFields)
		{
			BSFormGroup group = new BSFormGroup();
			Input chb = null;
			
			if (field.isAnnotationPresent(LabelField.class))
			{
				LabelField lf = field.getDeclaredAnnotation(LabelField.class);
				BSFormLabel label = buildFieldLabel(field, lf, group);
				label.setID(getObject().getClass().getSimpleName() + "_" + field.getName() + "_lbl");
				group.setLabel(label);
			}
			
			if (field.isAnnotationPresent(HeaderField.class) && field.getType().isAssignableFrom(String.class))
			{
				HeaderField lf = field.getDeclaredAnnotation(HeaderField.class);
				BSFormLabel label = buildHeaderField(field, lf, group);
				group.add(label);
			}
			
			if (field.isAnnotationPresent(SubHeaderField.class) && field.getType().equals(String.class))
			{
				SubHeaderField lf = field.getDeclaredAnnotation(SubHeaderField.class);
				BSFormLabel label = buildSubHeaderField(field, lf, group);
				group.add(label);
			}
			if (field.isAnnotationPresent(DateTimePickerField.class) && field.getType().equals(LocalDateTime.class))
			{
				DateTimePickerField lf = field.getDeclaredAnnotation(DateTimePickerField.class);
				chb = buildDateTimePicker(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(DateTimePickerField.class) && field.getType().equals(Date.class))
			{
				DateTimePickerField lf = field.getDeclaredAnnotation(DateTimePickerField.class);
				chb = buildDateTimePicker(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(DatePickerField.class) && field.getType().equals(LocalDate.class))
			{
				DatePickerField lf = field.getDeclaredAnnotation(DatePickerField.class);
				chb = buildDatePicker(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(DatePickerField.class) && field.getType().equals(Date.class))
			{
				DatePickerField lf = field.getDeclaredAnnotation(DatePickerField.class);
				chb = buildDatePicker(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(EmailField.class) && field.getType().equals(String.class))
			{
				EmailField lf = field.getDeclaredAnnotation(EmailField.class);
				chb = buildEmailField(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(HiddenField.class))
			{
				HiddenField lf = field.getDeclaredAnnotation(HiddenField.class);
				/*Input chb = buildHiddenField(field,lf, group);
				group.setInputComponent(chb);*/
			}
			else if (field.isAnnotationPresent(NumberField.class)
					&& ((Long.class.isAssignableFrom(field.getType()) ||
					Integer.class.isAssignableFrom(field.getType()) ||
					BigDecimal.class.isAssignableFrom(field.getType()))
			))
			{
				NumberField lf = field.getDeclaredAnnotation(NumberField.class);
				chb = buildNumberField(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(NumberSpinnerField.class)
					&& ((Long.class.isAssignableFrom(field.getType()) ||
					Integer.class.isAssignableFrom(field.getType()) ||
					BigDecimal.class.isAssignableFrom(field.getType()))
			))
			{
				NumberSpinnerField lf = field.getDeclaredAnnotation(NumberSpinnerField.class);
				group.addClass(BSComponentInputGroupOptions.Input_Group);
				chb = buildNumberSpinnerField(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(PasswordField.class) && field.getType().equals(String.class))
			{
				PasswordField lf = field.getDeclaredAnnotation(PasswordField.class);
				chb = buildPasswordField(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(TextField.class))
			{
				TextField lf = field.getDeclaredAnnotation(TextField.class);
				chb = buildTextField(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(TextAreaField.class) && field.getType().equals(String.class))
			{
				TextAreaField lf = field.getDeclaredAnnotation(TextAreaField.class);
				chb = buildTextAreaField(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(PrettyCheckboxField.class) && field.getType().equals(Boolean.class))
			{
				PrettyCheckboxField lf = field.getDeclaredAnnotation(PrettyCheckboxField.class);
				chb = buildPrettyCheckbox(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(TelephoneField.class) && field.getType().equals(String.class))
			{
				TelephoneField lf = field.getDeclaredAnnotation(TelephoneField.class);
				chb = buildTelephoneField(field, lf, group);
				group.setInputComponent(chb);
			}
			else if (field.isAnnotationPresent(SwitchField.class))
			{
				SwitchField lf = field.getDeclaredAnnotation(SwitchField.class);
				chb = buildSwitchField(field, lf, group);
				group.setInputComponent(chb);
			}
			
			if (chb != null || !group.getChildren().isEmpty())
			{
				if (chb != null)
				{
					chb.setID(getObject().getClass().getSimpleName() + "_" + field.getName());
					
					if (field.isAnnotationPresent(ReadOnlyWebComponent.class))
					{
						chb.addAttribute("readonly", "");
						chb.addAttribute("disabled", "");
					}
				}
				add(group);
			}
		}
		return this;
	}
	
	protected void setValue(Field field, Input input)
	{
		try
		{
			field.setAccessible(true);
			if (field.get(getObject()) != null)
			{
				Object value = field.get(getObject());
				input.setValue(value.toString());
			}
		}
		catch (IllegalAccessException e)
		{
			log.warning("Unable to access field : " + field.getName() + " in " + getObject().getClass());
		}
	}
	
	protected BSFormTextInput buildTextField(Field field, TextField anno, BSFormGroup group)
	{
		
		BSFormTextInput input = new BSFormTextInput();
		input.bind(getID() + "." + field.getName());
		if (anno.minLength() != Integer.MIN_VALUE)
		{
			input.setMinimumLength(anno.minLength());
		}
		if (anno.maxLength() != Integer.MIN_VALUE)
		{
			input.setMaximumLength(anno.maxLength());
		}
		
		input.setPlaceholder(anno.placeholder());
		
		group.setInputComponent(input);
		group.setAngularValidation(true);
		group.setShowControlFeedback(anno.showControlFeedback());
		
		setValue(field, input);
		
		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage().isEmpty())
		{
			group.setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage().isEmpty())
		{
			group.setPatternMessage(anno.patternMessage());
		}
		
		if (!anno.style().isEmpty())
		{
			input.addStyle(anno.style());
		}
		if (!anno.regex().isEmpty())
		{
			input.setPattern(anno.regex(), true);
		}
		if (!anno.regexBind().isEmpty())
		{
			input.setPattern(anno.regexBind());
		}
		
		return input;
	}
	
	protected BSFormDateInput buildDateTimePicker(Field field, DateTimePickerField anno, BSFormGroup group)
	{
		return null;
	}
	
	protected BSFormDateInput buildDatePicker(Field field, DatePickerField anno, BSFormGroup group)
	{
		return null;
	}
	
	protected BSFormEmailInput buildEmailField(Field field, EmailField anno, BSFormGroup group)
	{
		BSFormEmailInput input = new BSFormEmailInput();
		input.bind(getID() + "." + field.getName());
		
		group.setInputComponent(input);
		group.setAngularValidation(true);
		group.setShowControlFeedback(true);
		
		setValue(field, input);
		
		if (!anno.placeholder().isEmpty())
		{
			input.setPlaceholder(anno.placeholder());
		}
		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage().isEmpty())
		{
			group.setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage().isEmpty())
		{
			group.setPatternMessage(anno.patternMessage());
		}
		
		return input;
	}
	
	protected BSFormLabel buildSubHeaderField(Field field, SubHeaderField anno, BSFormGroup group)
	{
		BSFormLabel label = new BSFormLabel();
		label.setTag("H3");
		label.setText(anno.label());
		return label;
	}
	
	protected BSFormLabel buildHeaderField(Field field, HeaderField anno, BSFormGroup group)
	{
		BSFormLabel label = new BSFormLabel();
		label.setTag("H3");
		label.setText(anno.label());
		return label;
	}
	
	protected BSFormLabel buildFieldLabel(Field field, LabelField label, BSFormGroup group)
	{
		BSFormLabel component = new BSFormLabel();
		component.setText(label.label());
		return component;
	}
	
	protected BSFormPasswordInput buildPasswordField(Field field, PasswordField anno, BSFormGroup group)
	{
		BSFormPasswordInput input = new BSFormPasswordInput();
		input.bind(getID() + "." + field.getName());
		if (!(anno.minLength() == Integer.MIN_VALUE))
		{
			input.setMinimumLength(anno.minLength());
			group.setMinLengthMessage(anno.minLengthMessage());
		}
		if (!(anno.maxLength() == Integer.MIN_VALUE))
		{
			input.setMaximumLength(anno.maxLength());
			group.setMinLengthMessage(anno.maxLengthMessage());
		}
		input.setPlaceholder("Password");
		
		group.setInputComponent(input);
		group.setAngularValidation(true);
		group.setShowControlFeedback(true);
		
		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage().isEmpty())
		{
			group.setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage().isEmpty())
		{
			group.setPatternMessage(anno.patternMessage());
		}
		
		setValue(field, input);
		
		return input;
	}
	
	protected BSFormColourInput buildColourField(Field field, BSFormGroup group)
	{
		return null;
	}
	
	protected BSFormCheckInput buildCheckboxField(Field field, SwitchField anno, BSFormGroup group)
	{
		return null;
	}
	
	protected BSFormFileInput buildFileUploadField(Field field, BSFormGroup group)
	{
		return null;
	}
	
	protected BSFormRadioInput buildRadioField(Field field, BSFormGroup group)
	{
		return null;
	}
	
	protected BSFormSearchInput buildSearchField(Field field, BSFormGroup group)
	{
		return null;
	}
	
	protected BootstrapSwitchCheckBox buildSwitchField(Field field, SwitchField anno, BSFormGroup group)
	{
		BootstrapSwitchCheckBox input = new BootstrapSwitchCheckBox();
		input.bind(getID() + "." + field.getName());
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
			log.log(Level.WARNING, "Unable to set checked for field [" + field.getName() + "]");
		}

		group.setInputComponent(input);
		
		if (anno.required())
		{
			input.setRequired();
			group.setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.onText().isEmpty())
		{
			input.getOptions().setOnText(anno.onText());
		}
		if (!anno.offText().isEmpty())
		{
			input.getOptions().setOffText(anno.offText());
		}
		if (!anno.requiredMessage().isEmpty())
		{
			group.setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage().isEmpty())
		{
			group.setPatternMessage(anno.patternMessage());
		}
		
		group.setAngularValidation(true);
		group.setShowControlFeedback(anno.showControlFeedback());
		
		setValue(field, input);
		
		return input;
	}
	
	protected BSDropDown buildSelectDropDownField(Field field, SelectField anno, BSFormGroup group)
	{
		return null;
	}
	
	protected BSFormSelectInput buildSelectField(Field field, SelectField anno, BSFormGroup group)
	{
		return null;
	}
	
	protected BSFormTelInput buildTelephoneField(Field field, TelephoneField anno, BSFormGroup group)
	{
		return null;
	}
	
	/**
	 * Builds a pretty check box using the angular plugin
	 * @param field
	 * @param anno
	 * @param group
	 * @return
	 */
	protected PrettyCheckbox buildPrettyCheckbox(Field field, PrettyCheckboxField anno, BSFormGroup group)
	{
		PrettyCheckbox input = new PrettyCheckbox();
		input.bind(getID() + "." + field.getName());
		
		input.setLabel(anno.label());
		input.setValue(anno.value());
		if(anno.disabled())
		input.setDisabled(anno.disabled());
		if(anno.labelLeft())
			input.setLabelLeft(anno.labelLeft());
		if(anno.multiple())
			input.setMultiple(anno.multiple());
		
		group.setInputComponent(input);
		group.setAngularValidation(true);
		
		group.setShowControlFeedback(anno.showControlFeedback());
		
		setValue(field, input);
		
		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage().isEmpty())
		{
			group.setRequiredMessage(anno.requiredMessage());
		}
		
		return input;
	}
	
	
	protected BSFormTextAreaInput buildTextAreaField(Field field, TextAreaField anno, BSFormGroup group)
	{
		BSFormTextAreaInput input = new BSFormTextAreaInput();
		input.bind(getID() + "." + field.getName());
		if (!(anno.minLength() == Integer.MIN_VALUE))
		{
			input.setMinimumLength(anno.minLength());
			group.setMinLengthMessage(anno.minLengthMessage());
		}
		
		if (!(anno.maxLength() == Integer.MIN_VALUE))
		{
			input.setMaximumLength(anno.maxLength());
			group.setMaxLengthMessage(anno.maxLengthMessage());
		}
		
		if (anno.required())
		{
			input.setRequired();
		}
		
		input.setPlaceholder(anno.placeholder());
		
		group.setInputComponent(input);
		group.setAngularValidation(true);
		group.setShowControlFeedback(anno.showControlFeedback());
		
		setValue(field, input);
		
		if (anno.required())
		{
			input.setRequired();
		}
		if (!anno.requiredMessage().isEmpty())
		{
			group.setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage().isEmpty())
		{
			group.setPatternMessage(anno.patternMessage());
		}
		
		return input;
	}
	
	public BSFormNumberInput buildNumberField(Field field, NumberField anno, BSFormGroup group)
	{
		BSFormNumberInput input = new BSFormNumberInput();
		input.bind(getID() + "." + field.getName());
		
		if (anno.maximumValue() != Integer.MIN_VALUE)
		{
			input.setMaximumLength(anno.maximumValue());
		}
		if (anno.minimumValue() != Integer.MIN_VALUE)
		{
			input.setMinimumLength(anno.minimumValue());
		}
		
		group.setInputComponent(input);
		group.setAngularValidation(true);
		group.setShowControlFeedback(anno.showControlFeedback());
		
		if (anno.required())
		{
			input.setRequired();
		}
		
		if (!anno.requiredMessage().isEmpty())
		{
			group.setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage().isEmpty())
		{
			group.setPatternMessage(anno.patternMessage());
		}
		
		setValue(field, input);
		
		return input;
	}
	
	public BSFormNumberInput buildNumberSpinnerField(Field field, NumberSpinnerField anno, BSFormGroup group)
	{
		BSFormNumberInput input = new BSFormNumberInput();
		input.bind(getID() + "." + field.getName());
		
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
		
		group.setInputComponent(input);
		group.setAngularValidation(true);
		group.setShowControlFeedback(anno.showControlFeedback());
		
		if (!anno.requiredMessage().isEmpty())
		{
			group.setRequiredMessage(anno.requiredMessage());
		}
		if (!anno.patternMessage().isEmpty())
		{
			group.setPatternMessage(anno.patternMessage());
		}
		
		setValue(field, input);
		
		return input;
	}
	
	public BSFormTimeInput buildTimeField(Field field, BSFormGroup group)
	{
		return null;
	}
	
	public BSFormUrlInput buildUrlField(Field field, BSFormGroup group)
	{
		return null;
	}
	
}
