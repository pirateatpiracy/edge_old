/**
 * 
 */
package com.edge.collection.stack;

import java.util.ArrayList;
import java.util.List;
/**
 * @author edge
 *
 */
import java.util.Stack;

public class StackBasicExample {
    public static void main(String a[]){
    	Stack<Integer> stack1 = new Stack<>();
        System.out.println("Empty stack1 : "  + stack1);
        System.out.println("Empty stack1 : "  + stack1.isEmpty());
        // Exception in thread "main" java.util.Emptystack1Exception
        // System.out.println("Empty stack1 : Pop Operation : "  + stack1.pop());
        stack1.push(1001);
        stack1.push(1002);
        stack1.push(1003);
        stack1.push(1004);
        System.out.println("Non-Empty stack1 : "  + stack1);
        System.out.println("Non-Empty stack1: Pop Operation : "  + stack1.pop());
        System.out.println("Non-Empty stack1 : After Pop Operation : "  + stack1);
        System.out.println("Non-Empty stack1 : search() Operation : "  + stack1.search(1002));
        System.out.println("Non-Empty stack1 : "  + stack1.isEmpty());
        
        Integer[] intArr = { 1001,1002,1003,1004};
        Stack<Integer> stack2 = new Stack<>();
        for(Integer i : intArr)   	stack2.push(i);
        System.out.println("\nNon-Empty stack2 : "  + stack2);
        
        Stack<Integer> stack3 = new Stack<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("\nNon-Empty stack3 addAll Operation : "  + stack3.addAll(list));
        System.out.println("Non-Empty stack3 : "  + stack3);
        
        
        Stack<Integer> stack4 = new Stack<>();
        stack4.push(1);
        stack4.push(2);
        stack4.push(3);
        List<Integer> list2 = new ArrayList<>();
        list2.addAll(stack4);
        System.out.println("Non-Empty stack4 : "  + stack4);
        System.out.println("Non-Empty List : "  + list2);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
}