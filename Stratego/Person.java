package Stratego;

import java.util.ArrayList;

public class Person extends Players{
    public ArrayList<Piece> pieces = new ArrayList<>();
    int playerNumber;
    // index = power piece-1 --> marshal = 9 - spy = 0 -- bomb = 10 , flag = 11;
    int[] pieceCounter = new int[12];
    public Person(){
        color = "Red";
    }
    @Override
    public String toString() {
        return "Person";
    }


}
