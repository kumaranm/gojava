package com.mk.programiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PokerGraph {

	private boolean[] visited;
	private LinkedList<Integer> adjLists[];
	Map<Integer, String> map = new HashMap<>();

	public PokerGraph(int vertices) {

		visited = new boolean[vertices];
		adjLists = new LinkedList[vertices];

		for (int i = 0; i < vertices; i++) {
			visited[i] = false;
			adjLists[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int src, int dest) {
		adjLists[src].add(dest);
		adjLists[dest].add(src);
	}

	void DFS(List<Integer> list, int vertice) {

		if (vertice >= visited.length) {
			return;
		}

		visited[vertice] = true;
		list.add(vertice);
		// System.out.print(vertice + " ");

		Iterator<Integer> iterator = adjLists[vertice].listIterator();
		while (iterator.hasNext()) {
			int n = iterator.next();
			if (!visited[n]) {
				DFS(list, n);
				if (visited[vertice]) {
					return;
				}
			}

		}
	}

	public static void main(String[] args) {

		// String array = {"H3", }

		PokerGraph pg = new PokerGraph(4);

		pg.addEdge(0, 1);
		pg.addEdge(0, 2);
		pg.addEdge(1, 2);
		pg.addEdge(2, 3);

		// int[] a = new int[4];
		List<Integer> a = new ArrayList<>();
		// pg.DFS(a, 0, 0);

		// System.out.println(a);

		String[] pokerCards = { "H3", "H4", "S4", "D1", "D3", "H1", "B2" };
		Collections.sort(Arrays.asList(pokerCards));
		System.out.println(Arrays.toString(pokerCards));
		PokerGraph p = new PokerGraph(pokerCards.length);

		// convert array to graph
		for (int i = 0; i < pokerCards.length - 1; i++) {
			p.map.put(i, pokerCards[i]);
			String v1 = pokerCards[i];
			for (int j = i + 1; j < pokerCards.length; j++) {
				String v2 = pokerCards[j];
				if (v1.charAt(0) == v2.charAt(0) || v1.charAt(1) == v2.charAt(1)) {
					p.addEdge(i, j);
				}
			}
		}
		// add last value
		p.map.put(pokerCards.length - 1, pokerCards[pokerCards.length - 1]);

		List<Integer> a1 = new ArrayList<>();
		p.DFS(a1, 0);
		System.out.println(a1);
		for (int i : a1) {
			System.out.print(p.map.get(i) + " ");
		}
	}

}
