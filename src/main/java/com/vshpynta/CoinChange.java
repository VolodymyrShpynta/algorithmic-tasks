package com.vshpynta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/coin-change/description/
public class CoinChange {

    /**
     * The 'bottom-up' approach solution
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChangeBottomUp(int[] coins, int amount) {
        var memoized = new int[amount + 1]; //Min coins amount for every particular sum
        Arrays.fill(memoized, amount + 1); //fill by values that are bigger that possible number of coins
        memoized[0] = 0; //baseline (bottom): to get 0 we need 0 coins
        for (int i = 1; i < memoized.length; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    memoized[i] = Math.min(memoized[i], memoized[i - coin] + 1);
                }
            }
        }
        return memoized[amount] > amount ? -1 : memoized[amount];
    }

    /**
     * This is a 'Dynamic programing' solution. It's very similar to the recursive solution except,
     * but it uses memoization to store already calculated results
     */
    //{1,5,10} 26
    public static int coinChangeDP(int[] coins, int amount) {
        return coinChangeDP(coins, amount, new HashMap<>());
    }

    private static int coinChangeDP(int[] coins, int amount, Map<Integer, Integer> calculatedSums) {
        var alreadyCalculated = calculatedSums.get(amount);
        if (alreadyCalculated != null) {
            return alreadyCalculated;
        }
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        var min = -1;
        for (int coin : coins) {
            min = min_skip_negative(min,
                    incrementIfPositive(coinChangeDP(coins, amount - coin, calculatedSums), 1));
        }
        calculatedSums.put(amount, min);
        return min;
    }

    /**
     * It is a regular 'brute force' recursive solution
     *
     * @param coins
     * @param amount
     * @return
     */
    //{1,5,10} 26
    public static int coinChangeRecursive(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        var min = -1;
        for (int coin : coins) {
            min = min_skip_negative(min,
                    incrementIfPositive(coinChangeRecursive(coins, amount - coin), 1));
        }
        return min;
    }

    private static int incrementIfPositive(int num, int delta) {
        if (num < 0) {
            return num;
        }
        return num + delta;
    }

    private static int min_skip_negative(int a, int b) {
        if (a == -1) {
            return b;
        }
        if (b == -1) {
            return a;
        }
        return Math.min(a, b);
    }

    public static void main(String[] args) {
        System.out.println(coinChangeDP(new int[]{2, 5, 10, 1}, 27));
        System.out.println(coinChangeBottomUp(new int[]{2, 5, 10, 1}, 27));
    }
}
