package Stratego;

import java.util.ArrayList;

public class Spy extends Piece{
    public Spy(int row, int col){
        super(row,col);
        rank = 1;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[9] < 1){
            piecesList.add(this);
            pieceCounter[9] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have a spy");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Spy";
    }
}
