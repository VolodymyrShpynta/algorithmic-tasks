package com.vshpynta;

import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyQueue {

    public static String reverse(String str) {
        var chars = str.toCharArray();
        var result = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = chars[chars.length - 1 - i];
        }
        return String.valueOf(result);
    }

    public static String reverseRecursive(String str) {
        if (str.length() < 2) {
            return str;
        }

        return str.charAt(str.length() - 1) + reverseRecursive(str.substring(0, str.length() - 1));
    }

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

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            var minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }

    }

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

    public static int rob(int[] nums) {
        var robs = new HashMap<Integer, Integer>();
        return rob(nums, 0, robs);
    }

    private static int rob(int[] nums, int start, Map<Integer, Integer> robs) {
        System.out.println("Run rob for: " + start);
        if (nums == null || start >= nums.length) {
            return 0;
        }
        if (robs.get(start) != null) {
            System.out.println("Get rob for: " + start);
            return robs.get(start);
        }
        System.out.println("Calculate rob for: " + start);
        var rob = Math.max(
                nums[start] + rob(nums, start + 2, robs), //rob starting from the given index
                rob(nums, start + 1, robs) //rob starting from the adjacent index
        );
        robs.put(start, rob);
        return rob;
    }

    public static int rob1(int[] nums) {
        if (nums.length == 0) return 0;
        int prev1 = 0;
        int prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        var minPrice = prices[0];
        var maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }

        return maxProfit;
    }

    public static int climbStairs(int n) {
        var cachedResults = new HashMap<Integer, Integer>();
        return climbStairs(n, cachedResults);
    }

    public static int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        var prevPrev = 1;
        var prev = 2;
        for (int i = 3; i <= n; i++) {
            var temp = prev;
            prev = prev + prevPrev;
            prevPrev = temp;
        }
        return prev;
    }

    private static int climbStairs(int n, Map<Integer, Integer> cachedResults) {
        if (n <= 2) {
            return n;
        }
        if (cachedResults.get(n) != null) {
            return cachedResults.get(n);
        }
        var waysNumber = climbStairs(n - 1, cachedResults) + climbStairs(n - 2, cachedResults);
        cachedResults.put(n, waysNumber);
        return waysNumber;
    }

    public static List<String> fizzBuzz(int n) {
        var result = new LinkedList<String>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    public static int singleNumber(int[] nums) {
        var result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    public static void moveZeroes(int[] nums) {
        if (nums == null) {
            return;
        }
        var index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                if (i != index) {
                    nums[i] = 0;
                }
                index++;
            }
        }
    }

    public static int getSum(int a, int b) {
        while (b != 0) {
            var carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }

        return a;
    }

    public static int subtract(int a, int b) {
        var invertedB = getSum(~b, 1);
        return getSum(a, invertedB);
    }

    @ToString
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        var prev = (ListNode) null;
        var curr = head;
        var next = curr.next;
        while (next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }
        curr.next = prev;
        return curr;
    }

    public static int titleToNumber(String columnTitle) {
        if (columnTitle == null) {
            return 0;
        }

        var chars = columnTitle.toCharArray();
        var result = 0;
        var base = 'Z' - 'A' + 1;
        for (int i = 0; i < chars.length; i++) {
            result += (chars[i] - 'A' + 1) * Math.pow(base, chars.length - i - 1);
        }
        return result;
    }

    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        var candidate = nums[0];
        var appearances = 1;
        for (int i = 1; i < nums.length; i++) {
            if (candidate == nums[i]) {
                appearances++;
            } else {
                appearances--;
                if (appearances < 1) {
                    candidate = nums[i];
                    appearances = 1;
                }
            }
        }
        return candidate;
    }

    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }

        var next = node.next;
        node.val = next.val;
        node.next = next.next;
        next.next = null;
    }

    //Suppose the sequence of prices is "a <= b <= c <= d". The profit is "d - a = (b - a) + (c - b) + (d - c)".
    //In case when the sequence of prices is "a >= b <=c", the profit is "c - b = max(0, b - a) + (c - b)".
    public static int maxProfitFull(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        var maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
//        var array = new int[]{1, 2, 3};
        var array = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(array));
    }
}
