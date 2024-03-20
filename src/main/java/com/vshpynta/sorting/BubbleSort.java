package com.vshpynta.sorting;

public class BubbleSort {

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            var swapPerformed = false;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    swap(array, j - 1, j);
                    swapPerformed = true;
                }
            }
            if (!swapPerformed) {
                return;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
