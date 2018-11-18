package com.edge.edgeCollection.heap.maxHeap;

public class Main {
    public static  void main(String [] a){
        Heap heap= new Heap(10);
        heap.insert(10);
        heap.insert(15);
        heap.insert(5);
        heap.insert(76);
        heap.insert(34);
        heap.insert(98);
        heap.insert(38);
        heap.insert(56);
        heap.print();
        heap.delete(2);
        heap.print();
        heap.sort();
        heap.print();
    }

}
