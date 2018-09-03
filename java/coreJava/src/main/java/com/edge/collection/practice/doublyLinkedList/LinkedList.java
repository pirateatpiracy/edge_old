package com.edge.collection.practice.doublyLinkedList;

import java.util.NoSuchElementException;


public class LinkedList<E>{
	private Node<E> head;
	private Node<E> tail;
	private int size;

	  public boolean add(E e) {
	        linkLast(e);
	        return true;
	    }
	  public E getFirst() {
		  if (head == null)
	            throw new NoSuchElementException();
		  return  head.item;
	  }
	  public E getLast() {
		  if (tail == null)
	            throw new NoSuchElementException();
		  return  tail.item;
	  }
	public void linkLast(E e) {
		Node<E> node = new Node<E>(e);
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

	public void linkFirst(E e) {
		Node<E> node = new Node<E>(e);
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
	   E unlink(Node<E> x) {
	         Node<E> prev = x.prev;
	         E e = x.item;
	         Node<E> next = x.next;

	        if (prev == null) {
	            head = next;
	        } else {
	            prev.next = next;
	            x.prev = null;
	        }

	        if (next == null) {
	            tail = prev;
	        } else {
	            next.prev = prev;
	            x.next = null;
	        }

	        x.item = null;
	        size--;
	        return e;
	    }

	public void unlinkFirst() {
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

	public void unlinkLast() {
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

	public void printRev() {
		Node<E> current = tail;
		while (current != null) {
			System.out.print(current);
			current = current.getPrev();
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
	Node<E> prev;
    E item;
    Node<E> next;
    Node(E element) {
        this.item = element; 
    }
	public Node<E> getPrev() {
		return prev;
	}
	public void setPrev(Node<E> prev) {
		this.prev = prev;
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
