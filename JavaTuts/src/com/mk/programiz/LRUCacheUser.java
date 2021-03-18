package com.mk.programiz;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

class LRUCache<T, U> {

	private Map<T, U> cacheMap;
	private int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		cacheMap = Collections.synchronizedMap(new LinkedHashMap<>(this.capacity));
	}

	public void put(T key, U value) {
		if (cacheMap.size() == capacity) {
			Object firstKey = cacheMap.keySet().iterator().next();
			cacheMap.remove(firstKey);
		}
		cacheMap.put(key, value);
	}

	public U get(T key) {
		U value = null;
		if (cacheMap.containsKey(key)) {
			//add key back to end to track recent usage
			value = cacheMap.get(key);
			cacheMap.remove(key);
			cacheMap.put(key, value);
		}
		return value;
	}

	public void displayCache() {

		for (Entry<T, U> keys : cacheMap.entrySet()) {
			System.out.printf("%s - %s \n", keys.getKey(), keys.getValue());
		}
		System.out.printf("-----\n");
	}
}

public class LRUCacheUser {

	public static void main(String[] args) {

		LRUCache<String,String> cache = new LRUCache<>(5);

		cache.put("1", "one");
		cache.put("2", "one");
		cache.put("3", "one");
		cache.put("4", "one");
		cache.put("5", "one");
		cache.displayCache();

		cache.get("4");
		cache.get("3");
		cache.get("5");
		cache.get("4");
		cache.get("1");
		cache.displayCache();

		// 1
		// 2 1
		// 5 4 3 2 1
		// 4 5 3 2 1
		// 1 4 5 3 2
		// 6 1 4 5 3 2-> dropped Least used

		cache.put("6", "one");
		cache.displayCache();
		
		
		LRUCache<Integer,Integer> cache1 = new LRUCache<>(5);

		cache1.put(1,122);
		cache1.displayCache();
	}
}