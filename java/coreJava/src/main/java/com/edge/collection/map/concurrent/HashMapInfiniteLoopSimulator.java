package com.edge.collection.map.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * HashMapInfiniteLoopSimulator
 * @author edge
 *
 */
public class HashMapInfiniteLoopSimulator {
       private static final int NB_THREADS = 3;
       private static final int NB_TEST_ITERATIONS = 5;
       private static Map<String, Integer> assignedMapForTest = null;
       private static Map<String, Integer> nonThreadSafeMap = null;
       private static Map<String, Integer> threadSafeMap1 = null;
       private static Map<String, Integer> threadSafeMap2 = null;
       private static Map<String, Integer> threadSafeMap3 = null;
       /**
        * Main program
        * @param args
        */
       public static void main(String[] args) {
    	   
             nonThreadSafeMap = new HashMap<String, Integer>(2);// Plain old HashMap (since JDK 1.2)                   
             threadSafeMap1 = new Hashtable<String, Integer>(2); // Plain old Hashtable (since JDK 1.0)           
             threadSafeMap2 = Collections.synchronizedMap( new HashMap<String, Integer>(2));  // Fully synchronized HashMap                   
             threadSafeMap3 = new ConcurrentHashMap<String, Integer>(2); // ConcurrentHashMap (since JDK 1.5)
             List<Map<String, Integer>> maps=new ArrayList<Map<String, Integer>>();
             maps.add(nonThreadSafeMap);
             maps.add(threadSafeMap1);
             maps.add(threadSafeMap2);
             maps.add(threadSafeMap3);
             for (Map<String, Integer> map:maps) {
            	 long timeBefore = System.currentTimeMillis();
                 long timeAfter = 0;
                 Float totalProcessingTime = null;
             
             for (int i=0; i<NB_TEST_ITERATIONS; i++) {     
                    assignedMapForTest = map;                   
                    ExecutorService executor = Executors.newFixedThreadPool(NB_THREADS);
                    for (int j = 0; j < NB_THREADS; j++) {
                           Runnable worker = new WorkerThread(assignedMapForTest);
                           executor.execute(worker);              
                    }                   
                    executor.shutdown();
                    while (!executor.isTerminated()) {
                    }
                    
             }
             timeAfter = System.currentTimeMillis();
             totalProcessingTime = new Float( (float) (timeAfter - timeBefore) );
             System.out.println((float)NB_THREADS*NB_TEST_ITERATIONS*500000/1000000 +" million threads completed for \""+assignedMapForTest.getClass()+"\" in "+(float)totalProcessingTime/1000+" seconds");
             }
       }
}

class WorkerThread implements Runnable {
    private Map<String, Integer> map = null;
    public WorkerThread(Map<String, Integer> assignedMap) {
          this.map = assignedMap;
    }
    @Override
    public void run() {
          for (int i=0; i<500000; i++) {
                 // Return 2 integers between 1-1000000 inclusive
                 Integer newInteger1 = (int) Math.ceil(Math.random() * 1000000);
                 Integer newInteger2 = (int) Math.ceil(Math.random() * 1000000);                    
                 // 1. Attempt to retrieve a random Integer element
                 Integer retrievedInteger = map.get(String.valueOf(newInteger1));
                 // 2. Attempt to insert a random Integer element
                 map.put(String.valueOf(newInteger2), newInteger2);                
          }
    }
}