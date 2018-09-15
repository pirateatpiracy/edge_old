package com.edge.collection.iterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Iteratorr {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)
    {

        //list elements using Iterator
        List list = new ArrayList(); 
        list.add("ONE"); 
        list.add("TWO"); 
        list.add("THREE"); 
        Iterator iterator1 = list.iterator(); 
        while (iterator1.hasNext())
            System.out.println(iterator1.next());
        
      /*available methods in  Iterator       
        hasNext()
        next()
        remove()
       */
        
      //Traversing queue elements using Iterator
        Queue queue = new PriorityQueue(list);
        Iterator iterator2 = queue.iterator(); 
        while (iterator2.hasNext())
        	System.out.println(iterator2.next());
        
      //Traversing set elements using Iterator
        Set set = new HashSet(list);
        Iterator iterator3 = set.iterator();
        while (iterator3.hasNext())
            System.out.println(iterator3.next());
        
        //Traversing list elements using ListIterator
        ListIterator listIterator1 = list.listIterator(); 
        while (listIterator1.hasNext())
            System.out.println(listIterator1.next());

    /*  available methods in  ListIterator
        add(E e)
        hasNext()
        hasPrevious()
        next()
        nextIndex()
        previous()
        previousIndex()
        remove()
        set(E e)
 */
        //Traversing queue and set elements using ListIterator is not possible 
        //ListIterator listIterator2 = queue.listIterator();    //Compile time error, there is no such method in Queue 
        //ListIterator listIterator3 = set.listIterator();     //Compile time error, there is no such method in Set
    }
}
