package Stratego;

import java.util.ArrayList;

public class Player {
    public ArrayList<Piece> pieces = new ArrayList<>();
    // index = power piece-1 --> marshal = 9 - spy = 0 -- bomb = 10 , flag = 11;
    int[] pieceCounter = new int[12];

}
