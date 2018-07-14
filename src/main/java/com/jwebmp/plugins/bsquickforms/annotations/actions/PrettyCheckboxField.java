package com.jwebmp.plugins.bsquickforms.annotations.actions;

import java.lang.annotation.*;

@Target(
		{
				ElementType.FIELD, ElementType.TYPE_USE
		})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PrettyCheckboxField
{
	public String value() default "";

	public String label() default "";

	public boolean disabled() default false;

	public boolean labelLeft() default false;

	public boolean multiple() default false;

	public boolean required() default false;

	public String requiredMessage() default "This field is required";

	public boolean showControlFeedback() default true;

}
