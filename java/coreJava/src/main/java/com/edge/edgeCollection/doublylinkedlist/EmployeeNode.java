package com.edge.edgeCollection.doublylinkedlist;

public class EmployeeNode {
	private EmployeeNode prev;
	private Employee employee;
	private EmployeeNode next;
	public EmployeeNode(Employee employee) {
		super();
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public EmployeeNode getPrev() {
		return prev;
	}
	public void setPrev(EmployeeNode prev) {
		this.prev = prev;
	}
	public EmployeeNode getNext() {
		return next;
	}
	public void setNext(EmployeeNode next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return employee.toString();
	}

	
}


/*    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }*/