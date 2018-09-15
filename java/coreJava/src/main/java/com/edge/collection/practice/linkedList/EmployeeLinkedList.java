package com.edge.collection.practice.linkedList;

public class EmployeeLinkedList {
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
			head = node;
		}
		size++;
	}

	/*
	 * public void removeFromEnd() { if (head == null) {
	 * System.out.println("Empty List"); } else { tail.setEmployee( new Employee());
	 * }size--; }
	 */
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
		EmployeeNode current = head;
		while (current != null) {
			System.out.print(current);
			current = current.getNext();
			if (current != null)
				System.out.print(", ");
		}
	}
}
