package com.mk.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
//import java.util.stream.Stream;

public class WordOccurrence {

	public static void main(String args[]) {

		String str = "Lorem adipising ipsum dolor sit Lorem amet Consectetur adipising elit Lorem ipsum dolor";

//		List<String> list = Stream.of(str).map(w -> w.split("\\s+")).flatMap(Arrays::stream)
//				.collect(Collectors.toList());

		List<String> strs = Arrays.asList(str.split(" "));
		
		Map<String, Integer> wordCounter = strs.stream()
				.collect(Collectors.toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));

		AtomicInteger a = new AtomicInteger();
		System.out.println(a);
		a.getAndIncrement();
		System.out.println(a);
		
		System.out.println(wordCounter);
	}
}
