package Stratego;

import java.util.ArrayList;

public class Colonel extends Piece{
    public Colonel(int row, int col){
        super(row,col);
        rank = 8;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[7] < 2){
            piecesList.add(this);
            pieceCounter[7] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 2 colonels");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Colonel";
    }
}
