package com.vshpynta.sorting;

public class QuickSort {

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length);
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end || end - start < 2) {
            return;
        }
        var partitionIndex = partition(array, start, end);

        quickSort(array, start, partitionIndex);
        quickSort(array, partitionIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        var pivot = start;
        for (int i = pivot + 1; i < end; i++) {
            if (array[i] < array[pivot]) {
                if (i == pivot + 1) {
                    swap(array, pivot, i);
                } else {
                    swap(array, pivot, pivot + 1);
                    swap(array, pivot, i);
                }
                pivot++;
            }
        }
        return pivot;
    }

    private static void swap(int[] array, int i, int j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
