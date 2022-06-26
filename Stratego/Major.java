package Stratego;

import java.util.ArrayList;

public class Major extends Piece{
    public Major(int row, int col,Player player){
        super(row,col,player);
        rank = 7;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[6] <3){
            piecesList.add(this);
            pieceCounter[6] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 3 majors");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Major";
    }
}
