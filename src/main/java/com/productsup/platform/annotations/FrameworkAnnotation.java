package com.productsup.platform.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.productsup.platform.enums.CategoryType;



@Retention(RUNTIME)
@Target(METHOD)
public @interface FrameworkAnnotation {
	
	
	
	public String[] author();
	
	public CategoryType[] category();

}
