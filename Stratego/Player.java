package Stratego;

import java.util.ArrayList;

public class Player {
    ArrayList<Piece> pieces = new ArrayList<>();
    // index = power piece-1 --> marshal = 9 - spy = 0;
    int[] pieceCounter = new int[10];
    public void addPiece(Piece piece){
        pieces.add(piece);
    }

}
