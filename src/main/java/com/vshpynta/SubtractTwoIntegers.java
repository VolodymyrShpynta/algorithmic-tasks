package com.vshpynta;

import static com.vshpynta.SumTwoIntegers.getSum;

public class SubtractTwoIntegers {

    public static int subtract(int a, int b) {
        var invertedB = getSum(~b, 1);
        return getSum(a, invertedB);
    }
}
