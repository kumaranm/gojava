package com.mk.programiz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Singleton implements Serializable, Cloneable {

	private static Singleton instance = new Singleton();

	private Singleton() {

	}

	public static Singleton getInstance() {

		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					return new Singleton();
				}
			}
		}
		return instance;
	}

	protected Object readResolve() {
		return getInstance();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
//		throw new CloneNotSupportedException();
		return getInstance();
	}
}

public class SingletonTest {
	
	public static void main(String[] args) throws Exception {

		Singleton s = Singleton.getInstance();
		String file = "singletonfile.txt";
		System.out.println(s.hashCode());
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(s);
		oos.close();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Singleton t = (Singleton)ois.readObject();
		ois.close();
		System.out.println(t.hashCode());

		Singleton y = (Singleton)s.clone();
		System.out.println(y.hashCode());
	}
}