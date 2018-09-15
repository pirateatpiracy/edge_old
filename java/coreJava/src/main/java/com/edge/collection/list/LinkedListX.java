package com.edge.collection.list;

import java.util.LinkedList;
import java.util.Stack;

public class LinkedListX {
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main (String [] args){

        LinkedList linkedList=new LinkedList();
        linkedList.add("first");
        linkedList.add("second");
        linkedList.add("third");
        linkedList.add("forth");
        linkedList.add("fifth");

        Stack stack=new Stack();
        stack.add("1");stack.add("2");stack.add("3");stack.add("4");stack.add("5");


        System.out.println("Printing normally"+linkedList);

        linkedList.addFirst("sixth");

        System.out.println("Printing after adding at first"+linkedList);
        linkedList.addLast("seventh");

        System.out.println("Printing after adding at last"+linkedList);

        System.out.println("get first = "+linkedList.getFirst());
        System.out.println("get last = "+linkedList.getLast());
        linkedList.remove();
        System.out.println("Printing after remove"+linkedList);//removes from start
        linkedList.remove(3);
        System.out.println("Printing after remove at index 3"+linkedList);//index is zero based, this process is not constant time but 0n
        linkedList.removeFirst();
        System.out.println("Printing after removeFirst"+linkedList);//same as remove
        linkedList.removeLast();
        System.out.println("Printing after removeLast"+linkedList);

        LinkedList linkedListFromStack=new LinkedList();
        linkedListFromStack.addAll(stack);
        System.out.println("list created from stack"+linkedListFromStack);


    }

}
