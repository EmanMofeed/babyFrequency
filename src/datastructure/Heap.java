package datastructure;

import java.lang.reflect.Array;
import java.util.Arrays;
public class Heap<T extends Comparable<T>> {
    private T[] HeapTable;
    private int size;


    public T[] getHeapTable() {
        return HeapTable;
    }

    private static final int ROOT = 1;

    public Heap(int MaxSize,  Class clazz) {
        this.size = 0;
        HeapTable = (T[]) Array.newInstance(clazz, MaxSize + 1);
    }

    public void insert(T element) {
        HeapTable[++size] = element;
        swim();
    }

    private void swim() {
        int curr = size;
        while (curr > 1 && less(curr / 2, curr)) {
            swap(curr, curr / 2);
            curr = curr / 2;
        }
    }

    private void swap(int curr, int parent) {
        T temp;
        temp = HeapTable[curr];
        HeapTable[curr] = HeapTable[parent];
        HeapTable[parent] = temp;
    }

    private boolean less(int i, int j) {
        // (-) <0 the parent is smaller
        // (+) >0 or =0 the parent is larger
        return HeapTable[i].compareTo(HeapTable[j]) < 0;
    }

    public T remove() {
        T removedElement = HeapTable[ROOT];
        HeapTable[ROOT] = HeapTable[size--];
        sink(ROOT,size);
        HeapTable[size + 1] = null;
        return removedElement;
    }

    public void sort() {
        int n = size;
        while (n > 1) {
            swap(ROOT, n);
            n--;
            sink(ROOT,n);

        }
    }

    private void sink(int pos,int lastPos) {
        // left pos*2 =j
        // right pos*2+1 =j+1
        if (pos * 2 <= lastPos && (pos * 2) + 1 <= lastPos) // is not Leaf
            if (less(pos, pos * 2) || less(pos, (pos * 2) + 1)) {
                if (less(pos * 2, (pos * 2) + 1)) {
                    swap(pos, (pos * 2) + 1);
                    sink((pos * 2) + 1,lastPos);
                } else {
                    swap(pos, pos * 2);
                    sink(pos * 2,lastPos);
                }
            }
    }

    @Override
    public String toString() {
        return "HeapTable=" + Arrays.toString(HeapTable);
    }
}