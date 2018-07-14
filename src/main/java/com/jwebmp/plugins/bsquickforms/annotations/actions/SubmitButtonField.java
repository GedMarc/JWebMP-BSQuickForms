/*
 * Copyright (C) 2017 Marc Magon
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

package com.jwebmp.plugins.bsquickforms.annotations.actions;

import com.jwebmp.plugins.bootstrap.options.BSOffsetOptions;
import com.jwebmp.plugins.bootstrap.options.BSWidthOptions;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SubmitButtonField
{
	BSWidthOptions[] sizes() default {BSWidthOptions.col_xs_12, BSWidthOptions.col_md_4, BSWidthOptions.col_lg_3};

	BSOffsetOptions[] offsets() default {BSOffsetOptions.col_md_offset_2};

	String style() default "";

	String classes() default "";

	String label() default "";

	String iconClass() default "";

}
