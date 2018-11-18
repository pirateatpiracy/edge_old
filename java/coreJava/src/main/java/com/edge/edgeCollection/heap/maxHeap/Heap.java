package com.edge.edgeCollection.heap.maxHeap;

public class Heap {
    private int[] heap;
    private int size;

    public Heap(int capacity) {
        heap = new int[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public int getParent(int index) {
        return (index - 1) / 2;
    }

    public int getChildIndex(int index, boolean left) {
        return 2 * index + (left ? 1 : 2);
    }

    public void insert(int value) {
        if (isFull()) throw new IndexOutOfBoundsException("Heap is already full");
        heap[size] = value;
        fixHeapAbove(size);
        size++;
    }

    public int delete(int index) {
        if (isEmpty()) throw new IndexOutOfBoundsException("Heap is  empty");
        int parent = getParent(index);
        int value = heap[index];

        heap[index] = heap[size - 1];

        if (index == 0 || heap[index] < heap[parent])
            fixHeapBelow(index, size - 1);
        else {
            fixHeapAbove(index);
        }
        size--;
        return value;
    }

    private void fixHeapAbove(int index) {
        int value = heap[index];
        while (index > 0 && value > heap[getParent(index)]) {
            heap[index] = heap[getParent(index)];
            index = getParent(index);
        }
        heap[index] = value;
    }

    private void fixHeapBelow(int index, int lastHeapIndex) {
        int value;
        while (index <= lastHeapIndex) {
            int left = getChildIndex(index, true);
            int right = getChildIndex(index, false);
            if (left <= lastHeapIndex) {
                if (right > lastHeapIndex)
                    value = left;
                else {
                    value = (heap[left] > heap[right] ? left : right);
                }
                if (heap[index] < heap[value]) {
                    int tmp = heap[index];
                    heap[index] = heap[value];
                    heap[value] = tmp;
                } else {
                    break;
                }
                index = value;
            } else {
                break;
            }
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) System.out.print(heap[i]+",");
        System.out.println();
    }
}
