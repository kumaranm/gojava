package com.mk.programiz;


class fibheapnode {
	fibheapnode parent;
	fibheapnode left;
	fibheapnode right;
	fibheapnode child;
	int degree;
	boolean mark;
	int key;

	public fibheapnode() {
		this.degree = 0;
		this.mark = false;
		this.parent = null;
		this.left = this;
		this.right = this;
		this.child = null;
		this.key = Integer.MAX_VALUE;
	}

	fibheapnode(int x) {
		this();
		this.key = x;
	}

	void set_parent(fibheapnode x) {
		this.parent = x;
	}

	fibheapnode get_parent() {
		return this.parent;
	}

	void set_left(fibheapnode x) {
		this.left = x;
	}

	fibheapnode get_left() {
		return this.left;
	}

	void set_right(fibheapnode x) {
		this.right = x;
	}

	fibheapnode get_right() {
		return this.right;
	}

	void set_child(fibheapnode x) {
		this.child = x;
	}

	fibheapnode get_child() {
		return this.child;
	}

	void set_degree(int x) {
		this.degree = x;
	}

	int get_degree() {
		return this.degree;
	}

	void set_mark(boolean m) {
		this.mark = m;
	}

	boolean get_mark() {
		return this.mark;
	}

	void set_key(int x) {
		this.key = x;
	}

	int get_key() {
		return this.key;
	}
}

public class fibHeap {
	fibheapnode min;
	int n;
	boolean trace;
	fibheapnode found;

	public boolean get_trace() {
		return trace;
	}

	public void set_trace(boolean t) {
		this.trace = t;
	}

	public static fibHeap create_heap() {
		return new fibHeap();
	}

	fibHeap() {
		min = null;
		n = 0;
		trace = false;
	}

	private void insert(fibheapnode x) {
		if (min == null) {
			min = x;
			x.set_left(min);
			x.set_right(min);
		} else {
			x.set_right(min);
			x.set_left(min.get_left());
			min.get_left().set_right(x);
			min.set_left(x);
			if (x.get_key() < min.get_key())
				min = x;
		}
		n += 1;
	}

	public void insert(int key) {
		insert(new fibheapnode(key));
	}

	public void display() {
		display(min);
		System.out.println();
	}

	private void display(fibheapnode c) {
		System.out.print("(");
		if (c == null) {
			System.out.print(")");
			return;
		} else {
			fibheapnode temp = c;
			do {
				System.out.print(temp.get_key());
				fibheapnode k = temp.get_child();
				display(k);
				System.out.print("->");
				temp = temp.get_right();
			} while (temp != c);
			System.out.print(")");
		}
	}

	public static void merge_heap(fibHeap H1, fibHeap H2, fibHeap H3) {
		H3.min = H1.min;

		if (H1.min != null && H2.min != null) {
			fibheapnode t1 = H1.min.get_left();
			fibheapnode t2 = H2.min.get_left();
			H1.min.set_left(t2);
			t1.set_right(H2.min);
			H2.min.set_left(t1);
			t2.set_right(H1.min);
		}
		if (H1.min == null || (H2.min != null && H2.min.get_key() < H1.min.get_key()))
			H3.min = H2.min;
		H3.n = H1.n + H2.n;
	}

	public int find_min() {
		return this.min.get_key();
	}

	private void display_node(fibheapnode z) {
		System.out.println("right: " + ((z.get_right() == null) ? "-1" : z.get_right().get_key()));
		System.out.println("left: " + ((z.get_left() == null) ? "-1" : z.get_left().get_key()));
		System.out.println("child: " + ((z.get_child() == null) ? "-1" : z.get_child().get_key()));
		System.out.println("degree " + z.get_degree());
	}

	public int extract_min() {
		fibheapnode z = this.min;
		if (z != null) {
			fibheapnode c = z.get_child();
			fibheapnode k = c, p;
			if (c != null) {
				do {
					p = c.get_right();
					insert(c);
					c.set_parent(null);
					c = p;
				} while (c != null && c != k);
			}
			z.get_left().set_right(z.get_right());
			z.get_right().set_left(z.get_left());
			z.set_child(null);
			if (z == z.get_right())
				this.min = null;
			else {
				this.min = z.get_right();
				this.consolidate();
			}
			this.n -= 1;
			return z.get_key();
		}
		return Integer.MAX_VALUE;
	}

	public void consolidate() {
		double phi = (1 + Math.sqrt(5)) / 2;
		int Dofn = (int) (Math.log(this.n) / Math.log(phi));
		fibheapnode[] A = new fibheapnode[Dofn + 1];
		for (int i = 0; i <= Dofn; ++i)
			A[i] = null;
		fibheapnode w = min;
		if (w != null) {
			fibheapnode check = min;
			do {
				fibheapnode x = w;
				int d = x.get_degree();
				while (A[d] != null) {
					fibheapnode y = A[d];
					if (x.get_key() > y.get_key()) {
						fibheapnode temp = x;
						x = y;
						y = temp;
						w = x;
					}
					fib_heap_link(y, x);
					check = x;
					A[d] = null;
					d += 1;
				}
				A[d] = x;
				w = w.get_right();
			} while (w != null && w != check);
			this.min = null;
			for (int i = 0; i <= Dofn; ++i) {
				if (A[i] != null) {
					insert(A[i]);
				}
			}
		}
	}

	// Linking operation
	private void fib_heap_link(fibheapnode y, fibheapnode x) {
		y.get_left().set_right(y.get_right());
		y.get_right().set_left(y.get_left());

		fibheapnode p = x.get_child();
		if (p == null) {
			y.set_right(y);
			y.set_left(y);
		} else {
			y.set_right(p);
			y.set_left(p.get_left());
			p.get_left().set_right(y);
			p.set_left(y);
		}
		y.set_parent(x);
		x.set_child(y);
		x.set_degree(x.get_degree() + 1);
		y.set_mark(false);
	}

	// Search operation
	private void find(int key, fibheapnode c) {
		if (found != null || c == null)
			return;
		else {
			fibheapnode temp = c;
			do {
				if (key == temp.get_key())
					found = temp;
				else {
					fibheapnode k = temp.get_child();
					find(key, k);
					temp = temp.get_right();
				}
			} while (temp != c && found == null);
		}
	}

	public fibheapnode find(int k) {
		found = null;
		find(k, this.min);
		return found;
	}

	public void decrease_key(int key, int nval) {
		fibheapnode x = find(key);
		decrease_key(x, nval);
	}

	// Decrease key operation
	private void decrease_key(fibheapnode x, int k) {
		if (k > x.get_key())
			return;
		x.set_key(k);
		fibheapnode y = x.get_parent();
		if (y != null && x.get_key() < y.get_key()) {
			cut(x, y);
			cascading_cut(y);
		}
		if (x.get_key() < min.get_key())
			min = x;
	}

	// Cut operation
	private void cut(fibheapnode x, fibheapnode y) {
		x.get_right().set_left(x.get_left());
		x.get_left().set_right(x.get_right());

		y.set_degree(y.get_degree() - 1);

		x.set_right(null);
		x.set_left(null);
		insert(x);
		x.set_parent(null);
		x.set_mark(false);
	}

	private void cascading_cut(fibheapnode y) {
		fibheapnode z = y.get_parent();
		if (z != null) {
			if (y.get_mark() == false)
				y.set_mark(true);
			else {
				cut(y, z);
				cascading_cut(z);
			}
		}
	}

	// Delete operations
	public void delete(fibheapnode x) {
		decrease_key(x, Integer.MIN_VALUE);
		int p = extract_min();
	}

	public static void main(String[] args) {
		fibHeap obj = create_heap();
		obj.insert(7);
		obj.insert(26);
		obj.insert(30);
		obj.insert(39);
		obj.insert(10);
		obj.display();

		System.out.println(obj.extract_min());
		obj.display();
		System.out.println(obj.extract_min());
		obj.display();
		System.out.println(obj.extract_min());
		obj.display();
		System.out.println(obj.extract_min());
		obj.display();
		System.out.println(obj.extract_min());
		obj.display();
	}
}