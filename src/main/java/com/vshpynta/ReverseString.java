package com.vshpynta;

public class ReverseString {

    //https://leetcode.com/problems/reverse-string/
    public void reverseString(char[] s) {
        if (s == null) {
            return;
        }
        for (int i = 0; i < s.length / 2; i++) {
            var temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
    }

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
}
