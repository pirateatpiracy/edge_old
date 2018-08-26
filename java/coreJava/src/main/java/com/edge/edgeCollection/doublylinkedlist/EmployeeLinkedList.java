package com.edge.edgeCollection.doublylinkedlist;



public class EmployeeLinkedList {
	private EmployeeNode head;
	private EmployeeNode tail;

	public void add(Employee employee) {
		EmployeeNode node = new EmployeeNode(employee);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.setNext(node);
			tail = node;
		}
	}

	public void print() {
		EmployeeNode current = head;
		while (current != null) {
			System.out.print(current);
			current = current.getNext();
		}
	}
}
