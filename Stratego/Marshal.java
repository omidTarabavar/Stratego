package Stratego;

import java.util.ArrayList;

public class Marshal extends Piece{
    public Marshal(int row, int col){
        super(row,col);
        rank = 10;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList,int[] pieceCounter) {
        if(pieceCounter[9] < 0){
            piecesList.add(this);
            pieceCounter[9] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have a marshal");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Marshal";
    }
}
