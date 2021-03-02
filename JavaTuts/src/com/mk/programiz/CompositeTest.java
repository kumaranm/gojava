package com.mk.programiz;

import java.util.List;
import java.util.ArrayList;

//Component
interface Component {
	public void show();
}

// Composite
class Composite implements Component {
	private List<Component> childComponents = new ArrayList<Component>();

	public void add(Component component) {
		childComponents.add(component);
	}

	public void remove(Component component) {
		childComponents.remove(component);
	}

	@Override
	public void show() {
		for (Component component : childComponents) {
			component.show();
		}
	}
}

// leaf
class Leaf implements Component {
	String name;

	public Leaf(String s) {
		name = s;
	}

	public void show() {
		System.out.println(name);
	}
}

public class CompositeTest {
	public static void main(String[] args) {
		Leaf leaf1 = new Leaf("1");
		Leaf leaf2 = new Leaf("2");
		Leaf leaf3 = new Leaf("3");
		Leaf leaf4 = new Leaf("4");
		Leaf leaf5 = new Leaf("5");
		Composite composite1 = new Composite();
		composite1.add(leaf1);
		composite1.add(leaf2);
		Composite composite2 = new Composite();
		composite2.add(leaf3);
		composite2.add(leaf4);
		composite2.add(leaf5);
		composite1.add(composite2);
		composite1.show();
	}
}