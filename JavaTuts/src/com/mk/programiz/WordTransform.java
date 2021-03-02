package com.mk.programiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordTransform {

	boolean[] visited;
	LinkedList<Integer>[] adjList;
	Map<Integer, String> map;

	public WordTransform(int vertices) {
		visited = new boolean[vertices];
		adjList = new LinkedList[vertices];
		map = new HashMap<>();

		for (int i = 0; i < vertices; i++) {
			adjList[i] = new LinkedList<>();
		}
	}

	public void addEdge(int src, int dest) {
		adjList[src].add(dest);
		adjList[dest].add(src);
	}

	void DFS(int vertice, List<Integer> list, int outputIndex) {

		if (vertice > visited.length) {
			return;
		}

		visited[vertice] = true;
		list.add(vertice);

		Iterator<Integer> i = adjList[vertice].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n]) {
				DFS(n, list, outputIndex);
				if (visited[vertice] && list.contains(outputIndex)) {
					return;
				} else if (visited[vertice] && !list.contains(outputIndex)) {
					list.clear();
					list.add(vertice);
				}
			}
		}
	}

	void BFS(int vertice, int size, String output) {

		boolean visited[] = new boolean[size];
		LinkedList<Integer> queue = new LinkedList<>();

		visited[vertice] = true;
		queue.add(vertice);

		while (!queue.isEmpty()) {
			vertice = queue.poll();
			System.out.print(map.get(vertice) + " ");
			if(map.get(vertice).equals(output)){
				return;
			}
			
			Iterator<Integer> i = adjList[vertice].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				} else {
					
				}
			}
		}
	}

	public static void main(String[] args) {

		String[] dictionary = { "POON", "PLEE", "SAME", "POIE", "PLEA", "PLIE", "POIN", "TOON" };
		String input = "PLIE";
		String output = "TOON";

		WordTransform wt = new WordTransform(dictionary.length);
		// wt.map.put(0, input);
		for (int i = 0; i < dictionary.length; i++) {
			wt.map.put(i, dictionary[i]);
		}

		for (int i = 0; i < dictionary.length - 1; i++) {

			for (int j = i + 1; j < dictionary.length; j++) {

				if (compare(dictionary[i], dictionary[j])) {
					wt.addEdge(i, j);
				}
			}
		}
		
		int inputIndex = -1;
		int outputIndex = -1;
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equalsIgnoreCase(input)) {
				inputIndex = i;
			}
			if (dictionary[i].equalsIgnoreCase(output)) {
				outputIndex = i;
			}
		}

		System.out.println(input + "-->" + output);
		if (inputIndex == -1 || outputIndex == -1) {
			System.out.println("Input or Output not present in dictionary");
			System.exit(0);
		}

		List<Integer> a1 = new ArrayList<>();
//		wt.BFS(inputIndex, dictionary.length, output); not working
		
		wt.DFS(inputIndex, a1, outputIndex);
		// System.out.println(a1);
		for (int i : a1) {
			System.out.print(wt.map.get(i) + " ");
			if (wt.map.get(i).equalsIgnoreCase(output)) {
				break;
			}
		}
	}

	private static boolean compare(String string1, String string2) {
		if (string1 != null && string2 != null && string1.length() == string2.length()) {
			int diff = 0;
			for (int i = 0; i < string2.length(); i++) {

				if (string1.contains(String.valueOf(string2.charAt(i)))) {
					string1 = string1.replaceFirst(String.valueOf(string2.charAt(i)), "");
					string2 = string2.replaceFirst(String.valueOf(string2.charAt(i)), "");
					i--;
				} else {
					++diff;
				}

				if (diff > 1) {
					return false;
				}

			}
		}
		return true;
	}

}
