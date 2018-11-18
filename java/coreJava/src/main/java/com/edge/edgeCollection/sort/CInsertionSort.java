package com.edge.edgeCollection.sort;

public class CInsertionSort {
    static int counter = 0;

    public static void main(String[] args) {


        //  int[] intArray = {20, 35, -15, 7, 55, 1, -22};
        int[] intArray = {20, 19, 18, 17, 16, 15, 14, 13, 12, 10, 9, 8, 7, 6, 5, 4, 2, 1};

        for (int i = 1; i < intArray.length; i++) {
            int temp = intArray[i];

            int j;

            for (j = i; j > 0 && intArray[j - 1] > temp; j--) {
                intArray[j] = intArray[j - 1];
                counter++;
            }

            intArray[i] = temp;
        }

        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
        System.out.println(counter);
    }
}
