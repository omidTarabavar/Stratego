package Stratego;

import java.util.ArrayList;

public class Lieutenant extends Piece{
    public Lieutenant(int row, int col){
        super(row,col);
        rank = 5;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[4] <4){
            piecesList.add(this);
            pieceCounter[4] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 4 Lieutenants");
            return false;
        }
    }
}
