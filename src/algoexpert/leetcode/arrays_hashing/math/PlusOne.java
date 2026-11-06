package algoexpert.leetcode.arrays_hashing.math;

import java.util.Arrays;
import java.util.List;

public class PlusOne {

    public static int[] plusOne(int[] digits) {
        int arrSize = 0;
        var arr = new int[arrSize];
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] == 9){
                arrSize++;
                arr[i] = 1;
            }
        }






        return digits;
    }

    public static void main(String[] args) {
        var integerMap = List.of(
                   //new int[]{1,2,3}  // [1,2,4]
                   new int[]{9}  // [1, 0]
                   //new int[]{1,2,9} //[ 1,2,1,9 ]
        );

        integerMap.forEach( elem -> System.out.println
                ("Input: "+ Arrays.toString(elem) +" Output index is " + Arrays.toString(plusOne(elem))));


    }
}
