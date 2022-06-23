package Stratego;

import java.util.ArrayList;

public class Capitan extends Piece{
    public Capitan(int row, int col){
        super(row,col);
        rank = 6;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[5] < 4){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have 4 capitans");
        }
    }
}
