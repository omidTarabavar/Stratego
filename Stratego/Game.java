package Stratego;

import java.util.Scanner;

public class Game {
    public static Piece[][] board = new Piece[10][10];
    public Piece addAPiece(Player player){
        Piece piece = null;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Choose your piece:");
        System.out.println("\n1-Spy");
        System.out.println("2-Scout");
        System.out.println("3-Miners");
        System.out.println("4-Sergeant");
        System.out.println("5-Lieutenant");
        System.out.println("6-Capitan");
        System.out.println("7-Major");
        System.out.println("8-Colonel");
        System.out.println("9-General");
        System.out.println("10-Marshal\n");
        System.out.print("-> ");
        int chosenPiece = keyboard.nextInt();
        System.out.print("Choose a place(e.g -> 2,3): ");
        String chosenPlace = keyboard.next();
        int row = chosenPlace.charAt(0);
        int col = chosenPlace.charAt(2);

        return piece;
    }
}
