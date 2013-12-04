package com.mk.solutions;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CollectionFunction
{
	public <e> List<e> function(List<e> list)
	{
		return new ArrayList<e>(new LinkedHashSet<e>(list));
	}
	
	public static void main(String[] args)
	{
		CollectionFunction fn = new CollectionFunction();
		
		List<String> lst = new ArrayList<>();
		lst.add("1");
		lst.add("3");
		lst.add("1");
		lst.add("2");
		lst.add("2");
		lst.add("4");
		System.out.println(lst);
		List<String> fnlist = fn.function(lst);
		System.out.println(fnlist);
		
	}
}