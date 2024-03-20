package com.vshpynta.sorting;

import java.util.Arrays;

public class MergeSort {

    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        var leftSubArray = Arrays.copyOfRange(array, 0, array.length / 2);
        var rightSubArray = Arrays.copyOfRange(array, array.length / 2, array.length);
        return merge(mergeSort(leftSubArray), mergeSort(rightSubArray));
    }

    private static int[] merge(int[] arrayOne, int[] arrayTwo) {
        var indexOne = 0;
        var indexTwo = 0;
        var resIndex = 0;
        var result = new int[arrayOne.length + arrayTwo.length];
        while (indexOne < arrayOne.length && indexTwo < arrayTwo.length) {
            if (arrayOne[indexOne] < arrayTwo[indexTwo]) {
                result[resIndex] = arrayOne[indexOne];
                indexOne++;
            } else {
                result[resIndex] = arrayTwo[indexTwo];
                indexTwo++;
            }
            resIndex++;
        }

        while (indexOne < arrayOne.length) {
            result[resIndex] = arrayOne[indexOne];
            indexOne++;
            resIndex++;
        }

        while (indexTwo < arrayTwo.length) {
            result[resIndex] = arrayTwo[indexTwo];
            indexTwo++;
            resIndex++;
        }

        return result;
    }
}
