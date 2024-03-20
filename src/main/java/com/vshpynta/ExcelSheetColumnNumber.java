package com.vshpynta;

//https://leetcode.com/problems/excel-sheet-column-number/
public class ExcelSheetColumnNumber {

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
}
