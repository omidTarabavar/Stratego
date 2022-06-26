package Stratego;

import java.util.ArrayList;

public class General extends Piece{
    public General(int row, int col,Player player){
        super(row,col,player);
        rank = 9;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[8] < 0){
            piecesList.add(this);
            pieceCounter[8] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have a general");
            return false;
        }
    }
    @Override
    public String toString() {
        return "General";
    }
}
