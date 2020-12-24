package com.mk.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {

	public static void main(String[] args) {

		filter();
		filterWithStreams();
		filterObjectsWithStreams();
	}

	private static void filterObjectsWithStreams() {
		List<Person> personList = Arrays.asList(new Person("Dave",10), new Person("Peter",20), new Person("John",30),
				new Person("Abi",40));

		Person result = personList.stream()
				.filter(x -> "Dave".equals(x.getName()) && 10 == x.getId())
				.findAny()
				.orElse(null);
		
		System.out.println(result.getName());
		
		result = personList.stream()
				.filter(x -> {
					if("John".equals(x.getName()) && 30 == x.getId())
					{
						return true;
					}
					return false;
				}).findAny()
				.orElse(null);
		System.out.println(result.getName());
		
		String resultname = personList.stream()
				.filter(x -> "Dave".equals(x.getName()) && 10 == x.getId())
				.map(Person::getName)
				.findAny()
				.orElse("");
		System.out.println(resultname);
	}
	

	private static void filterWithStreams() {
		List<String> lines = Arrays.asList("one", "two", "three");
		List<String> result = lines.stream().filter(line -> !"three".equals(line)).collect(Collectors.toList());
		result.forEach(System.out::println);

	}

	private static void filter() {

		List<String> lines = Arrays.asList("one", "two", "three");
		List<String> filtered = filter1(lines, "three");

		for (String temp : filtered) {
			System.out.println(temp);
		}

	}

	private static List<String> filter1(List<String> lines, String string) {
		List<String> result = new ArrayList<>();
		for (String str : lines) {
			if (!string.equals(str)) {
				result.add(str);
			}
		}
		return result;
	}

}

class Person {

	private String name;
	private int id;

	Person(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	public int getId()
	{
		return id;
	}
}
