package Stratego;

import java.util.Scanner;

public class Temp {
    public static void main(String[] args) {
        int[][] board = new int[5][5];
        board[0][1] = 1;
        for(int i = 0 ; i < board.length;i++){
            for (int j = 0 ; j < board[i].length;j++){
                System.out.print(board[i][j]+"-");
            }
            System.out.println();
        }
    }

}
