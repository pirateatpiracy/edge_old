package com.edge.collection.practice.linkedList;



public class LinkedList <E>{
	private Node<E> head;
	private Node<E> tail;
	private int size;

	public void add(E e) {
		Node<E> node = new Node<>(e);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.setNext(node);
			tail = node;
		}
		size++;
	}

	public void addAtFront(E e) {
		Node<E>  node = new Node<>(e);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.setNext(head);
			head = node;
		}
		size++;
	}

	public void removeFromFront() {
		if (head == null) {
			System.out.println("Empty List");
		} else {
			head = head.getNext();
		}
		size--;
	}

	public int size() {
		return size;
	}

	public void print() {
		Node<E>  current = head;
		while (current != null) {
			System.out.print(current);
			current = current.getNext();
			if (current != null)
				System.out.print(", ");
		}
	}

	@Override
	public String toString() {
		String result="[";

		Node<E>  current = head;
		while (current != null) {
			result+= current;
			current = current.getNext();
			if (current != null)
				result+= ", ";
		}
		return result+"]";
	}
}
class Node<E> {
    E item;
    Node<E> next;
    Node(E element) {
        this.item = element; 
    }
	public Node<E> getNext() {
		return next;
	}
	public void setNext(Node<E> next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return item.toString();
	}
}