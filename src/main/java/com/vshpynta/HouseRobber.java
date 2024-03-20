package com.vshpynta;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber {

    //https://leetcode.com/problems/house-robber/
    public static int robIterative(int[] nums) {
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

    public int robDP(int[] nums) {
        var robs = new HashMap<Integer, Integer>();
        return rob(nums, 0, robs);
    }

    private int rob(int[] nums, int start, Map<Integer, Integer> robs) {
        if (nums == null || start >= nums.length) {
            return 0;
        }
        if (robs.get(start) != null) {
            return robs.get(start);
        }
        var rob = Math.max(
                nums[start] + rob(nums, start + 2, robs), //rob starting from the given index
                rob(nums, start + 1, robs) //rob starting from the adjacent index
        );
        robs.put(start, rob);
        return rob;
    }
}
