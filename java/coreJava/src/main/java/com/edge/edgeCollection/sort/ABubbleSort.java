package com.edge.edgeCollection.sort;

public class ABubbleSort {
    static  int counter=0;
    public static void main(String[] args) {

      //  int[] intArray = {20, 35, -15, 7, 55, 1, -22};
        int[] intArray = {20,19,18,17,16,15,14,13,12,10,9,8,7,6,5,4,2,1};

        for (int lastUnsortedIndex = intArray.length - 1; lastUnsortedIndex > 0;
             lastUnsortedIndex--) {
            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (intArray[i] > intArray[i + 1]) {
                    swap(intArray, i, i + 1);
                }counter++;
            }
        }

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }

System.out.println(counter);
    }

    public static void swap(int[] array, int i, int j) {

        if (i == j) {
            return;
        }

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }
}
