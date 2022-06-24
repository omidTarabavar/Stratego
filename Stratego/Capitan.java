package Stratego;

import java.util.ArrayList;

public class Capitan extends Piece{
    public Capitan(int row, int col){
        super(row,col);
        rank = 6;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[5] < 4){
            piecesList.add(this);
            pieceCounter[5] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 4 capitans");
            return false;
        }
    }
}
