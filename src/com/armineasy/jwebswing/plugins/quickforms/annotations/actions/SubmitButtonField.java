package com.armineasy.jwebswing.plugins.quickforms.annotations.actions;

import za.co.mmagon.jwebswing.plugins.bootstrap.componentoptions.BSOffsetOptions;
import za.co.mmagon.jwebswing.plugins.bootstrap.componentoptions.BSWidthOptions;

import java.lang.annotation.*;

@Target(
		{
				ElementType.FIELD, ElementType.TYPE_USE
		})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SubmitButtonField
{
	BSWidthOptions[] sizes() default
			{
					BSWidthOptions.col_xs_12, BSWidthOptions.col_md_4, BSWidthOptions.col_lg_3
			};

	BSOffsetOptions[] offsets() default
			{
					BSOffsetOptions.col_md_offset_2
			};


	String style() default "";

	String classes() default "";

	String label() default "";

	String iconClass() default "";

}
