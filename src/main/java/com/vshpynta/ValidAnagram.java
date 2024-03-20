package com.vshpynta;

import java.util.HashMap;

public class ValidAnagram {

    //https://leetcode.com/problems/valid-anagram/
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        var charsCounts = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            charsCounts.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }

        for (char c : t.toCharArray()) {
            var count = charsCounts.get(c);
            if (count == null || count == 0) {
                return false;
            }
            charsCounts.put(c, count - 1);
        }

        return true;
    }
}
