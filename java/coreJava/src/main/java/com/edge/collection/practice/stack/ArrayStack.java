package com.edge.collection.practice.stack;

public class ArrayStack <E>{
	private int top;
	private E[] stack;
	public ArrayStack(int capacity) {
		stack= (E[]) new Object [capacity];
	}
	public void push(E e) {
		if (top==stack.length) {
		E[] newArray= (E[])  new Object[stack.length*2];
		System.arraycopy(stack, 0, newArray, 0, stack.length);
		stack=newArray;
		}stack[top++]=e;
	}
	public E pop() {
		if(top!=0) {
			E e=stack[--top];
			stack[top]=null;
			return e;
		}
		return null;
	}
	public boolean isEmpty() {return top==0;}

}
