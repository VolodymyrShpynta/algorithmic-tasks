package com.vshpynta;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
public class MaxProfitFull {
    //Suppose the sequence of prices is "a <= b <= c <= d". The profit is "d - a = (b - a) + (c - b) + (d - c)".
    //In case when the sequence of prices is "a >= b <=c", the profit is "c - b = max(0, b - a) + (c - b)".
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        var maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxProfit;
    }
}
