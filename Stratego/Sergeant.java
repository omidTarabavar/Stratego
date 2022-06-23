package Stratego;

import java.util.ArrayList;

public class Sergeant extends Piece{
    public Sergeant(int row, int col){
        super(row,col);
        rank = 4;
    }

    @Override
    public void addToPieceList(Piece piece, ArrayList<Piece> piecesList, int[] peaceCounter) {
        if(peaceCounter[3] < 4){
            piecesList.add(piece);
        }
        else {
            System.out.println("You already have 4 sergeants");
        }
    }
}
