package com.mk.problem.problem1;


public class P1Main
{
	public static void main(String[] args)
	{
		Loader L = new Loader();
		L.loadNumeralMappingFile("numeralmappings.txt");
		L.loadCreditMappingFile("creditmappings.txt");
		System.out.println(CodeMapper.getMap());
		System.out.println(MetalCreditMapper.getMap());

		L.loadQuestionsFile("questions.txt");
	}
}
