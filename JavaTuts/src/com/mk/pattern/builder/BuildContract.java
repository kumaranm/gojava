package com.mk.pattern.builder;

//2. THE BUILDER METHOD WILL ADD 
//PARTS RETURNING THE BUILDER ITSELF
public interface BuildContract<B> {
	B mount(Part part);
}