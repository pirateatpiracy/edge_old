package com.edge.collection.practice.doublyLinkedList;

public class EmployeeDoublyLinkedList {
	private EmployeeNode head;
	private EmployeeNode tail;
	private int size;

	public void add(Employee employee) {
		EmployeeNode node = new EmployeeNode(employee);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.setNext(node);
			node.setPrev(tail);
			tail = node;
		}
		size++;
	}

	public void addAtFront(Employee employee) {
		EmployeeNode node = new EmployeeNode(employee);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.setNext(head);
			head.setPrev(node);
			head = node;
		}
		size++;
	}

	public void removeFromFront() {
		if (head == null) {
			System.out.println("Empty List");
		} else if (head.getNext() == null) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
			head.setPrev(null);
			size--;
		}

	}

	public void removeFromEnd() {
		if (head == null) {
			System.out.println("Empty List");
		} else if (tail.getPrev() == null) {
			head = null;
			tail = null;
		} else {
			tail = tail.getPrev();
			tail.setNext(null);
			size--;
		}

	}

	public int size() {
		return size;
	}

	public void print() {
		EmployeeNode current = head;
		while (current != null) {
			System.out.print(current);
			current = current.getNext();
			if (current != null)
				System.out.print(", ");
		}
	}

	public void printRev() {
		EmployeeNode current = tail;
		while (current != null) {
			System.out.print(current);
			current = current.getPrev();
			if (current != null)
				System.out.print(", ");

		}
	}
}
