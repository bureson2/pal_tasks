package org.example.printer;

import org.example.structures.heaps.BinaryMinHeap;

public class HeapPrinter {
    public static void printHeap(BinaryMinHeap heap) {
        printHeapRecursive(heap, 0, 0, "Root: ");
    }

    private static void printHeapRecursive(BinaryMinHeap heap, int index, int level, String prefix) {
        if (index < heap.getCurrentHeapSize()) {
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }

            System.out.println(prefix + heap.getHeapArray()[index]);

            printHeapRecursive(heap, heap.getLeftChild(index), level + 1, "L: ");
            printHeapRecursive(heap, heap.getRightChild(index), level + 1, "R: ");
        }
    }
}
