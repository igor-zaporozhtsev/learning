package algoexpert.leetcode.arrays_hashing;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
        char[][] arr = new char[9][9];
        arr[0][0] = '1'; arr[0][1] = '2';arr[0][2] = '.'; arr[0][3] = '.';arr[0][4] = '3'; arr[0][5] = '.';arr[0][6] = '.'; arr[0][7] = '.'; arr[0][8] = '.';
        arr[1][0] = '4'; arr[1][1] = '.';arr[1][2] = '.'; arr[1][3] = '5';arr[1][4] = '.'; arr[1][5] = '.';arr[1][6] = '.'; arr[1][7] = '.'; arr[1][8] = '.';
        arr[2][0] = '.'; arr[2][1] = '9';arr[2][2] = '8'; arr[2][3] = '.';arr[2][4] = '.'; arr[2][5] = '.';arr[2][6] = '.'; arr[2][7] = '.'; arr[2][8] = '3';

        arr[3][0] = '5'; arr[3][1] = '.';arr[3][2] = '.'; arr[3][3] = '.';arr[3][4] = '6'; arr[3][5] = '.';arr[3][6] = '.'; arr[3][7] = '.'; arr[3][8] = '4';
        arr[4][0] = '.'; arr[4][1] = '.';arr[4][2] = '.'; arr[4][3] = '8';arr[4][4] = '.'; arr[4][5] = '3';arr[4][6] = '.'; arr[4][7] = '.'; arr[4][8] = '5';
        arr[5][0] = '7'; arr[5][1] = '.';arr[5][2] = '.'; arr[5][3] = '.';arr[5][4] = '2'; arr[5][5] = '.';arr[5][6] = '.'; arr[5][7] = '.'; arr[5][8] = '6';

        arr[6][0] = '.'; arr[6][1] = '.';arr[6][2] = '.'; arr[6][3] = '.';arr[6][4] = '.'; arr[6][5] = '.';arr[6][6] = '2'; arr[6][7] = '.'; arr[6][8] = '.';
        arr[7][0] = '.'; arr[7][1] = '.';arr[7][2] = '.'; arr[7][3] = '4';arr[7][4] = '1'; arr[7][5] = '9';arr[7][6] = '.'; arr[7][7] = '.'; arr[7][8] = '8';
        arr[8][0] = '.'; arr[8][1] = '.';arr[8][2] = '.'; arr[8][3] = '.';arr[8][4] = '8'; arr[8][5] = '.';arr[8][6] = '.'; arr[8][7] = '7'; arr[8][8] = '9';

/*
        ["1","2",".",  ".","3",".",  ".",".","."]
        ["4",".",".",  "5",".",".",  ".",".","."]
        [".","9","8",  ".",".",".",  ".",".","3"]

        ["5",".",".",  ".","6",".",  ".",".","4"]
        [".",".",".",  "8",".","3",  ".",".","5"]
        ["7",".",".",  ".","2",".",  ".",".","6"]

        [".",".",".",  ".",".",".",  "2",".","."]
        [".",".",".",  "4","1","9",  ".",".","8"]
        [".",".",".",  ".","8",".",  ".","7","9"]
*/

        System.out.println(isValidSudoku(arr));
    }

    public static boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for(int i =0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];

                if (num != '.'){
                    if (!seen.add(num + "at row"  + i) ||
                    !seen.add(num + "at col"  + j) ||
                    !seen.add(num + "at box"  + i/3 + "-" + j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
