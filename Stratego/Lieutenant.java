package Stratego;

import java.util.ArrayList;

public class Lieutenant extends Piece{

    public Lieutenant(int row, int col,Player player){
        super(row,col,player);
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
    @Override
    public String toString() {
        return "Lieutenant";
    }
}
