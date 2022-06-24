package Stratego;

import java.util.ArrayList;

public class Miner extends Piece{
    public Miner(int row, int col){
        super(row,col);
        rank = 3;
    }

    @Override
    public boolean addToPieceList(ArrayList<Piece> piecesList, int[] pieceCounter) {
        if(pieceCounter[2] < 5){
            piecesList.add(this);
            pieceCounter[2] += 1;
            System.out.println("Done");
            return true;
        }
        else {
            System.out.println("You already have 5 miners");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Miner";
    }
}
