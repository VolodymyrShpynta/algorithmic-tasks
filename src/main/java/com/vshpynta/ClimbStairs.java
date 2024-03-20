package com.vshpynta;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/climbing-stairs/description/
public class ClimbStairs {

    public static int climbStairsIterative(int n) {
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

    public static int climbStairsDP(int n) {
        var cachedResults = new HashMap<Integer, Integer>();
        return climbStairs(n, cachedResults);
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
}
