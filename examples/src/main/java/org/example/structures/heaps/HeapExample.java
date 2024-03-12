package org.example.structures.heaps;

import org.example.printer.HeapPrinter;

public class HeapExample {
    public static void main(String[] args) {

        // EXAMPLE 1 - BINARY MIN HEAP
        BinaryMinHeap minHeap = prepareBinaryMinHeap();
        HeapPrinter.printHeap(minHeap);

        // EXAMPLE 2 - BINOMIAL MIN HEAP
        // TODO in future

        // EXAMPLE 3 - FIBONACCI MIN HEAP
        // TODO in future

    }

    private static BinaryMinHeap prepareBinaryMinHeap(){
        BinaryMinHeap minHeap = new BinaryMinHeap(11);
        minHeap.insert(3);
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.insert(15);
        minHeap.insert(5);
        minHeap.insert(4);
        minHeap.insert(45);
        return minHeap;
    }
}
