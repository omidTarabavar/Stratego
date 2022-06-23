package Stratego;

import java.util.ArrayList;

public class Miner extends Piece{
    public Miner(int row, int col){
        super(row,col);
        rank = 3;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[2] < 5){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have 5 miners");
        }
    }
}
