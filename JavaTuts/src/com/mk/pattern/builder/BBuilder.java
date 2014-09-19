package com.mk.pattern.builder;

//3. DEFINE THE BUILDER'S CONTRUCTION METHOD
//WHICH BUILDS AND RETURNS THE FINAL PRODUCT "T"
public interface BBuilder<T> extends BuildContract<BBuilder<T>> {
	T build();
}