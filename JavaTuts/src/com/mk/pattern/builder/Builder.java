package com.mk.pattern.builder;

//3. DEFINE THE BUILDER CONTRUCTION METHOD
//WHICH BUILDS AND RETURNS THE FINAL PRODUCT "T"
public interface Builder<T> extends Recipe<Builder<T>> {
	T build();
}