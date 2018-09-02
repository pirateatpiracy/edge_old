package com.edge.collection.practice.stack;

public class Stack <E> {
	 protected Object[] e;
	    protected int count;
	    protected int capacityIncrement;
	    protected transient int modCount = 0;
	public Stack() {}
}
