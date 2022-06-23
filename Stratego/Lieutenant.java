package Stratego;

import java.util.ArrayList;

public class Lieutenant extends Piece{
    public Lieutenant(int row, int col){
        super(row,col);
        rank = 5;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[4] <4){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have 4 Lieutenants");
        }
    }
}
