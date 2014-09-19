package com.mk.pattern.builder;

public class NoSugar implements Ingredient {

    private int gram;
    private int calories;
    private String unitPrice;
    
    public NoSugar(int deciLiter){this.gram=deciLiter;}
    
    public NoSugar(int gram, int calories, String unitPrice) {
        super();
        this.gram = gram;
        this.calories = calories;
        this.unitPrice = unitPrice;
    }

    @Override public void printName() {System.out.printf(" No Sugar");}
    @Override public String getUnitPrice() {return unitPrice;}
    @Override public void printCalories() {System.out.printf(" 0kc");}
    public int getGram() {return gram;}
    public void setGram(int gram) {this.gram = gram;}
    public int getCalories() {return calories;}
    public void setCalories(int calories) {this.calories = calories;}
    public void setUnitPrice(String unitPrice) {this.unitPrice = unitPrice;}
}