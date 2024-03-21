package com.vshpynta;

import java.util.HashMap;

//https://leetcode.com/problems/first-unique-character-in-a-string/
public class FirstUniqChar {

    public int firstUniqChar(String s) {
        if (s == null) {
            return -1;
        }
        var charsCount = new HashMap<Character, Integer>();
        var chars = s.toCharArray();
        for (char aChar : chars) {
            charsCount.compute(aChar, (ch, count) -> count == null ? 1 : ++count);
        }

        for (int i = 0; i < chars.length; i++) {
            if (charsCount.get(chars[i]) == 1) {
                return i;
            }
        }

        return -1;
    }

    //Constraints: consists of only lowercase English letters.
    public static int firstUniqCharWithArray(String s) {
        if (s == null) {
            return -1;
        }
        var charsCount = new int[26];
        var chars = s.toCharArray();
        for (char aChar : chars) {
            charsCount[aChar-'a']++;
        }

        for (int i = 0; i < chars.length; i++) {
            if (charsCount[chars[i]-'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqCharWithArray("aaaaa"));
    }
}
