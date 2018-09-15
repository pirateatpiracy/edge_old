package com.edge.collection.practice.HashTable;

import java.util.Objects;

public class HashTable <X,Y>{
	private int defaultCapacity=10;
	private Edge [] e;
	public HashTable() {
		e=new Edge[defaultCapacity];
	}

}


class Edge<K,V>{
	int hash;
	K key;
	V value;
	Edge<K,V> next;
	
   Edge(int hash, K key, V value, Edge<K,V> next) {
        this.hash = hash;
        this.key =  key;
        this.value = value;
        this.next = next;
    }

   protected Object clone() {
       return new Edge<>(hash, key, value,(next==null ? null : (Edge<K,V>) next.clone()));
   }

public K getKey() {
	return key;
}

public V getValue() {
	return value;
}

public void setValue(V value) {
	this.value = value;
}
public int hashCode() {
    return hash ^ Objects.hashCode(value);
}

public String toString() {
    return key.toString()+"="+value.toString();
}
}
