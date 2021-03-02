package com.mk.programiz;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams {

	public static void main(String[] args) {

		//map -> apply function
		List<Integer> numbers = Arrays.asList(9,11,2,3,4,5,6, 2,3);
		System.out.println(numbers);
		List<Integer> squares = numbers.stream().map(i -> i * i).collect(Collectors.toList());
		System.out.println(squares);

		//filter - select elements based on Predicate 
		List<String> names = Arrays.asList("Reflection", "Streams","Collections");
		System.out.println(names);
		List<String> res = names.stream().filter(s -> s.startsWith("S")).collect(Collectors.toList());
		System.out.println(res);
		
		//sorted - sort
		List<Integer> sorted = numbers.stream().sorted().collect(Collectors.toList());
		System.out.println(sorted);
		
		// collect
		Set<Integer> noDups = numbers.stream().collect(Collectors.toSet());
		System.out.println(noDups);
		
		//for Each  - iterate all elements
		names.stream().forEach(s -> System.out.println("i love " + s));
		
		//reduce - reduce to single value
		int even = numbers.stream().filter(x -> x % 2 == 0).reduce(1, (ans, i) -> ans + i);
		System.out.println(even);
		
		List<String> words = Arrays.asList("bd", "sds" ,"ssss","dd","sadsd");
		Optional<String> longestString = words.stream()
				.reduce((word1, word2) -> (word1.length() > word2.length()) ? word1 : word2);
		longestString.ifPresent(System.out::println);
		
		String[] wrds = {"sss" , "asd", "sdsd"};
		
		Optional<String> joined = Arrays.stream(wrds).reduce((w1, w2) -> w1 +"_" + w2);
		joined.ifPresent(System.out::println);
		
		List<Integer> array = Arrays.asList(-2, 0, 4, 6, 8); 
		int sum = array.stream().reduce(0, (x,y) -> x + y);
		System.out.println(sum);
		
		int product = IntStream.range(2, 20).reduce((num1, num2) -> num1 * num2).orElse(-1);
		System.out.println(product);
	}

}
