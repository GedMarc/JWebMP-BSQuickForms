package com.armineasy.jwebswing.plugins.quickforms.annotations.actions;

import za.co.mmagon.jwebswing.plugins.bootstrap.componentoptions.BSComponentOffsetOptions;
import za.co.mmagon.jwebswing.plugins.bootstrap.componentoptions.BSComponentWidthOptions;

import java.lang.annotation.*;

@Target(
		{
				ElementType.FIELD, ElementType.TYPE_USE
		})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SubmitButtonField
{
	public BSComponentWidthOptions[] sizes() default
			{
					BSComponentWidthOptions.col_xs_12, BSComponentWidthOptions.col_md_4, BSComponentWidthOptions.col_lg_3
			};
	
	public BSComponentOffsetOptions[] offsets() default
			{
					BSComponentOffsetOptions.col_md_offset_2
			};
	
	
	public String style() default "";

	public String classes() default "";
	
	public String label() default "";

	public String iconClass() default "";
	
}
