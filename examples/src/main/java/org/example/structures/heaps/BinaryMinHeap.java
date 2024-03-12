package org.example.structures.heaps;

import lombok.Getter;

@Getter
public class BinaryMinHeap {
    private int[] heapArray;
    // max size of the heap
    private int capacity;
    // Current number of elements in the heap
    private int currentHeapSize;

    public BinaryMinHeap(int capacity) {
        this.capacity = capacity;
        this.heapArray = new int[capacity];
        this.currentHeapSize = 0;
    }

    public boolean insert(int number){
        if (currentHeapSize == capacity) return false;

        // First insert the new key at the end
        int i = currentHeapSize;
        heapArray[i] = number;
        currentHeapSize++;

        // Fix the min heap property if it is violated
        // i == 0 if i is root (at the begining or after swap
        while (i != 0 && heapArray[i] < heapArray[getParent(i)]) {
            swap(i, getParent(i));
            i = getParent(i);
        }

        return true;
    }

    // Returns the minimum key (key at root) from min heap
    public int getMin() {
        return heapArray[0];
    }

    // Changes value on a key
    public void changeValueOnAIndex(int index, int newValue) {
        if (heapArray[index] == newValue) {
            return;
        }
        if (heapArray[index] < newValue) {
            increaseKey(index, newValue);
        } else {
            decreaseKey(index, newValue);
        }
    }

    // This function deletes key at the given index.
    // It first reduced value to minus infinite, then calls extractMin()
    public void delete(int index) {
        decreaseKey(index, Integer.MIN_VALUE);
        extractMin();
    }

    // Method to remove minimum element (or root) from min heap
    public int extractMin() {
        if (currentHeapSize <= 0) {
            return Integer.MAX_VALUE;
        }

        if (currentHeapSize == 1) {
            currentHeapSize--;
            return heapArray[0];
        }

        // Store the minimum value and remove it from heap
        int root = heapArray[0];

        heapArray[0] = heapArray[currentHeapSize - 1];
        currentHeapSize--;
        MinHeapify(0);

        return root;
    }

    // Increases value of given key to newValue
    // It is assumed that newValue is greater than heapArray[key].
    // Heapify from the given key
    private void increaseKey(int index, int newValue) {
        heapArray[index] = newValue;
        MinHeapify(index);
    }

    private void decreaseKey(int index, int newValue) {
        heapArray[index] = newValue;

        while (index != 0 && heapArray[index] < heapArray[getParent(index)]) {
            swap(index, getParent(index));
            index = getParent(index);
        }
    }

    // A recursive method to heapify a subtree with the root at given index
    // This method assumes that the subtrees are already heapified
    private void MinHeapify(int index) {
        int l = getLeftChild(index);
        int r = getRightChild(index);

        int smallest = index;
        if (l < currentHeapSize && heapArray[l] < heapArray[smallest]) {
            smallest = l;
        }
        if (r < currentHeapSize && heapArray[r] < heapArray[smallest]) {
            smallest = r;
        }

        if (smallest != index) {
            swap(index, smallest);
            MinHeapify(smallest);
        }
    }

    private void swap(int i, int j){
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    private int getParent(int index){
        return (index - 1) / 2;
    }

    public int getLeftChild(int index){
        return 2 * index + 1;
    }

    public int getRightChild(int index){
        return 2 * index + 2;
    }
}
