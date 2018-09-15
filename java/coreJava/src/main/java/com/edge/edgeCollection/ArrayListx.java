package com.edge.edgeCollection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@SuppressWarnings("rawtypes")
public class ArrayListx  implements List,RandomAccess,Cloneable,java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    transient Object[] elementData; 
    private int size;
	public int size() {	return 0;}
	public boolean isEmpty() {return false;}
	public boolean contains(Object o) {return false;}
	public Iterator iterator() {return null;}
	public Object[] toArray() {return null;}
	public Object[] toArray(Object[] a) {return null;}
	public boolean add(Object e) {return false;	}
	public boolean remove(Object o) {return false;}
	public boolean containsAll(Collection c) {return false;}
	public boolean addAll(Collection c) {return false;}
	public boolean addAll(int index, Collection c) {return false;}
	public boolean removeAll(Collection c) {return false;}
	public boolean retainAll(Collection c) {return false;}
	public void clear() {}
	public Object get(int index) {return null;}
	public Object set(int index, Object element) {return null;}
	public void add(int index, Object element) {}
	public Object remove(int index) {return null;}
	public int indexOf(Object o) {return 0;	}public int lastIndexOf(Object o) {return 0;}
	public ListIterator listIterator() {return null;}
	public ListIterator listIterator(int index) {return null;}
	public List subList(int fromIndex, int toIndex) {return null;}

}
