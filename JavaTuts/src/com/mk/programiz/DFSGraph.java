package com.mk.programiz;

import java.util.Iterator;
import java.util.LinkedList;

public class DFSGraph {

	private boolean[] visited;
	private LinkedList<Integer> adjList[];
	int V;

	public DFSGraph(int vertices) {
		V = vertices;
		visited = new boolean[vertices];
		adjList = new LinkedList[vertices];

		for (int i = 0; i < vertices; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
	}

	void addEdge(int src, int dest) {
		adjList[src].add(dest);
	}

	void DFS(int vertice) {

		visited[vertice] = true;
		System.out.print(vertice + " ");

		Iterator<Integer> i = adjList[vertice].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n]) {
				DFS(n);
			}
//			System.out.println();
		}
	}

	// BFS algorithm
	void BFS(int vertice) {

		boolean visited[] = new boolean[V];

		LinkedList<Integer> queue = new LinkedList();

		visited[vertice] = true;
		queue.add(vertice);

		while (queue.size() != 0) {
			vertice = queue.poll();
			System.out.print(vertice + " ");

			Iterator<Integer> i = adjList[vertice].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	public static void main(String[] args) {
		DFSGraph g = new DFSGraph(5);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(2, 4);

		System.out.print("DFS -> ");
		g.DFS(0);
		
		System.out.print("\nBFS -> ");
		g.BFS(0);

	}

}
