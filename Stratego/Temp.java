package Stratego;

import java.util.Scanner;

public class Temp {
    public static void main(String[] args) {
        int[] position = new int[2];
        Scanner keyboard = new Scanner(System.in);
        String a = "Major,2,3-l";
        System.out.println(a.charAt(a.length()-1));
    }
    public static String findPieceName(String move){
        int endingIndex = 0;
        for(int i = 0 ; i < move.length();i++){
            if(move.charAt(i) == ','){
                endingIndex = (i-1);
                break;
            }
        }
        return move.substring(0,endingIndex+1);
    }
    public static void findPosition(String move,int[] position){
        for(int i = 0 ; i < move.length();i++){
            if(move.charAt(i) == ','){
                position[0] = move.charAt(i+1)-48;
                position[1] = move.charAt(i+3)-48;
                break;
            }
        }
    }

}
