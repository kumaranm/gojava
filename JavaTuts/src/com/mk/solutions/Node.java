package com.mk.solutions;

import java.util.ArrayList;
import java.util.List;

public class Node
{
	private String name;
	private List<Node> children = new ArrayList<Node>();
	private Node parent;

	public Node getParent()
	{
		return parent;
	}

	public void setParent(Node parent)
	{
		this.parent = parent;
	}

	public Node(String name) {
		this.name = name;
	}

	public void addChild(Node child)
	{
		children.add(child);
	}

	public void removeChild(Node child)
	{
		children.remove(child);
	}

	public String toString()
	{
		return name;
	}
}